package com.codegym.dto;

import com.codegym.model.Customer;

/**
 * Data Transfer Object for Customer entity
 * Follows modern Java practices for DTOs
 */
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    
    public CustomerDTO() {}
    
    public CustomerDTO(Long id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }
    
    /**
     * Factory method to create CustomerDTO from Customer entity
     */
    public static CustomerDTO from(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
    
    /**
     * Convert DTO to Customer entity
     */
    public Customer toEntity() {
        return new Customer(id, name, email, address);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
