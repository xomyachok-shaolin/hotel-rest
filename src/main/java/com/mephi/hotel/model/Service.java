package com.mephi.hotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(
        name = "service",
        uniqueConstraints = {
                @UniqueConstraint(name = "service_unique", columnNames = "title")}
)
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Поле \"type\" не может быть пустым")
    @Column(name = "type", length = 30)
    @Size(min = 1, max = 15)
    private String type;

    @NotBlank(message = "Поле \"title\" не может быть пустым")
    @Column(name = "title", length = 30)
    @Size(min = 1, max = 30)
    private String title;

    @NotNull(message = "Поле \"price\" не может быть пустым")
    @Column(name = "price")
    @Range(min=0)
    private Integer price;


    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "services", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Order> order = new ArrayList<>();

}
