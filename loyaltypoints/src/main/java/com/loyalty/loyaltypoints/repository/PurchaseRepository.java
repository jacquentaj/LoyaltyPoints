package com.loyalty.loyaltypoints.repository;

import com.loyalty.loyaltypoints.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}