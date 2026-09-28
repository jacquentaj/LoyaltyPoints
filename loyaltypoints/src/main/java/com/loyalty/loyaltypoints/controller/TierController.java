package com.loyalty.loyaltypoints.controller;
import jakarta.validation.Valid;
import com.loyalty.loyaltypoints.entity.Tier;
import com.loyalty.loyaltypoints.service.TierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tier")
public class TierController {

    @Autowired
    TierService tierService;

    @PostMapping("/create")
    public ResponseEntity<Tier> createTier(
            @Valid @RequestBody Tier tier) {

        tierService.createTier(tier);

        return new ResponseEntity<>(tier, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tier>> getAllTiers() {

        return new ResponseEntity<>(
                tierService.getAllTiers(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tier> getTierById(
            @PathVariable int id) {

        Tier tier = tierService.getTierById(id);

        if (tier != null) {
            return new ResponseEntity<>(tier, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tier> updateTier(
            @PathVariable("id") int id,
            @Valid @RequestBody Tier tier) {

        Tier updatedTier =
                tierService.updateTier(id, tier);

        if (updatedTier != null) {
            return new ResponseEntity<>(
                    updatedTier,
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTier(
            @PathVariable int id) {

        tierService.deleteTier(id);

        return new ResponseEntity<>(
                "Tier deleted successfully",
                HttpStatus.OK
        );
    }
}