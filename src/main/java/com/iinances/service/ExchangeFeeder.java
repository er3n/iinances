package com.iinances.service;

import com.iinances.repository.ExchangeRepository;
import com.iinances.service.dto.cc.CoinListResponse;
import com.iinances.service.dto.fixer.ExchangeResponse;
import com.iinances.service.mapper.CoinMapper;
import com.iinances.service.mapper.ExchangeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;


@Component
public class ExchangeFeeder {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private WebClient client;
    private final ExchangeRepository exchangeRepository;
    private final ExchangeMapper exchangeMapper;

    public ExchangeFeeder(ExchangeRepository exchangeRepository, ExchangeMapper exchangeMapper) {
        this.exchangeRepository = exchangeRepository;
        this.exchangeMapper = exchangeMapper;
    }

    @PostConstruct
    public void init() {
        this.client = WebClient
                .create("https://api.fixer.io")
                .mutate()
                .build();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run(ApplicationEvent applicationEvent) {

        client
                .get()
                .uri("/latest?base=USD")
                .retrieve()
                .bodyToMono(ExchangeResponse.class)
                .flatMapMany(exchangeResponse -> Flux.fromIterable(exchangeMapper.toEntity(exchangeResponse)))
                .flatMap(exchangeRate -> exchangeRepository.findById(exchangeRate.getId()).switchIfEmpty(exchangeRepository.save(exchangeRate)))
                .subscribe(exchange -> logger.info(exchange.toString()));


    }

}