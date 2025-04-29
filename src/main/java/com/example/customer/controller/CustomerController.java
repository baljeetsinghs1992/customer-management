package com.example.customer.controller;

import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createCustomer(@Valid @RequestBody Customer customer) {
        if (customer.getId() != null) {
            return ResponseEntity.badRequest().body(Map.of("error", "ID must not be provided"));
        }
        Customer saved = service.save(customer);
        Map<String, Object> response = new HashMap<>();
        response.put("id", saved.getId());
        response.put("name", saved.getName());
        response.put("email", saved.getEmail());
        response.put("annualSpend", saved.getAnnualSpend());
        response.put("lastPurchaseDate", saved.getLastPurchaseDate());
        response.put("tier", service.calculateTier(saved));
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCustomerById(@PathVariable UUID id) {
        return service.findById(id)
                .map(c -> ResponseEntity.ok(toResponse(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getCustomer(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String email) {
        Optional<Customer> customerOpt = Optional.empty();
        if (name != null) {
            customerOpt = service.findByName(name);
        } else if (email != null) {
            customerOpt = service.findByEmail(email);
        }
        return customerOpt.map(c -> ResponseEntity.ok(toResponse(c)))
                          .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCustomer(@PathVariable UUID id,
                                         @Valid @RequestBody Customer customer) {
        return service.findById(id).map(existing -> {
            existing.setName(customer.getName());
            existing.setEmail(customer.getEmail());
            existing.setAnnualSpend(customer.getAnnualSpend());
            existing.setLastPurchaseDate(customer.getLastPurchaseDate());
            Customer updated = service.save(existing);
            return ResponseEntity.ok(toResponse(updated));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private Map<String, Object> toResponse(Customer c) {
        Map<String, Object> res = new HashMap<>();
        res.put("id", c.getId());
        res.put("name", c.getName());
        res.put("email", c.getEmail());
        res.put("annualSpend", c.getAnnualSpend());
        res.put("lastPurchaseDate", c.getLastPurchaseDate());
        res.put("tier", service.calculateTier(c));
        return res;
    }
}
