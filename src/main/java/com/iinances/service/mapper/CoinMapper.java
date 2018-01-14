package com.iinances.service.mapper;

import com.iinances.domain.Coin;
import com.iinances.service.dto.cc.CoinResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class CoinMapper{

    public Coin toEntity(CoinResponse dto) {
        Coin coin = new Coin();
        BeanUtils.copyProperties(dto, coin);
        return coin;
    }
}
