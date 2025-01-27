package com.shieldteq.customer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "customer_id_record")
public class CustomerIdRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerNumberSeq")
    @SequenceGenerator(name = "customerNumberSeq", sequenceName = "CUSTOMER_NUMBER_SEQ", initialValue = 1000000, allocationSize = 1)
    private Long id;
    private String email;
    private String phone;
}
