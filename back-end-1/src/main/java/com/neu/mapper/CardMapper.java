package com.neu.mapper;


import com.neu.domain.Card;

public interface CardMapper {
    void add(Card card);
    void deleteById(String studentid);
    void updateById(Card card);
}
