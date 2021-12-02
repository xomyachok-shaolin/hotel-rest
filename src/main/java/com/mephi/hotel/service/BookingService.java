package com.mephi.hotel.service;

import com.mephi.hotel.model.Booking;
import com.mephi.hotel.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Stream<Booking> getAllBooking() {
        return bookingRepository.findAll().stream();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).get();
    }

    public void save(Booking booking) {
        bookingRepository.save(booking);
    }
}
