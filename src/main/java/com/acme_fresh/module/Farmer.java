package com.acme_fresh.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Farmer extends User{

    @JsonIgnore
    @OneToMany(mappedBy = "farmer",cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();

}
