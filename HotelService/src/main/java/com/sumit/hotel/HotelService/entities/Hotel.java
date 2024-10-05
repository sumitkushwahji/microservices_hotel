package com.sumit.hotel.HotelService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "micro_hotels")
@Data
public class Hotel {

    @Id
    private String hotelId;

    private String hotelName;

    private String hotelLocation;

    private String hotelAbout;
}
