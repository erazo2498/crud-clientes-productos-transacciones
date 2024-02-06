package com.quind.pruebatecnica.adapters.driven.jpa.mysql.entity;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class CustomerEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "identification_number")
    private String identificationNumber;
    @Column(name = "identification_type")
    private String identificationType;
    private String name;
    private String surname;
    private String mail;
    private LocalDate birthday;

    @OneToMany(mappedBy = "customer")
    private List<ProductEntity> products;
}
