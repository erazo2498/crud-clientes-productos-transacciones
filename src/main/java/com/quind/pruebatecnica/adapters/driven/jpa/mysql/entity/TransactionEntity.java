package com.quind.pruebatecnica.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(name = "id_origin_product")
    private Long originProductId;

    @Column(name = "id_destination_product")
    private Long destinationProductId;

    @ManyToOne
    @JoinColumn(name = "id_origin_product", foreignKey = @ForeignKey(name = "fk_origin_product"), insertable = false, updatable = false)
    private ProductEntity originProduct;

    @ManyToOne
    @JoinColumn(name = "id_destination_product", foreignKey = @ForeignKey(name = "fk_destination_product"), insertable = false, updatable = false)
    private ProductEntity destinationProduct;
}
