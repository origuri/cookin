package com.example.cookin.entity;


import com.example.cookin.dto.item.ItemInsertDto;
import com.example.cookin.dto.item.ItemUpdateDto;
import com.example.cookin.entity.enums.Status;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Item extends Base {

    @Id @GeneratedValue
    private Long itemId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String origin; // 원산지

    @Column(nullable = false)
    private String unit; // 단위

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;

    private String notice; // 특이사항 -> 필수아님

    @Column(nullable = false)
    private String itemStatus; // 구매가능 여부

    private String keep; // 보관방법

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;


    public static Item toInsertItemEntity(ItemInsertDto itemInsertDto){
        return Item.builder()
                .name(itemInsertDto.getName())
                .origin(itemInsertDto.getOrigin())
                .unit(itemInsertDto.getUnit())
                .price(itemInsertDto.getPrice())
                .quantity(itemInsertDto.getQuantity())
                .notice(itemInsertDto.getNotice())
                .itemStatus(itemInsertDto.getItemStatus())
                .keep(itemInsertDto.getKeep())
                .category(itemInsertDto.getCategory())
                .build();
    }

    public void toUpdateItemEntity(ItemUpdateDto itemUpdateDto){
        this.name = itemUpdateDto.getName();
        this.origin = itemUpdateDto.getOrigin();
        this.unit = itemUpdateDto.getUnit();
        this.price = itemUpdateDto.getPrice();
        this.quantity = itemUpdateDto.getQuantity();
        this.notice = itemUpdateDto.getNotice();
        this.itemStatus = itemUpdateDto.getItemStatus();
        this.keep = itemUpdateDto.getKeep();
        this.category = itemUpdateDto.getCategory();
    }





}
