package com.quind.pruebatecnica.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(name = "account_type", nullable = false)
    private String accountType;
    @Column(name = "account_number", nullable = false)
    private String accountNumber;
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String status;
    @Column(nullable = false)
    private BigDecimal balance;
    @Column(name = "exempt_GMF", nullable = false)
    private Boolean exemptGMF;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "modified_date", nullable = false)
    private LocalDateTime modifiedDate;

    @Column(name = "id_customer", nullable = false)
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "id_customer", foreignKey = @ForeignKey(name = "FK_Customer"), insertable = false, updatable = false)
    private CustomerEntity customer;
}
