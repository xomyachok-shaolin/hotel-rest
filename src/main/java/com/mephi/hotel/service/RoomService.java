package com.mephi.hotel.service;

import com.mephi.hotel.model.*;
import com.mephi.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Stream<Room> getAllRoom() {
        return roomRepository.findAll().stream();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).get();
    }

    public List<Room> getAllUnavailableRoom(Date start_date, Date end_date) {
        return roomRepository.getAllUnavailableRoom(start_date, end_date);
    }
}
