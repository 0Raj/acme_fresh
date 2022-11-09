package com.acme_fresh.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Customer extends User{

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Order> productList = new ArrayList<>();


}
