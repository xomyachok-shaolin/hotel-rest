package com.mephi.hotel.repository;

import com.mephi.hotel.model.Payment;
import com.mephi.hotel.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
}
