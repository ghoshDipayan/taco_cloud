package com.taco_cloud.taco_cloud.api.controller;


import com.taco_cloud.taco_cloud.api.model.TacoOrder;
import com.taco_cloud.taco_cloud.api.model.User;
import com.taco_cloud.taco_cloud.api.respository.TacoOrderRepository;
import com.taco_cloud.taco_cloud.api.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/api/order")
public class TacoOrderController {

    private TacoOrderRepository orderRepo;
    private UserRepository userRepo;

    @Autowired
    public TacoOrderController(TacoOrderRepository orderRepo,UserRepository userRepo){
        this.orderRepo=orderRepo;
        this.userRepo=userRepo;
    }


    @GetMapping(produces="application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Iterable<TacoOrder> getOrders(){
        return orderRepo.findAll();
    }

    @GetMapping(path="/{id}",produces="application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TacoOrder getOrderById(@PathVariable("id") Long id){
        Optional<TacoOrder> res=orderRepo.findById(id);

        if(res.isPresent()){
            return res.get();
        }else{
            throw new EntityNotFoundException();
        }
    }

    @PostMapping(path="/user={userid}",consumes="application/json",produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TacoOrder postOrder(@PathVariable("userid") Long userId,@Valid @RequestBody TacoOrder order){
        Optional<User> user= userRepo.findById(userId);
        if(user.isPresent()) {
            order=orderRepo.save(order);
            user.get().getTaco_orders().add(order);
            userRepo.save(user.get());
            return order;
        }else{
            throw new EntityNotFoundException("User not found");
        }
    }

    @PatchMapping(path="/{id}",consumes="application/json",produces="application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TacoOrder editOrder(@PathVariable("id") Long id, @RequestBody TacoOrder patch){

        Optional<TacoOrder> res=orderRepo.findById(id);
        if(res.isPresent()){
            TacoOrder order=res.get();
            if(patch.getTacos()!=null)
                order.setTacos(patch.getTacos());
            if(patch.getName()!=null)
                order.setName(patch.getName());
            if(patch.getMobile_number()!=null)
                order.setMobile_number(patch.getMobile_number());
            if(patch.getCity()!=null)
                order.setCity(patch.getCity());
            if(patch.getState()!=null)
                order.setState(patch.getState());
            if(patch.getStreet()!=null)
                order.setStreet(patch.getStreet());
            if(patch.getCcNumber()!=null)
                order.setCcNumber(patch.getCcNumber());
            if(patch.getCcCVV()!=null)
                order.setCcCVV(patch.getCcCVV());
            if(patch.getCcExpiration()!=null)
                order.setCcExpiration(patch.getCcExpiration());

            return orderRepo.save(order);

        } else {
            throw new EntityNotFoundException();
        }
    }

    @GetMapping(path="user={userid}",produces="application/json")
    public Iterable<TacoOrder> getUserOrders(@PathVariable("userid") Long userId){

        Optional<User> user=userRepo.findById(userId);

        if(user.isPresent()){
            return user.get().getTaco_orders();
        }else
            throw new EntityNotFoundException("User Not Found");

    }


}
