package com.quind.pruebatecnica.configurations;

import com.quind.pruebatecnica.domain.model.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class DataTest {
    public final static List<Customer> customers = Arrays.asList(
            new Customer(1L,"1","C.C", "Brayan","Stiven",
                    "erazo@gmail.com", LocalDate.of(1998,Month.MARCH,24), LocalDateTime.now(),LocalDateTime.now()
            ),
            new Customer(2L,"2","C.C", "STIVEN","Stiven 2",
                    "erazo@gmail.com", LocalDate.of(1998,Month.MARCH,24), LocalDateTime.now(),LocalDateTime.now()
            ),
            new Customer(3L,"3","DNI", "Brayan 2","Stiven 3",
                    "erazo@gmail.com", LocalDate.of(1998, Month.MARCH,24), LocalDateTime.now(),LocalDateTime.now()
            ),
            new Customer(4L,"4","C.C", "Brayan 4","Stiven 4",
                    "erazo@gmail.com", LocalDate.of(1998,Month.DECEMBER,24), LocalDateTime.now(),LocalDateTime.now()
            ),
            new Customer(5L,"5","DNI", "Brayan 4","Stiven 5",
                    "erazo@gmail.com", LocalDate.of(1998,Month.OCTOBER,24), LocalDateTime.now(),LocalDateTime.now()
            )
    );

    public final static Customer NEW_CUSTOMER = new Customer(6L,"5","DNI", "Brayan 4","Stiven 5",
            "erazo@gmail.com", LocalDate.of(1998,Month.OCTOBER,24), null,null
    );

    public final static Customer UPDATE_CUSTOMER = new Customer(1L,"5","DNI", "Brayan 4","Stiven 5",
            "erazo@gmail.com", LocalDate.of(1998,Month.OCTOBER,24), LocalDateTime.now(),LocalDateTime.now()
    );
}
