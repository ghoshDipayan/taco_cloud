package com.taco_cloud.taco_cloud.api.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotNull
    @NotBlank
    @Size(min=4, message="Minimum 4 characters")
    private String name;

    @NotNull
    @Size(min=1, message="Minimimum 1 ingredient")
    @ManyToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredients;


    private Date createdAt;

    @PrePersist
    void createdAt(){
        createdAt=new Date();
    }

}
