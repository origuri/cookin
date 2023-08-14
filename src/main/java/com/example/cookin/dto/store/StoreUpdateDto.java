package com.example.cookin.dto.store;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class StoreUpdateDto {

    private Long id;
    @NotEmpty(message = "필수값")
    private String storeName; // 사업장 이름.
    @NotEmpty(message = "필수값")
    private String storeTel;  // 사업장 번호
    @NotEmpty(message = "필수값")
    private String city;
    @NotEmpty(message = "필수값")
    private String street;
    @NotEmpty(message = "필수값")
    private String zipCode;

}
