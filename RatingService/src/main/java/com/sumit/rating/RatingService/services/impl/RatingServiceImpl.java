package com.sumit.rating.RatingService.services.impl;

import com.sumit.rating.RatingService.entities.Rating;
import com.sumit.rating.RatingService.repositories.RatingRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.sumit.rating.RatingService.services.RatingService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingById(String ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(() -> new RuntimeException("Rating not found with ID: " + ratingId));
    }

    @Override
    public Rating addRating(Rating rating) {
        rating.setRatingId(UUID.randomUUID().toString());

        // Save the rating to the repository
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(String ratingId, Rating rating) {
        rating.setRatingId(ratingId);
        return ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(String ratingId) {
        ratingRepository.deleteById(ratingId);
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
