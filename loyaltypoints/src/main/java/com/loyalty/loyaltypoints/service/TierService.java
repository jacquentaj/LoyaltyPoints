package com.loyalty.loyaltypoints.service;

import com.loyalty.loyaltypoints.entity.Tier;
import com.loyalty.loyaltypoints.repository.TierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TierService {

    @Autowired
    TierRepository tierRepository;

    public Tier createTier(Tier tier) {
        return tierRepository.save(tier);
    }

    public List<Tier> getAllTiers() {
        return tierRepository.findAll();
    }

    public Tier getTierById(int id) {
        return tierRepository.findById(id).orElse(null);
    }

    public Tier updateTier(int id, Tier tier) {

        Tier existingTier =
                tierRepository.findById(id).orElse(null);

        if (existingTier != null) {

            existingTier.setName(tier.getName());
            existingTier.setMinimumPoints(tier.getMinimumPoints());
            existingTier.setDiscountPercentage(
                    tier.getDiscountPercentage()
            );

            return tierRepository.save(existingTier);
        }

        return null;
    }

    public void deleteTier(int id) {
        tierRepository.deleteById(id);
    }
}