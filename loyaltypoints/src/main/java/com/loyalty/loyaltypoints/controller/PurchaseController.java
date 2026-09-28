package com.loyalty.loyaltypoints.controller;
import jakarta.validation.Valid;
import com.loyalty.loyaltypoints.entity.Purchase;
import com.loyalty.loyaltypoints.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/create")
    public ResponseEntity<Purchase> createPurchase(
            @Valid @RequestBody Purchase purchase) {

        purchaseService.createPurchase(purchase);

        return new ResponseEntity<>(
                purchase,
                HttpStatus.OK
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAllPurchases() {

        return new ResponseEntity<>(
                purchaseService.getAllPurchases(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getPurchaseById(
            @PathVariable int id) {

        Purchase purchase =
                purchaseService.getPurchaseById(id);

        if (purchase != null) {
            return new ResponseEntity<>(
                    purchase,
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Purchase> updatePurchase(
            @PathVariable("id") int id,
            @Valid @RequestBody Purchase purchase) {

        Purchase updatedPurchase =
                purchaseService.updatePurchase(id, purchase);

        if (updatedPurchase != null) {
            return new ResponseEntity<>(
                    updatedPurchase,
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePurchase(
            @PathVariable int id) {

        purchaseService.deletePurchase(id);

        return new ResponseEntity<>(
                "Purchase deleted successfully",
                HttpStatus.OK
        );
    }
}