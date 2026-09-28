package com.loyalty.loyaltypoints.repository;

import com.loyalty.loyaltypoints.entity.Tier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TierRepository extends JpaRepository<Tier, Integer> {
}