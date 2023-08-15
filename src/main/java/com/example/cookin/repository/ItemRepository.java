package com.example.cookin.repository;

import com.example.cookin.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
   /* @Query("SELECT i FROM Item i WHERE i.itemStatus = :itemStatus")
    List<Item> findAllByItemStatus(@Param("itemStatus") String itemStatus);
*/
   @Query("SELECT i " +
            "FROM Item i join fetch i.category c " +
            "WHERE (:large is null OR c.large = :large) " +
            "AND (:mid is null OR c.mid = :mid) " +
            "AND (:name is null OR i.name LIKE %:name%) " +
            "AND i.itemStatus = :itemStatus")
    List<Item> findAllByItemNameAndCategory(@Param("itemStatus") String itemStatus ,
                                            @Param("large") String large,
                                            @Param("mid") String mid,
                                            @Param("name") String name);
}
