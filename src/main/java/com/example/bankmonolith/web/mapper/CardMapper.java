package com.example.bankmonolith.web.mapper;

import com.example.bankmonolith.domain.Card;
import com.example.bankmonolith.web.model.CardDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = DateMapper.class,unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface CardMapper {
    CardDto cardToCardDto(Card card);
    Card cardDtoToCard(CardDto cardDto);
}
