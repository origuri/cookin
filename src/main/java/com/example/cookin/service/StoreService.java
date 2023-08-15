package com.example.cookin.service;

import com.example.cookin.dto.store.StoreDeleteDto;
import com.example.cookin.dto.store.StoreUpdateDto;
import com.example.cookin.entity.Store;
import com.example.cookin.repository.MemberRepository;
import com.example.cookin.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StoreService {
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /*
     * store 수정하기
     * 접근권한 : admin
     * 파라미터 : storeUpdateDto
     * */
    public int modifyStoreByStoreUpdateDto(StoreUpdateDto storeUpdateDto) {
        String encodePass = memberRepository.findPasswordById(storeUpdateDto.getMemberUid());
        String rawPassword = storeUpdateDto.getPassword();
        boolean checkedPassword = bCryptPasswordEncoder.matches(rawPassword, encodePass);

        Store store = storeRepository.findById(storeUpdateDto.getId()).get();

        if(checkedPassword && store != null){
            store.toStoreUpdateEntity(storeUpdateDto);
            return 1;
        }else if(!checkedPassword) {
            return 2;
        }else{
            return 3;
        }

    }

    /*
     * store 삭제하기(폐점.)
     * 접근권한 admin
     * 파라미터 : storeId, password
     * */
    public int removeStoreByStoreId(Long storeId, StoreDeleteDto storeDeleteDto) {
        String encodePass = memberRepository.findPasswordById(storeDeleteDto.getMemberUid());
        String rawPassword = storeDeleteDto.getPassword();
        boolean checkedPassword = bCryptPasswordEncoder.matches(rawPassword, encodePass);

        if(checkedPassword){
            storeRepository.deleteById(storeId);
            return 1;
        }else{
            return 2;
        }
    }
}
