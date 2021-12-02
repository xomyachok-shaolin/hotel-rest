package com.mephi.hotel.postRequestResponse.response;

public class ReviewResponse {
    private Long id;
    private Integer score;
    private String booking_id;
    private String title;
    private String description;

    public ReviewResponse(Long id, Integer score, String booking_id, String title, String description) {
        this.id = id;
        this.score = score;
        this.booking_id = booking_id;
        this.title = title;
        this.description = description;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Integer getScore() {
        return score;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
