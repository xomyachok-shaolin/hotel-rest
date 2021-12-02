package com.mephi.hotel.repository;

import com.mephi.hotel.model.Booking;
import com.mephi.hotel.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
}
