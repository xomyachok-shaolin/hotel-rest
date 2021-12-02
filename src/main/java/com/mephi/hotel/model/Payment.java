package com.mephi.hotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date start_date;


    @Column(name = "payment_type", length = 16)
    @Size(min = 1, max = 16)
    private String payment_type;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id", nullable = false)
    @JsonBackReference
    Booking booking;

    @Column(name = "amount")
    @Range(min=0)
    private Integer amount;

}
