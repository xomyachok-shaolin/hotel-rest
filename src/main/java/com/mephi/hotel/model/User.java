package com.mephi.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(
        name = "users"
        , uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "user_login_unique", columnNames = "login")
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Long idUser;

    @NotBlank(message = "Поле \"login\" не может быть пустым")
    @Column(name = "login", length = 30)
    @Size(min = 1, max = 30)
    private String login;

    @NotBlank(message = "Поле \"last_name\" не может быть пустым")
    @Column(name = "last_name", length = 30)
    @Size(min = 1, max = 30)
    private String last_name;

    @NotBlank(message = "Поле \"first_name\" не может быть пустым")
    @Column(name = "first_name", length = 30)
    @Size(min = 1, max = 30)
    private String first_name;

    @NotBlank(message = "Поле \"phone_number\" не может быть пустым")
    @Column(name = "phone_number", length = 30)
    @Size(min = 10, max = 10)
    private String phone_number;

    @NotBlank(message = "Поле \"email\" не может быть пустым")
    @Column(name = "email", length = 60)
    @Size(min = 1, max = 60)
    private String email;

    @NotBlank(message = "Поле \"address\" не может быть пустым")
    @Column(name = "address", length = 500)
    @Size(min = 10, max = 500)
    private String address;

    @NotBlank(message = "Поле \"password\" не может быть пустым")
    @Column(name = "password", length = 255)
    @Size(min = 4, max = 255)
    private String password;

    @ManyToOne
    @JoinColumn(name = "idRole", nullable = false)
    private Role role;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Booking> bookings;

    public void setRole(Role role) {
        this.role = role;
    }
}
