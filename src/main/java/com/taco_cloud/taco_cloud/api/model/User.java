package com.taco_cloud.taco_cloud.api.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    @OneToMany(targetEntity = TacoOrder.class)
    private List<TacoOrder> taco_orders;

    private Date createdAt;

    @PrePersist
    void createdAt(){
        createdAt=new Date();
    }



}
