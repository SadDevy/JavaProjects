package ru.agrarian.services.back.domain;

import jakarta.persistence.*;
import lombok.*;
import ru.agrarian.services.back.dto.GoodsDto;

import java.lang.reflect.Array;
import java.util.Arrays;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_id_seq")
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

    public Goods(GoodsDto dto) {
        this.name = dto.getName();
        this.image = Arrays.copyOf(dto.getImage(), dto.getImage().length);
        this.price = dto.getPrice();
        this.description = dto.getDescription();
    }
}
