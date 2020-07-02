package com.taco_cloud.taco_cloud.api.model;


import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class TacoOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    // Profile and Address Details

    @NotBlank(message="Name cannot be blank")
    @Size(min=4, message = "Minimum length 4 character")
    private String name;

    @NotBlank(message="Phone number cannot be blank")
    @Size(min=10,message="Invalid mobile number")
    private String mobile_number;

    @NotBlank
    @Size(min=4)
    private String street;

    @NotBlank
    @Size(min=4)
    private String city;

    @NotBlank
    @Size(min=4)
    private String state;

    @NotBlank
    private String pincode;

    // Payment details

    @NotBlank
    @CreditCardNumber(message="Invalid Credit Card Number")
    private String ccNumber;

    @NotBlank
    @Digits(integer=3, fraction=0, message="Invalid CVV number")
    private String ccCVV;

    @NotBlank
    @Pattern(regexp = "^(0[1-9]|1[1-2])(\\/)([2-9][2-9])$")
    private String ccExpiration;

    @NotNull
    @Size(min=1,message="Minimum size is 1")
    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos;

    private Date createdAt;
    @PrePersist
    void createdAt(){
        createdAt=new Date();
    }




}
