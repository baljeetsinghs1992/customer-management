package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public Optional<Customer> findById(UUID id) {
        return repository.findById(id);
    }

    public Optional<Customer> findByName(String name) {
        return repository.findByName(name);
    }

    public Optional<Customer> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public String calculateTier(Customer customer) {
        Double spend = customer.getAnnualSpend();
        LocalDateTime lastPurchase = customer.getLastPurchaseDate();

        if (spend == null) return "Silver";

        if (spend >= 10000) {
            if (lastPurchase != null && lastPurchase.isAfter(LocalDateTime.now().minusMonths(6))) {
                return "Platinum";
            }
        } else if (spend >= 1000) {
            if (lastPurchase != null && lastPurchase.isAfter(LocalDateTime.now().minusMonths(12))) {
                return "Gold";
            }
        }
        return "Silver";
    }
}
