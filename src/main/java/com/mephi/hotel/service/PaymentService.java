package com.mephi.hotel.service;

import com.mephi.hotel.model.Booking;
import com.mephi.hotel.model.Payment;
import com.mephi.hotel.repository.BookingRepository;
import com.mephi.hotel.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Stream<Payment> getAllPayment() {
        return paymentRepository.findAll().stream();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).get();
    }

    public void save(Payment payment) {
        paymentRepository.save(payment);
    }
}
