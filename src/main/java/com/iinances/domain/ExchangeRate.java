package com.iinances.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Document(collection = "exchange_rate")
public class ExchangeRate {

    @Id
    private String id;
    @NotNull
    @Field("rate")
    private Double rate;
    @NotNull
    @Field("create_date")
    private LocalDate createDate;

    public ExchangeRate() {
    }

    public ExchangeRate(String id, @NotNull Double rate, @NotNull LocalDate createDate) {
        this.id = id;
        this.rate = rate;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id='" + id + '\'' +
                ", rate=" + rate +
                ", createDate=" + createDate +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
