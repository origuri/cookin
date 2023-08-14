package com.example.cookin.repository;

import com.example.cookin.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query("SELECT s FROM Store s WHERE s.storeName LIKE %:storeName%")
    List<Store> findByStoreName(@Param("storeName") String storeName);
}
