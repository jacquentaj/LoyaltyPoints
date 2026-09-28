package com.loyalty.loyaltypoints.entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Tier {

    @Id
    private int id;

    @NotBlank(message = "Tier name is required")
    private String name;

    @Min(value = 0, message = "Minimum points cannot be negative")
    private int minimumPoints;

    @Min(value = 0, message = "Discount percentage cannot be negative")
    private double discountPercentage;

    public Tier() {
    }

    public Tier(int id, String name, int minimumPoints,
                double discountPercentage) {
        this.id = id;
        this.name = name;
        this.minimumPoints = minimumPoints;
        this.discountPercentage = discountPercentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinimumPoints() {
        return minimumPoints;
    }

    public void setMinimumPoints(int minimumPoints) {
        this.minimumPoints = minimumPoints;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}