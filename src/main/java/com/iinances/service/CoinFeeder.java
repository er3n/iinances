package com.iinances.service;

import com.iinances.domain.Coin;
import com.iinances.repository.CoinRepository;
import com.iinances.service.constant.Currency;
import com.iinances.service.dto.cc.CoinListResponse;
import com.iinances.service.mapper.CoinMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Component
public class CoinFeeder {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private WebClient client;
    private final CoinRepository coinRepository;
    private final CoinMapper coinMapper;

    public CoinFeeder(CoinRepository coinRepository, CoinMapper coinMapper) {
        this.coinRepository = coinRepository;
        this.coinMapper = coinMapper;
    }

    @PostConstruct
    public void init() {
        this.client = WebClient
                .create("https://www.cryptocompare.com")
                .mutate()
                .build();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run(ApplicationEvent applicationEvent) {

        client
                .get()
                .uri("/api/data/coinlist/")
                .retrieve()
                .bodyToMono(CoinListResponse.class)
                .filter(response -> response.getResponse().equals("Success"))
                .flatMapMany(coinListResponse -> Flux.fromIterable(coinListResponse.getCoinMap().values()))
                .flatMap(coinResponse -> Mono.just(coinMapper.toEntity(coinResponse)))
                .flatMap(coin -> coinRepository.findById(coin.getId()).switchIfEmpty(coinRepository.save(coin)))
                .subscribe(exchange -> logger.info(exchange.toString()));


    }

}