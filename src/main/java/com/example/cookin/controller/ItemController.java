package com.example.cookin.controller;

import com.example.cookin.dto.item.ItemInsertDto;
import com.example.cookin.dto.item.ItemSearchDto;
import com.example.cookin.entity.Item;
import com.example.cookin.repository.ItemRepository;
import com.example.cookin.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cookin")
@Slf4j
/*
* 접근 권한 admin - 등록, 수정, 삭제
* 접근 권한 user -  전체 조회, 이름 조회
* */
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    /*
    * 전제 item 조회.
    * 파라미터 : x
    * */
    @GetMapping("/items")
    public ResponseEntity<?> itemList(@RequestBody ItemSearchDto itemSearchDto){
        List<Item> itemList = itemRepository.findAllByItemStatus(itemSearchDto.getItemStatus());
        if(itemList.size() > 0){
            return new ResponseEntity<>(itemList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("item 목록이 없습니다.", HttpStatus.NOT_FOUND);
        }
    }
    /*
    * item 이름으로 조회
    * 파라미터 : itemName
    * */
    @GetMapping("/items/search")
    public ResponseEntity<?> itemListByItemName(@RequestBody ItemSearchDto itemSearchDto){
        List<Item> itemList = itemRepository.findAllByItemNameAndCategory(itemSearchDto.getItemStatus(),itemSearchDto.getName(),itemSearchDto.getLarge(),itemSearchDto.getMid());
        if(itemList.size()>0){
            return new ResponseEntity<>(itemList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("item 목록이 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    /*
    * item 등록
    * 파라미터 : itemInsertDto
    * 권한 : ADMIN
    */
    @PostMapping("/item/write")
    public ResponseEntity<?> itemAddByItemInsertDto(@RequestBody ItemInsertDto itemInsertDto){
        Item item = Item.toInsertItemEntity(itemInsertDto);
        Long itemId = itemRepository.save(item).getItemId();
        if(itemId > 0){
            return new ResponseEntity<>("상품 등록 성공", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("상품 등록 실패", HttpStatus.BAD_REQUEST);
        }
    }
}
