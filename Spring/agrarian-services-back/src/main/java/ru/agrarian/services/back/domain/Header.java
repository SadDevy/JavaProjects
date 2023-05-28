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
@Table(name = "headers")
public class Header {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "headers_id_seq")
    private long id;

    @Lob
    @Column(name = "logo")
    private byte[] logo;

    @Lob
    @Column(name = "background")
    private byte[] background;

    @Column(name = "address", length = 250)
    private String address;

    @Lob
    @Column(name = "address_url")
    private String addressUrl;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "active")
    private boolean active;
}
