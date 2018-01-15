package com.iinances.service.mapper;

import com.iinances.domain.ExchangeRate;
import com.iinances.service.dto.fixer.ExchangeResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExchangeMapper {

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<ExchangeRate> toEntity(ExchangeResponse dto) {
        LocalDate dateCreated = LocalDate.parse(dto.getDate(), dateFormat);
        return dto.getRates().entrySet().stream()
                .map(rate -> new ExchangeRate(rate.getKey(), rate.getValue(), dateCreated))
                .collect(Collectors.toList());
    }

}
