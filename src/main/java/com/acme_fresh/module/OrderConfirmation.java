package com.acme_fresh.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class OrderConfirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false)
    private Long orderID;


    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    @JoinColumn(name = "customer_email_id")
    private Customer customer;

    @OneToMany(mappedBy = "productID")
    private List<Product> productList = new ArrayList<>();

    private Double total;

    private LocalDate orderedDate;

    private OrderStatus orderStatus;




}
