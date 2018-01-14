package com.iinances.service;

import com.iinances.domain.Coin;
import com.iinances.repository.CoinRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CoinService {

    private final CoinRepository coinRepository;

    public CoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    public Mono<Coin> saveIfCoinExist(Coin coin){
        return coinRepository.findById(coin.getId()).switchIfEmpty(coinRepository.save(coin));
    }

}
