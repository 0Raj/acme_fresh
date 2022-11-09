package com.acme_fresh.module;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class FarmerDTO {

    @Email
    private String emailId;

    @Pattern(regexp = "[6-9]{1}[0-9]{9}", message = "Mobile number must have 10 digits")
    private String mobileNumber;

    @Pattern(regexp = "[a-zA-Z]{3,12}", message = "Name must not contain numbers or special characters")
    private String name;

    @Pattern(regexp = "[A-Z]{0,1}", message = "Gender should contain only starting letter in caps")
    private String gender;

    private Integer age;

    private String password;

}
