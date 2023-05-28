package ru.agrarian.services.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.agrarian.services.back.domain.Goods;
import ru.agrarian.services.back.dto.GoodsDto;
import ru.agrarian.services.back.repository.GoodsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsStorageService {

    private final GoodsRepository goodsRepository;

    @Transactional(readOnly = true)
    public List<GoodsDto> getGoods() {
        return goodsRepository.findAll().stream()
                .map(GoodsDto::new)
                .toList();
    }

    @Transactional
    public void addGoods(GoodsDto goods) {
        goodsRepository.save(new Goods(goods));
    }

    @Transactional
    public void removeGoods(long id) {
        Goods goods = goodsRepository.findById(id);
    }
}
