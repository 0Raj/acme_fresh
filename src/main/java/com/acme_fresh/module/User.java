package com.acme_fresh.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class User {

    @Id
    private String emailId;

    private String mobileNumber;

    @NotNull
    private String name;

    @NotNull
    private Gender gender;

    @NotNull
    private Integer age;

    @NotNull
    @JsonIgnore
    private String password;

    @JsonIgnore
    private String role;


}
