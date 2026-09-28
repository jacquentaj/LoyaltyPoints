package com.loyalty.loyaltypoints.entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.persistence.OneToMany;
import java.util.List;
import jakarta.persistence.Column;
@Entity
public class Customer {

    @Id
    private int id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Enter a valid email")
    @NotBlank(message = "Email is required")
    private String email;

    @Min(value = 0, message = "Available points cannot be negative")
    private int availablePoints;

    @Min(value = 0, message = "Lifetime points cannot be negative")
    private int lifetimePoints;

    @ManyToOne
    @JoinColumn(name = "tier_id", insertable = false, updatable = false)
    private Tier tier;

    @OneToMany(mappedBy = "customer")
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "customer")
    private List<PointsLedger> pointsLedger;

    @Min(value = 1, message = "Tier ID must be greater than 0")
    @Column(name = "tier_id")
    private int tierId;


    public Customer() {
    }

    public Customer(int id, String name, String email,
                    int availablePoints, int lifetimePoints,
                    int tierId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.availablePoints = availablePoints;
        this.lifetimePoints = lifetimePoints;
        this.tierId = tierId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(int availablePoints) {
        this.availablePoints = availablePoints;
    }

    public int getLifetimePoints() {
        return lifetimePoints;
    }

    public void setLifetimePoints(int lifetimePoints) {
        this.lifetimePoints = lifetimePoints;
    }

    public int getTierId() {
        return tierId;
    }

    public void setTierId(int tierId) {
        this.tierId = tierId;
    }
}