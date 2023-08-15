package com.example.cookin.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Category extends Base{

    @Id @GeneratedValue
    private Long categoryId;

    private String large; // 대분류

    private String mid; // 중분류



}
