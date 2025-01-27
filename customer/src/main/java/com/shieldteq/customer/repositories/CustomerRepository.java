package com.shieldteq.customer.repositories;

import com.shieldteq.customer.entities.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    List<Customer> findAllByActiveIsTrue();

    Optional<Customer> findByCustomerNumber(String s);

    @Modifying
    @Transactional
    void deleteByCustomerNumber(String customerNumber);
}
