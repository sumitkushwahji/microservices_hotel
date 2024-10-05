package com.sumit.hotel.HotelService.services;

import com.sumit.hotel.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {
    public List<Hotel> getAllHotels();
    public Hotel getHotelById(String hotelId);
    public Hotel addHotel(Hotel hotel);
    public Hotel updateHotel(String hotelId, Hotel hotel);
    public void deleteHotel(String hotelId);
}
