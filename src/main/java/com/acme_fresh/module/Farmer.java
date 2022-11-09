package com.acme_fresh.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Farmer extends User{

    @JsonIgnore
    @OneToMany(mappedBy = "farmer")
    private List<Product> productList = new ArrayList<>();

    @JsonIgnore
    private String role = "ROLE_Farmer";
}
