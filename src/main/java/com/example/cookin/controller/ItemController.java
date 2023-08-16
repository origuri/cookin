package com.example.cookin.controller;

import com.example.cookin.dto.item.ItemDto;
import com.example.cookin.dto.item.ItemInsertDto;
import com.example.cookin.dto.item.ItemSearchDto;
import com.example.cookin.dto.item.ItemUpdateDto;
import com.example.cookin.entity.Item;
import com.example.cookin.repository.ItemRepository;
import com.example.cookin.repository.custom.ItemCustomRepository;
import com.example.cookin.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cookin")
@Slf4j
/*
* 접근 권한 admin - 등록, 수정, 삭제  : /item/**
* 접근 권한 user -  전체 조회, 이름 조회 : /items
* */
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final ItemCustomRepository itemCustomRepository;

    /*
    * 전제 item 조회.
    * 파라미터 : x
    * */
    /*@GetMapping("/items")
    public ResponseEntity<?> itemList(@RequestBody ItemSearchDto itemSearchDto){
        log.info("이거나오나? ->{}",itemSearchDto.getItemStatus());

        List<ItemDto> itemDTOList = itemService.findItemDtoListByItemStatus(itemSearchDto.getItemStatus());

        if (!itemDTOList.isEmpty()) {
            return new ResponseEntity<>(itemDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("아이템 목록이 없습니다.", HttpStatus.NOT_FOUND);
        }
    }*/
    /*
    * item 전체조회
    * item 이름, 카테고리 대분류 , 중분류로 조회하기.
    * 파라미터 : itemStatus, large, mid, name
    * */
    @GetMapping("/items/search")
    public ResponseEntity<?> itemListByItemName(@RequestBody ItemSearchDto itemSearchDto){
        List<ItemDto> itemDTOList = itemService.findItemDtoItemNameAndCategory(itemSearchDto.getItemStatus(), itemSearchDto.getLarge(), itemSearchDto.getMid(),itemSearchDto.getName());


        if (!itemDTOList.isEmpty()) {
            return new ResponseEntity<>(itemDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("아이템 목록이 없습니다.", HttpStatus.NOT_FOUND);
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

    /*
    * item 수정 form으로 정보 전달, 단건 조회
    * 파라미터 : itemId
    * 접근권한 : admin
    * */
    @GetMapping("/item/{itemId}")
    public ResponseEntity<?> itemDetailByItemId(@PathVariable("itemId") Long itemId){
        ItemDto itemDto = itemService.findItemByItemId(itemId);
        if(itemDto != null){
            return new ResponseEntity<>(itemDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("불러오기 실패", HttpStatus.BAD_REQUEST);
        }
    }

    /*
    * item 수정
    * 파라미터 : itemUpdateDto
    * 접근권한 : admin
    * 수정 완료 후
    * */
    @PutMapping("/item/{itemId}")
    public ResponseEntity<?> itemModifyByItemUpdateDto(@PathVariable("itemId") Long itemId,
                                                       @RequestBody ItemUpdateDto itemUpdateDto) {
        itemUpdateDto.setItemId(itemId);

        int result = itemService.modifyItemByItemUpdateDto(itemUpdateDto);

        if (result == 1) {
            return new ResponseEntity<>("수정 성공", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("수정 실패", HttpStatus.BAD_REQUEST);
        }

    }


}
