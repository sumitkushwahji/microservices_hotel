package com.sumit.user.service.entities;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Rating {
    private String ratingId;

    private String hotelId;

    private String userId;

    private int rating;

    private String feedback;

    private Hotel hotel;
}
