package com.mephi.hotel.postRequestResponse.response;

import java.util.Date;
import java.util.List;

public class BookingResponse {
    private Long id;
    private Date start_date;
    private Date end_date;
    private String status;
    private String user;
    private List<Integer> room;

    public BookingResponse(Long id, Date start_date, Date end_date, String status, String user, Date check_in, Date check_out, List<Integer> room) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.user = user;
        this.room = room;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setRoom(List<Integer> room) {
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public String getStatus() {
        return status;
    }

    public String getUser() {
        return user;
    }

    public List<Integer> getRoom() {
        return room;
    }

}
