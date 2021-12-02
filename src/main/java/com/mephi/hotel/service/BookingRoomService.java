package com.mephi.hotel.service;

import com.mephi.hotel.model.Booking;
import com.mephi.hotel.model.BookingRoom;
import com.mephi.hotel.repository.BookingRoomRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class BookingRoomService {
    private final BookingRoomRepository bookingRoomRepository;

    public BookingRoomService(BookingRoomRepository bookingRoomRepository) {
        this.bookingRoomRepository = bookingRoomRepository;
    }

    public Stream<BookingRoom> getAllBooking() {
        return bookingRoomRepository.findAll().stream();
    }

    public BookingRoom getBookingRoomById(Long id) {return bookingRoomRepository.findById(id).get();
    }

    public void save(BookingRoom bookingRoom) {
        bookingRoomRepository.save(bookingRoom);
    }
}
