package com.taco_cloud.taco_cloud.api.controller;


import com.taco_cloud.taco_cloud.api.model.Ingredient;
import com.taco_cloud.taco_cloud.api.respository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/ingredients")
@CrossOrigin("*")
public class IngredientController {

    private IngredientRepository repo;

    @Autowired
    public IngredientController(IngredientRepository repo){
        this.repo=repo;
    }

    @GetMapping(produces="application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<Ingredient> getIngredients(){
        return repo.findAll();
    }

    @GetMapping(path="/{id}",produces="application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Ingredient getIngredientById(@PathVariable("id") String id){
        Optional<Ingredient> res=repo.findById(id);
        if(res.isPresent()){
            return res.get();
        }else
            throw new EntityNotFoundException();
    }

    @PostMapping(produces = "application/json",consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient){
        return repo.save(ingredient);
    }

    @DeleteMapping(path="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") String id){
        repo.deleteById(id);
    }

}
