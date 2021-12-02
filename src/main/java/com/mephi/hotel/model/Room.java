package com.mephi.hotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(
        name = "room",
        uniqueConstraints = {
                @UniqueConstraint(name = "room_number_unique", columnNames = "room_number")}
)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Поле \"room_number\" не может быть пустым")
    @Column(name = "room_number", length = 30)
    @Size(min = 1, max = 30)
    private String room_number;

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    List<BookingRoom> detail = new ArrayList<>();

    @NotNull(message = "Поле \"floor\" не может быть пустым")
    @Column(name = "floor")
    @Range(min=1, max=100)
    private Integer floor;

    @NotNull(message = "Поле \"price\" не может быть пустым")
    @Column(name = "price")
    @Range(min=0)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "idType")
    Type type;


}