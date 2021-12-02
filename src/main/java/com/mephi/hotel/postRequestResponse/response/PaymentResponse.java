package com.mephi.hotel.postRequestResponse.response;

import java.util.Date;
import java.util.List;

public class PaymentResponse {
    private Long id;
    private String booking_id;
    private String payment_type;
    private Integer amount;

    public PaymentResponse(Long id, String booking_id, String payment_type, Integer amount) {
        this.id = id;
        this.booking_id = booking_id;
        this.payment_type = payment_type;
        this.amount = amount;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setBooking(String booking) {
        this.booking_id = booking;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getBooking() {
        return booking_id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public Integer getAmount() {
        return amount;
    }
}
