package com.sumit.hotel.HotelService.payload;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse {
    private String message;
    private Boolean success;
    private HttpStatus status;
}
