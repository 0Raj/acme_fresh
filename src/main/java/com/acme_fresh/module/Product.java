package com.acme_fresh.module;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productID;

    private String productName;

    private Category category;

    private Double productPrice;

    private LocalDate yeildDate;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "farmer_email_id")
    private Farmer farmer;

}
