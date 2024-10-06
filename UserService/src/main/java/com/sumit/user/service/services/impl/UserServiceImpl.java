package com.sumit.user.service.services.impl;

import com.sumit.user.service.entities.Hotel;
import com.sumit.user.service.entities.Rating;
import com.sumit.user.service.entities.User;
import com.sumit.user.service.exceptions.ResourceNotFoundExceptions;
import com.sumit.user.service.repositories.UserRepository;
import com.sumit.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    //    logger
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User createUser(User user) {
        // Generate a random UUID and set it as the user's UID
        user.setUid(UUID.randomUUID().toString());

        // Save the user to the repository
        return userRepository.save(user);
    }

    @Override
    public User getUser(String uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new ResourceNotFoundExceptions("User not found with ID: " + uid));

        // Call another microservice to get the ratings of the user
        Rating[] ratingsOfUser = restTemplate.getForObject(
                "http://RATING-SERVICE/ratings/user/" + user.getUid(), Rating[].class);
        logger.info("Ratings: {}", ratingsOfUser);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        // Call another microservice to get the hotels of each rating using stream and map
        List<Rating> ratingList = ratings.stream().map(rating -> {
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(
                    "http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }


    @Override
    public User updateUser(String uid, User user) {
        return null;
    }

    @Override
    public void deleteUser(String uid) {
        userRepository.deleteById(uid);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
