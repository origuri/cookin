package com.example.cookin.controller;

import com.example.cookin.auth.PrincipalDetails;
import com.example.cookin.dto.store.StoreDeleteDto;
import com.example.cookin.dto.store.StoreInsertDto;
import com.example.cookin.dto.store.StoreSearchDto;
import com.example.cookin.dto.store.StoreUpdateDto;
import com.example.cookin.entity.Store;
import com.example.cookin.repository.StoreRepository;
import com.example.cookin.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<?> storeList(@RequestBody StoreSearchDto storeSearchDto){

        List<Store> storeList = storeRepository.findAllByStoreStatus( storeSearchDto.getStoreStatus());
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
    @GetMapping("/stores/search")
    public ResponseEntity<?> storeListByStoreName( @RequestBody StoreSearchDto storeSearchDto){
        List<Store> storeList = storeRepository.findByStoreName(storeSearchDto.getStoreName(), storeSearchDto.getStoreStatus());
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
        log.info("status확인 ->{}",storeInsertDto.getStoreStatus());
        Store store = Store.toStoreInsertEntity(storeInsertDto);
        Long id = storeRepository.save(store).getId();
        if(id != null){
            return new ResponseEntity<>("등록 성공", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("등록실패", HttpStatus.BAD_REQUEST);
        }
    }

    /*
    * store 수정하기(휴점, 전화번호 변경 등)
    * 접근권한 : admin
    * 파라미터 : storeUpdateDto(이중 확인으로 admin password까지)
    * */
    //@Secured("ADMIN")
    @PutMapping("store/{storeId}")
    public ResponseEntity<?> storeModifyByStoreUpdateDto(@RequestBody StoreUpdateDto storeUpdateDto,
                                                         @PathVariable("storeId") Long storeId
                                                        // @AuthenticationPrincipal PrincipalDetails principalDetails
                                                         ){
        //storeUpdateDto.setMemberUid(principalDetails.getMemberId());
        storeUpdateDto.setMemberUid(6L);
        log.info("password 나오나? => {}",storeUpdateDto.getPassword());
        storeUpdateDto.setId(storeId);
        int result = 0;
        result = storeService.modifyStoreByStoreUpdateDto(storeUpdateDto);
        if(result == 1){
            return new ResponseEntity<>("수정완료", HttpStatus.OK);
        }else if(result == 2){
            return new ResponseEntity<>("수정실패 비번 확인", HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>("수정실패, 업장이 없습니다.", HttpStatus.NOT_FOUND);
        }
    }
    /*
    * store 삭제하기(폐점.)
    * 접근권한 admin
    * 파라미터 : storeId, password
    * */
    //@Secured("ADMIN")
    @DeleteMapping("/store/{storeId}")
    public ResponseEntity<?> storeRemoveByStoreId(@PathVariable("storeId") Long storeId,
                                                  @RequestBody StoreDeleteDto storeDeleteDto
                                                  //@AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        //storeDeleteDto.setMemberUid(principalDetails.getMemberId());
        storeDeleteDto.setMemberUid(4L);
        log.info("password 나오나? => {}",storeDeleteDto.getPassword());
        int result = storeService.removeStoreByStoreId(storeId, storeDeleteDto);
        if(result == 1){
            return new ResponseEntity<>("삭제완료", HttpStatus.OK);
        }else if(result == 2){
            return new ResponseEntity<>("삭제실패 비번 확인", HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>("삭제실패, 업장이 없습니다.", HttpStatus.NOT_FOUND);
        }
    }
}
