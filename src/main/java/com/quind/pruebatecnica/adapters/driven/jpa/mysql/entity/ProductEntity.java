package com.quind.pruebatecnica.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^(33|53)\\d{8}$",
            message = "El número de las cuentas debe iniciar en '33 o 53' y tener 10 dígitos numéricos.")
    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;
    @Column(nullable = false)
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
