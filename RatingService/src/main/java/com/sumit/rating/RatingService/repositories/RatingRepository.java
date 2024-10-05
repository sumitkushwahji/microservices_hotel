package com.sumit.rating.RatingService.repositories;

import com.sumit.rating.RatingService.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, String> {

//    custom Finder methods

    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);

}
