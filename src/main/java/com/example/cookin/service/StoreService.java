package com.example.cookin.service;

import com.example.cookin.dto.store.StoreUpdateDto;
import com.example.cookin.entity.Store;
import com.example.cookin.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StoreService {
    private final StoreRepository storeRepository;

    /*
     * store 수정하기
     * 접근권한 : admin
     * 파라미터 : storeUpdateDto
     * */
    public int modifyStoreByStoreUpdateDto(StoreUpdateDto storeUpdateDto) {
        int result = 0;
        Store store = storeRepository.findById(storeUpdateDto.getId()).get();
        if(store != null){
            store.toStoreUpdateEntity(storeUpdateDto);
            return 1;
        }else{
            return 3;
        }
    }
}
