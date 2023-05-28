package ru.agrarian.services.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.agrarian.services.back.domain.Goods;

import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDto {

    private String name;
    private byte[] image;
    private double price;
    private String description;

    public GoodsDto(Goods goods) {
        this.name = goods.getName();
        this.image = Arrays.copyOf(goods.getImage(), goods.getImage().length);
        this.price = goods.getPrice();
        this.description = goods.getDescription();
    }
}
