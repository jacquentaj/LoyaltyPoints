package com.loyalty.loyaltypoints.repository;

import com.loyalty.loyaltypoints.entity.PointsLedger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointsLedgerRepository
        extends JpaRepository<PointsLedger, Integer> {
}