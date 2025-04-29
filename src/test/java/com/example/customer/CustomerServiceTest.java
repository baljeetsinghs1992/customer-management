package com.example.customer;

import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CustomerServiceTest {

    private CustomerService service;

    @BeforeEach
    public void setUp() {
        service = new CustomerService();
    }

    @Test
    public void testTierSilverWhenNoSpend() {
        Customer c = new Customer();
        c.setAnnualSpend(null);
        assertEquals("Silver", service.calculateTier(c));
    }

    @Test
    public void testTierGoldValid() {
        Customer c = new Customer();
        c.setAnnualSpend(5000.0);
        c.setLastPurchaseDate(LocalDateTime.now().minusMonths(6));
        assertEquals("Gold", service.calculateTier(c));
    }

    @Test
    public void testTierPlatinumValid() {
        Customer c = new Customer();
        c.setAnnualSpend(15000.0);
        c.setLastPurchaseDate(LocalDateTime.now().minusMonths(3));
        assertEquals("Platinum", service.calculateTier(c));
    }
}
