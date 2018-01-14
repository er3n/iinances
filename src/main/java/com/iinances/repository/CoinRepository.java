package com.iinances.repository;

import com.iinances.domain.Coin;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CoinRepository extends ReactiveMongoRepository<Coin, Long>{
}
