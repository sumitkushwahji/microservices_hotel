package com.sumit.hotel.HotelService.services.impl;

import com.sumit.hotel.HotelService.entities.Hotel;
import com.sumit.hotel.HotelService.exceptions.ResourceNotFoundExceptions;
import com.sumit.hotel.HotelService.repositories.HotelRepository;
import com.sumit.hotel.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundExceptions("Hotel not found with ID: " + hotelId));
    }

    @Override
    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel updateHotel(String hotelId, Hotel hotel) {
        return null;
    }

    @Override
    public void deleteHotel(String hotelId) {
        hotelRepository.deleteById(hotelId);

    }
}
