package com.loyalty.loyaltypoints.service;

import com.loyalty.loyaltypoints.entity.Customer;
import com.loyalty.loyaltypoints.entity.PointsLedger;
import com.loyalty.loyaltypoints.entity.Tier;
import com.loyalty.loyaltypoints.repository.CustomerRepository;
import com.loyalty.loyaltypoints.repository.PointsLedgerRepository;
import com.loyalty.loyaltypoints.repository.TierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointsLedgerService {

    @Autowired
    PointsLedgerRepository pointsLedgerRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TierRepository tierRepository;


    public PointsLedger createTransaction(
            PointsLedger pointsLedger) {

        Customer customer =
                customerRepository
                        .findById(pointsLedger.getCustomerId())
                        .orElse(null);

        if (customer == null) {
            throw new RuntimeException(
                    "Customer not found"
            );
        }

        String type =
                pointsLedger.getTransactionType();

        int points =
                pointsLedger.getPoints();


        // EARN POINTS
        if (type.equalsIgnoreCase("EARN")) {

            customer.setAvailablePoints(
                    customer.getAvailablePoints() + points
            );

            customer.setLifetimePoints(
                    customer.getLifetimePoints() + points
            );

            updateCustomerTier(customer);
        }


        // REDEEM POINTS
        else if (type.equalsIgnoreCase("REDEEM")) {

            if (points > customer.getAvailablePoints()) {

                throw new RuntimeException(
                        "Insufficient points for redemption"
                );
            }

            customer.setAvailablePoints(
                    customer.getAvailablePoints() - points
            );
        }


        else {
            throw new RuntimeException(
                    "Transaction type must be EARN or REDEEM"
            );
        }


        customerRepository.save(customer);

        return pointsLedgerRepository.save(pointsLedger);
    }


    private void updateCustomerTier(Customer customer) {

        List<Tier> tiers =
                tierRepository.findAll();

        Tier currentTier = null;

        for (Tier tier : tiers) {

            if (customer.getLifetimePoints()
                    >= tier.getMinimumPoints()) {

                if (currentTier == null ||
                        tier.getMinimumPoints()
                                > currentTier.getMinimumPoints()) {

                    currentTier = tier;
                }
            }
        }

        if (currentTier != null) {

            customer.setTierId(
                    currentTier.getId()
            );
        }
    }


    public List<PointsLedger> getAllTransactions() {

        return pointsLedgerRepository.findAll();
    }


    public PointsLedger getTransactionById(int id) {

        return pointsLedgerRepository
                .findById(id)
                .orElse(null);
    }


    public PointsLedger updateTransaction(
            int id, PointsLedger pointsLedger) {

        PointsLedger existingTransaction =
                pointsLedgerRepository
                        .findById(id)
                        .orElse(null);

        if (existingTransaction != null) {

            existingTransaction.setCustomerId(
                    pointsLedger.getCustomerId()
            );

            existingTransaction.setTransactionType(
                    pointsLedger.getTransactionType()
            );

            existingTransaction.setPoints(
                    pointsLedger.getPoints()
            );

            existingTransaction.setTransactionDate(
                    pointsLedger.getTransactionDate()
            );

            existingTransaction.setDescription(
                    pointsLedger.getDescription()
            );

            return pointsLedgerRepository.save(
                    existingTransaction
            );
        }

        return null;
    }


    public void deleteTransaction(int id) {

        pointsLedgerRepository.deleteById(id);
    }
}