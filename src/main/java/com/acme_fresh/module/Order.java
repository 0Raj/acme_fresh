package com.acme_fresh.module;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderID;

    private LocalDate orderDate;

    @OneToMany(mappedBy = "productID")
    private List<Product> product;

    private Integer orderQuantity;

    private Double orderTotal;



    @ManyToOne
    @JoinColumn(name = "customer_MailID")
    private Customer customer;
}
