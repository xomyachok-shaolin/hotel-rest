package com.mephi.hotel.postRequestResponse.response;

import java.util.Date;
import java.util.List;

public class OrderResponse {
    private Long id;
    private Integer total_price;
    private String booking_room;
    private List<Integer> service;

    public OrderResponse(Long id, Integer total_price, String booking_room, List<Integer> service) {
        this.id = id;
        this.total_price = total_price;
        this.booking_room = booking_room;
        this.service = service;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTotal_price(Integer total_price) {
        this.total_price = total_price;
    }

    public void setBooking_room(String booking_room) {
        this.booking_room = booking_room;
    }

    public void setService(List<Integer> service) {
        this.service = service;
    }

    public Long getId() {
        return id;
    }

    public Integer getTotal_price() {
        return total_price;
    }

    public String getBooking_room() {
        return booking_room;
    }

    public List<Integer> getService() {
        return service;
    }

}
