package com.mephi.hotel.repository;

import com.mephi.hotel.model.Booking;
import com.mephi.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
    @Query(value = "select distinct r from Booking b LEFT JOIN BookingRoom br on br.id = br.id left join Room r on br.id = r.id " +
            "where ((b.end_date > ?1 and b.end_date <= ?2) or (b.start_date >= ?1 and b.start_date < ?2)) and b.status = 'Зарегистрировано'")
    public List<Room> getAllUnavailableRoom(@Param("startDate") Date startDate, @Param("endDate")Date endDate);

}
