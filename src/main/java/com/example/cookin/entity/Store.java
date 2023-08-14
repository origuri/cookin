package com.example.cookin.entity;


import com.example.cookin.dto.store.StoreInsertDto;
import com.example.cookin.dto.store.StoreUpdateDto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Store {

    @Id @GeneratedValue
    @Column(name = "storeId")
    private Long id;
    @Column(nullable = false, unique = true)
    private String storeName; // 사업장 이름.
    @Column(nullable = false, unique = true)
    private String storeNum;   // 사업자 등록번호
    @Column(nullable = false)
    private String storeTel;  // 사업장 번호
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String zipCode;

    public static Store toStoreInsertEntity(StoreInsertDto storeInsertDto){
        return Store.builder()
                .storeName(storeInsertDto.getStoreName())
                .storeNum(storeInsertDto.getStoreNum())
                .storeTel(storeInsertDto.getStoreTel())
                .city(storeInsertDto.getCity())
                .street(storeInsertDto.getStreet())
                .zipCode(storeInsertDto.getZipCode())
                .build();

    }

    public void toStoreUpdateEntity(StoreUpdateDto storeUpdateDto){
        this.storeName = storeUpdateDto.getStoreName();
        this.storeTel = storeUpdateDto.getStoreTel();
        this.city = storeUpdateDto.getCity();
        this.street = storeUpdateDto.getStreet();
        this.zipCode = storeUpdateDto.getZipCode();
    }
}
