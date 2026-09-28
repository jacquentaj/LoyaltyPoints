package com.loyalty.loyaltypoints.entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
@Entity
public class PointsLedger {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    @Column(name = "customer_id")
    private int customerId;

    @NotBlank(message = "Transaction type is required")
    private String transactionType;

    @Min(value = 1, message = "Points must be greater than 0")
    private int points;

    @NotBlank(message = "Transaction date is required")
    private String transactionDate;

    private String description;
    public PointsLedger() {
    }

    public PointsLedger(int id, int customerId,
                        String transactionType,
                        int points,
                        String transactionDate,
                        String description) {

        this.id = id;
        this.customerId = customerId;
        this.transactionType = transactionType;
        this.points = points;
        this.transactionDate = transactionDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}