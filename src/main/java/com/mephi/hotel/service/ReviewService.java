package com.mephi.hotel.service;

import com.mephi.hotel.model.Review;
import com.mephi.hotel.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Stream<Review> getAllReview() {
        return reviewRepository.findAll().stream();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).get();
    }

    public void save(Review review) {
        reviewRepository.save(review);
    }
}
