package com.example.cookin.controller;

import com.example.cookin.dto.store.StoreInsertDto;
import com.example.cookin.dto.store.StoreUpdateDto;
import com.example.cookin.entity.Store;
import com.example.cookin.repository.StoreRepository;
import com.example.cookin.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cookin")
@Slf4j
/*
* 프렌차이즈 점포 추가, 삭제, 수정, 전체 조회, 조회 하는 기능.
* */
public class StoreController {

    private final StoreService storeService;
    private final StoreRepository storeRepository;

    /*
    * 전체 프렌차이즈 매장 가져오는 로직
    * 파라미터 : x
    * */
    @GetMapping("/stores")
    public ResponseEntity<?> storeList(){
        List<Store> storeList = storeRepository.findAll();
        if(storeList.size()>0){
            return new ResponseEntity<>(storeList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("업장 등록된 게 없네요", HttpStatus.NOT_FOUND);
        }
    }

    /*
    * 이름으로 특정 프렌차이즈 매장 가져오는 로직
    * 파라미터 : storeName의 아웃백 xx점
    * */
    @GetMapping("/stores/{storeName}")
    public ResponseEntity<?> storeListByStoreName(@PathVariable("storeName") String storeName){
        List<Store> storeList = storeRepository.findByStoreName(storeName);
        if(storeList.size()>0){
            return new ResponseEntity<>(storeList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("해당 이름의 업장은 없네요", HttpStatus.NOT_FOUND);
        }
    }

    /*
    * store 등록하기
    * 접근 권한 admin
    * 파라미터 : storeInsertDto
    * */
    //@Secured("ADMIN")
    @PostMapping("store/write")
    public ResponseEntity<?> storeAddByStoreInsertDto(@RequestBody StoreInsertDto storeInsertDto){
        Store store = Store.toStoreInsertEntity(storeInsertDto);
        Long id = storeRepository.save(store).getId();
        if(id != null){
            return new ResponseEntity<>("등록 성공", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("등록실패", HttpStatus.BAD_REQUEST);
        }
    }

    /*
    * store 수정하기
    * 접근권한 : admin
    * 파라미터 : storeUpdateDto
    * */
    //@Secured("ADMIN")
    @PutMapping("store/{storeId}")
    public ResponseEntity<?> storeModifyByStoreUpdateDto(@RequestBody StoreUpdateDto storeUpdateDto,
                                                         @PathVariable("storeId") Long storeId){
        storeUpdateDto.setId(storeId);
        int result = 0;
        result = storeService.modifyStoreByStoreUpdateDto(storeUpdateDto);
        if(result == 1){
            return new ResponseEntity<>("수정완료", HttpStatus.OK);
        }else if(result == 2){
            return new ResponseEntity<>("수정실패", HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>("수정실패, 업장이 없습니다.", HttpStatus.NOT_FOUND);
        }
    }
}
