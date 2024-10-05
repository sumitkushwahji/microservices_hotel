package com.sumit.user.service.controllers;


import com.netflix.discovery.converters.Auto;
import com.sumit.user.service.entities.User;
import com.sumit.user.service.services.UserService;
import com.sumit.user.service.services.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    //        create user
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);

    }


//        get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }



//        get user by id

    @GetMapping("/{uid}")
//    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public ResponseEntity<User> getUser(@PathVariable String uid){
        User user = userService.getUser(uid);
        return ResponseEntity.ok(user);
    }

//    public ResponseEntity<User> fallback( String uid ,Exception e) {
//        logger.info("Fallback response : {}", e.getMessage());
//        User build = User.builder().email("0000").name("").about("").build();
//        return new ResponseEntity<>(build, HttpStatus.OK);
//    }





}

