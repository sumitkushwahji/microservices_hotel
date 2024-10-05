package com.sumit.rating.RatingService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "micro_rating")
@Data
public class Rating {

    @Id
    private String ratingId;

    private String hotelId;

    private String userId;

    private int rating;

    private String feedback;

}
