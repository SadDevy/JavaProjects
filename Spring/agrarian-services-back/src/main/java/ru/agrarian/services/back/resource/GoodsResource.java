package ru.agrarian.services.back.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.agrarian.services.back.dto.GoodsDto;
import ru.agrarian.services.back.service.GoodsStorageService;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/goods")
public class GoodsResource {

    private final GoodsStorageService goodsStorageService;

    @GetMapping
    public ResponseEntity<List<GoodsDto>> getAllGoods() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(goodsStorageService.getGoods());
    }

    @PostMapping
    public ResponseEntity<?> addGoods(@RequestBody GoodsDto goods) throws IOException {
        goodsStorageService.addGoods(goods);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
