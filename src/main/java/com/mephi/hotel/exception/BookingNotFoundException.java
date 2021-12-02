package com.mephi.hotel.exception;

public class BookingNotFoundException extends Exception {
    private long booking_id;

    public BookingNotFoundException(long booking_id) {
        super(String.format("Booking is not found with id : '%s'", booking_id));
    }
}
