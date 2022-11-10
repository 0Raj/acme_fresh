package com.acme_fresh.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer orderID;

    private LocalDate orderDate;

    @OneToMany(mappedBy = "productID")
    private List<Product> productList = new ArrayList<>();

    private Integer orderQuantity;

    private Double orderTotal;

    private OrderStatus orderStatus;


    @ManyToOne
    @JoinColumn(name = "customer_email_id")
    private Customer customer;
}
