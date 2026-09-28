package com.loyalty.loyaltypoints.repository;

import com.loyalty.loyaltypoints.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}