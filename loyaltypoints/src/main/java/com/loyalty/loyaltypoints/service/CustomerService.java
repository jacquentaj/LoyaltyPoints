package com.loyalty.loyaltypoints.service;
import com.loyalty.loyaltypoints.repository.TierRepository;
import com.loyalty.loyaltypoints.entity.Customer;
import com.loyalty.loyaltypoints.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TierRepository tierRepository;

    public Customer createCustomer(Customer customer) {

        if (!tierRepository.existsById(customer.getTierId())) {
            throw new RuntimeException("Tier not found");
        }

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer updateCustomer(int id, Customer customer) {

        Customer existingCustomer =
                customerRepository.findById(id).orElse(null);

        if (existingCustomer != null) {

            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setAvailablePoints(
                    customer.getAvailablePoints()
            );
            existingCustomer.setLifetimePoints(
                    customer.getLifetimePoints()
            );
            existingCustomer.setTierId(customer.getTierId());

            return customerRepository.save(existingCustomer);
        }

        return null;
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}