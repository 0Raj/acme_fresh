package com.acme_fresh.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User{

//    @JsonIgnore
//    @OneToMany(mappedBy = "customer")
//    private Set<Order> orderList = new HashSet<>();

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<OrderConfirmation> orderConfirmationList = new ArrayList<>();


}
