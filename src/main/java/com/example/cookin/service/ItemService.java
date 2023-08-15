package com.example.cookin.service;

import com.example.cookin.dto.item.ItemDto;
import com.example.cookin.entity.Item;
import com.example.cookin.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;

    /*public List<ItemDto> findItemDtoListByItemStatus(String itemStatus) {
        List<Item> itemList = itemRepository.findAllByItemStatus(itemStatus);
        List<ItemDto> itemDTOList = new ArrayList<>();

        for (Item item : itemList) {
            ItemDto itemDto = ItemDto.toSearchItemDto(item);
            itemDTOList.add(itemDto);
        }
        return itemDTOList;
    }*/

    public List<ItemDto> findItemDtoItemNameAndCategory(String itemStatus, String large, String mid, String name) {
        List<Item> itemList = itemRepository.findAllByItemNameAndCategory(itemStatus,large,mid,name);
        List<ItemDto> itemDTOList = new ArrayList<>();

        for (Item item : itemList) {
            ItemDto itemDto = ItemDto.toSearchItemDto(item);
            itemDTOList.add(itemDto);
        }
        return itemDTOList;
    }
}
