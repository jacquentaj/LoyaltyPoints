package com.loyalty.loyaltypoints.service;
import org.springframework.beans.factory.annotation.Value;
import com.loyalty.loyaltypoints.entity.Customer;
import com.loyalty.loyaltypoints.entity.Purchase;
import com.loyalty.loyaltypoints.repository.CustomerRepository;
import com.loyalty.loyaltypoints.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.loyalty.loyaltypoints.entity.Tier;
import com.loyalty.loyaltypoints.repository.TierRepository;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TierRepository tierRepository;
    @Value("${loyalty.points-per-rupee}")
    private double pointsPerRupee;


    public Purchase createPurchase(Purchase purchase) {

        Customer customer =
                customerRepository
                        .findById(purchase.getCustomerId())
                        .orElse(null);

        if (customer == null) {
            throw new RuntimeException(
                    "Customer not found"
            );
        }

        int calculatedPoints =
                (int) (purchase.getPurchaseAmount() * pointsPerRupee);

        purchase.setPointsEarned(calculatedPoints);
        Tier customerTier = tierRepository
                .findById(customer.getTierId())
                .orElse(null);

        double discountedAmount = purchase.getPurchaseAmount();

        if (customerTier != null) {
            double discount =
                    purchase.getPurchaseAmount()
                            * customerTier.getDiscountPercentage()
                            / 100;

            discountedAmount =
                    purchase.getPurchaseAmount() - discount;
        }

        purchase.setDiscountedAmount(discountedAmount);
        int pointsRedeemed = purchase.getPointsRedeemed();

        if (pointsRedeemed > customer.getAvailablePoints()) {
            throw new RuntimeException(
                    "Insufficient points for redemption"
            );
        }

        double redemptionDiscount = pointsRedeemed;

        discountedAmount =
                Math.max(0, discountedAmount - redemptionDiscount);

        purchase.setDiscountedAmount(discountedAmount);

        customer.setAvailablePoints(
                customer.getAvailablePoints() - pointsRedeemed
        );
        customer.setAvailablePoints(
                customer.getAvailablePoints() + calculatedPoints
        );

        customer.setLifetimePoints(
                customer.getLifetimePoints() + calculatedPoints
        );

        updateCustomerTier(customer);
        customerRepository.save(customer);

        return purchaseRepository.save(purchase);
    }


    public List<Purchase> getAllPurchases() {

        return purchaseRepository.findAll();
    }


    public Purchase getPurchaseById(int id) {

        return purchaseRepository
                .findById(id)
                .orElse(null);
    }


    public Purchase updatePurchase(
            int id, Purchase purchase) {

        Purchase existingPurchase =
                purchaseRepository
                        .findById(id)
                        .orElse(null);

        if (existingPurchase != null) {

            existingPurchase.setCustomerId(
                    purchase.getCustomerId()
            );

            existingPurchase.setPurchaseAmount(
                    purchase.getPurchaseAmount()
            );

            existingPurchase.setPointsEarned(
                    purchase.getPointsEarned()
            );

            existingPurchase.setPurchaseDate(
                    purchase.getPurchaseDate()
            );

            return purchaseRepository.save(
                    existingPurchase
            );
        }

        return null;
    }


    public void deletePurchase(int id) {

        purchaseRepository.deleteById(id);
    }
    private void updateCustomerTier(Customer customer) {

        List<Tier> tiers = tierRepository.findAll();

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
            customer.setTierId(currentTier.getId());
        }
    }
}