package com.loyalty.loyaltypoints.controller;
import jakarta.validation.Valid;
import com.loyalty.loyaltypoints.entity.PointsLedger;
import com.loyalty.loyaltypoints.service.PointsLedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ledger")
public class PointsLedgerController {

    @Autowired
    PointsLedgerService pointsLedgerService;

    @PostMapping("/create")
    public ResponseEntity<PointsLedger> createTransaction(
            @Valid @RequestBody PointsLedger pointsLedger) {
        pointsLedgerService.createTransaction(pointsLedger);

        return new ResponseEntity<>(
                pointsLedger,
                HttpStatus.OK
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<PointsLedger>> getAllTransactions() {

        return new ResponseEntity<>(
                pointsLedgerService.getAllTransactions(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PointsLedger> getTransactionById(
            @PathVariable int id) {

        PointsLedger transaction =
                pointsLedgerService.getTransactionById(id);

        if (transaction != null) {
            return new ResponseEntity<>(
                    transaction,
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PointsLedger> updateTransaction(
            @PathVariable("id") int id,
            @Valid @RequestBody PointsLedger pointsLedger) {

        PointsLedger updatedTransaction =
                pointsLedgerService.updateTransaction(
                        id, pointsLedger
                );

        if (updatedTransaction != null) {
            return new ResponseEntity<>(
                    updatedTransaction,
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(
            @PathVariable int id) {

        pointsLedgerService.deleteTransaction(id);

        return new ResponseEntity<>(
                "Points transaction deleted successfully",
                HttpStatus.OK
        );
    }
}