package com.mephi.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(
        name = "type",
        uniqueConstraints = {
                @UniqueConstraint(name = "title_unique", columnNames = "title")}
)
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idType")
    private Long id;

    @NotBlank(message = "Поле \"title\" не может быть пустым")
    @Column(name = "title", length = 30)
    @Size(min = 1, max = 30)
    private String title;

    @OneToMany(mappedBy = "type", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Room> rooms;
}
