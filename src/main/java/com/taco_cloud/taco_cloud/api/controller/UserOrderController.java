package com.taco_cloud.taco_cloud.api.controller;


import com.taco_cloud.taco_cloud.api.model.User;
import com.taco_cloud.taco_cloud.api.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/api/user")
public class UserOrderController {

    private UserRepository userRepository;

    @Autowired
    public UserOrderController(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @PostMapping(consumes="application/json",produces="application/json")
    public User postUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }
    @GetMapping(produces="application/json")
    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }
    @GetMapping(path="{id}",produces="application/json")
    public User getUserById(@PathVariable("id") Long id){
        return userRepository.findById(id).get();
    }


}
