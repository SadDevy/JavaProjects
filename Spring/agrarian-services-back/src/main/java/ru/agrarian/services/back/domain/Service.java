package ru.agrarian.services.back.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "services_id_seq")
    private long id;

    @Column(name = "name", length = 250)
    private String name;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "price")
    private double price;

    @Lob
    @Column(name = "description")
    private String description;
}
