package com.sumit.rating.RatingService.services;
import com.sumit.rating.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {
    public List<Rating> getAllRatings();
    public Rating getRatingById(String ratingId);
    public Rating addRating(Rating rating);
    public Rating updateRating(String ratingId, Rating rating);
    public void deleteRating(String ratingId);

    List<Rating> getRatingByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);


}
