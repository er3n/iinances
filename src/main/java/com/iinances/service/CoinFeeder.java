package com.iinances.service;

import com.iinances.domain.Coin;
import com.iinances.service.dto.cc.CoinListResponse;
import com.iinances.service.mapper.CoinMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@Component
public class CoinFeeder {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final WebClient webClient;
    private final CoinService coinService;
    private final CoinMapper coinMapper;

    public CoinFeeder(WebClient webClient, CoinService coinService, CoinMapper coinMapper) {
        this.webClient = webClient;
        this.coinService = coinService;
        this.coinMapper = coinMapper;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run(ApplicationEvent applicationEvent) {

        webClient
                .get()
                .uri("/api/data/coinlist/")
                .retrieve()
                .bodyToMono(CoinListResponse.class)
                .filter(response -> response.getResponse().equals("Success"))
                .map(coinListResponse ->
                        fetchCoins(coinListResponse)
                        .subscribe()
                )
                .subscribe(System.out::println);


    }

    public Flux<Coin> fetchCoins(CoinListResponse coinListResponse) {
        logger.info(coinListResponse.toString());
        return Flux.fromIterable(coinListResponse.getCoinMap().values())
                .flatMap(coinResponse -> coinService.saveIfCoinExist(coinMapper.toEntity(coinResponse)));
    }

}