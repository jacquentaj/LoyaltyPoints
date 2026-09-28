package com.loyalty.loyaltypoints.controller;

import com.loyalty.loyaltypoints.entity.Customer;
import com.loyalty.loyaltypoints.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(
            @Valid @RequestBody Customer customer) {

        customerService.createCustomer(customer);

        return new ResponseEntity<>(
                customer,
                HttpStatus.OK
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {

        return new ResponseEntity<>(
                customerService.getAllCustomers(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable int id) {

        Customer customer =
                customerService.getCustomerById(id);

        if (customer != null) {
            return new ResponseEntity<>(
                    customer,
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable("id") int id,
            @Valid @RequestBody Customer customer) {

        Customer updatedCustomer =
                customerService.updateCustomer(id, customer);

        if (updatedCustomer != null) {
            return new ResponseEntity<>(
                    updatedCustomer,
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable int id) {

        customerService.deleteCustomer(id);

        return new ResponseEntity<>(
                "Customer deleted successfully",
                HttpStatus.OK
        );
    }
}