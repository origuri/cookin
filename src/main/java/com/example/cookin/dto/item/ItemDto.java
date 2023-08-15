package com.example.cookin.dto.item;

import com.example.cookin.entity.Item;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ItemDto {

    private String name;

    private String origin; // 원산지

    private String unit; // 단위

    private int price;

    private int quantity;

    private String notice; // 특이사항 -> 필수아님

    private String keep; //보관방법

    private String itemStatus; // 구매가능 여부

    public static ItemDto toSearchItemDto(Item item){
        return ItemDto.builder()
                .name(item.getName())
                .origin(item.getOrigin())
                .unit(item.getUnit())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .notice(item.getNotice())
                .keep(item.getKeep())
                .itemStatus(item.getItemStatus())
                .build();
    }

}
