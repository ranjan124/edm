package com.shieldteq.customer.repositories;

import com.shieldteq.customer.entities.CustomerIdRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerIdRecordRepository extends JpaRepository<CustomerIdRecord, Long> {
    Optional<CustomerIdRecord> findByEmailOrPhone(String email, String phone);
}
