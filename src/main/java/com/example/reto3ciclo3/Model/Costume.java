package com.example.reto3ciclo3.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "costume")
public class Costume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String brand;
    private Integer year;
    private String description;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonIgnoreProperties("costumes")
    private Category category;

    @OneToMany(mappedBy = "costume")
    @JsonIgnoreProperties({"costume", "client"})
    private List<Message> messages;

    @OneToMany(mappedBy = "costume")
    @JsonIgnoreProperties({"costume", "client"})
    private List<Reservation> reservations;

}
