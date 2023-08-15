package com.example.cookin.repository.custom;

import com.example.cookin.entity.Item;

import java.util.List;

public interface ItemCustomRepository {
    List<Item> findAllByItemNameAndCategory(String itemStatus, String name, String large, String mid);
}
