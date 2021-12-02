package com.mephi.hotel.controller;

import com.mephi.hotel.model.*;
import com.mephi.hotel.postRequestResponse.response.MessageResponse;
import com.mephi.hotel.postRequestResponse.response.ReviewResponse;
import com.mephi.hotel.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Transactional
@RestController
public class ReviewController {
    private final ReviewService reviewService;
    private final BookingService bookingService;

    public ReviewController(BookingService bookingService, ReviewService reviewService) {

        this.bookingService = bookingService;
        this.reviewService = reviewService;
    }

    @PostMapping(value = "/review/addReview", consumes = {"application/json"})
    public ResponseEntity<?> saveReview(@RequestBody ReviewResponse reviewResponse) {
        try {
            if (reviewResponse.getDescription() == null ||
                    reviewResponse.getScore() == null ||
                    reviewResponse.getTitle() == null)
                return ResponseEntity.badRequest().body(new MessageResponse("Не корректно составленный отзыв!"));
            Review review = new Review();
            review.setDescription(reviewResponse.getDescription());
            review.setTitle(reviewResponse.getTitle());
            review.setScore(reviewResponse.getScore());
            Booking booking = bookingService.getBookingById(Long.parseLong(reviewResponse.getBooking_id()));
            review.setBooking(booking);
            reviewService.save(review);

            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Отзыв успешно добавлен"));
        } catch (Exception ex) {
            String strEx = ex.getCause().getCause().getMessage();
            System.out.println("Ошибка! Подробнее: " + strEx);
            return ResponseEntity.badRequest().body(new MessageResponse(strEx));
        }
    }

    @PostMapping(value = "/review/{id}", consumes = {"application/json"})
    public ResponseEntity<?> editReview(@PathVariable Long id, @RequestBody ReviewResponse reviewResponse) {
        try {
            if (reviewResponse.getDescription() == null ||
                    reviewResponse.getScore() == null ||
                    reviewResponse.getTitle() == null)
                return ResponseEntity.badRequest().body(new MessageResponse("Во время редактирования отзыва произошла ошибка!"));

            Review review = reviewService.getReviewById(id);
            review.setDescription(reviewResponse.getDescription());
            review.setTitle(reviewResponse.getTitle());
            review.setScore(reviewResponse.getScore());
            reviewService.save(review);

            return ResponseEntity.status(HttpStatus.OK).body("Отзыв успешно отредактирован");
        } catch (Exception ex) {
            String strEx = ex.getCause().getCause().getMessage();
            System.out.println("Ошибка! Подробнее: " + strEx);
            return ResponseEntity.badRequest().body(new MessageResponse(strEx));
        }
    }

 }
