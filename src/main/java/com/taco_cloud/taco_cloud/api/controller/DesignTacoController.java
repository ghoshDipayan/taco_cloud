package com.taco_cloud.taco_cloud.api.controller;


import com.taco_cloud.taco_cloud.api.model.Taco;
import com.taco_cloud.taco_cloud.api.respository.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;


@Slf4j
@RequestMapping(path="/api/design")
@RestController
public class DesignTacoController {

    private TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(TacoRepository tacoRepository){

        this.tacoRepository=tacoRepository;
    }

    @PostMapping(consumes="application/json",produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco saveTaco(@Valid @RequestBody Taco taco){
        return tacoRepository.save(taco);
    }


    @GetMapping(path="/taco/{id}",produces="application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Taco getTacoById(@PathVariable("id") Long id){
        Optional<Taco> res=tacoRepository.findById(id);
        log.info("Taco "+id+" requested");
        if(res.isPresent()){
            log.info("Taco found: "+res.get());
            return res.get();
        }else{
            log.error("Taco "+id+" Not Found");
            throw new EntityNotFoundException();
        }
    }

    @GetMapping(path="/taco",produces="application/json")
    public Iterable<Taco> getTacos(){
        Iterable<Taco> res=tacoRepository.findAll();
        log.info("All Taco Requested :"+res);
        return  res;
    }

    @DeleteMapping(path="/taco/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaco(@PathVariable("id") Long id){
        tacoRepository.deleteById(id);
    }

    @PatchMapping(path="/taco/{id}",consumes="application/json",produces="application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Taco editTaco(@PathVariable("id") Long id, @RequestBody Taco patch){
        Optional<Taco> res=tacoRepository.findById(id);
        if(res.isPresent()){
               Taco taco=res.get();
               if(patch.getIngredients()!=null){
                   taco.setIngredients(patch.getIngredients());
               }
               if(patch.getName()!=null){
                   taco.setName(patch.getName());
               }
               return tacoRepository.save(taco);
        }else{
            throw new EntityNotFoundException();
        }
    }



}
