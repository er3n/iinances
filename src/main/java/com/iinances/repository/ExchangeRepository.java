package com.iinances.repository;

import com.iinances.domain.Coin;
import com.iinances.domain.ExchangeRate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ExchangeRepository extends ReactiveMongoRepository<ExchangeRate, String>{
}
