package com.mephi.hotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Table(name = "review")
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id", nullable = false)
    @JsonBackReference
    Booking booking;

    @NotBlank(message = "Поле \"description\" не может быть пустым")
    @Column(name = "description", length = 500)
    @Size(min = 1, max = 500)
    private String description;

    @NotBlank(message = "Поле \"title\" не может быть пустым")
    @Column(name = "title", length = 30)
    @Size(min = 1, max = 30)
    private String title;

    @NotNull(message = "Поле \"score\" не может быть пустым")
    @Column(name = "score")
    @Range(min=0, max=10)
    private Integer score;

}
