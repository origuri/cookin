package com.example.cookin.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "order_item")
public class OrderItem {

    @Id @GeneratedValue
    private Long orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;

    @Column(nullable = false)
    private int orderPrice; // 주문 가격

    @Column(nullable = false)
    private int count;    // 주문 수량

}
