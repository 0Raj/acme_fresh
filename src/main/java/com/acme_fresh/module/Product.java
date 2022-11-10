package com.acme_fresh.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productID;

    private String productName;

    private Category category;

    private Double productPrice;

    private LocalDate uploadedDate;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "farmer_email_id")
    private Farmer farmer;

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", category=" + category +
                ", productPrice=" + productPrice +
                ", uploadedDate=" + uploadedDate +
                '}';
    }
}
