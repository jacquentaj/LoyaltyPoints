package com.loyalty.loyaltypoints.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Purchase {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    @Column(name = "customer_id")
    private int customerId;


    @Min(value = 0, message = "Purchase amount cannot be negative")
    private double purchaseAmount;
    @Min(value = 0, message = "Purchase amount cannot be negative")
    private double discountedAmount;
    @Min(value = 0, message = "Purchase amount cannot be negative")
    private int pointsRedeemed;
    @Min(value = 0, message = "Points earned cannot be negative")
    private int pointsEarned;

    @NotBlank(message = "Purchase date is required")
    private String purchaseDate;
    public Purchase() {
    }

    public Purchase(int id, int customerId, double purchaseAmount,
                    double discountedAmount, int pointsRedeemed,
                    int pointsEarned, String purchaseDate) {
        this.id = id;
        this.customerId = customerId;
        this.purchaseAmount = purchaseAmount;
        this.discountedAmount = discountedAmount;
        this.pointsRedeemed = pointsRedeemed;
        this.pointsEarned = pointsEarned;
        this.purchaseDate = purchaseDate;
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

    public double getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(double purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    public double getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(double discountedAmount) {
        this.discountedAmount = discountedAmount;
    }
    public int getPointsRedeemed() {
        return pointsRedeemed;
    }

    public void setPointsRedeemed(int pointsRedeemed) {
        this.pointsRedeemed = pointsRedeemed;
    }
}