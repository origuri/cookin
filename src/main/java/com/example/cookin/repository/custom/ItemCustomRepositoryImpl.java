package com.example.cookin.repository.custom;

import com.example.cookin.entity.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ItemCustomRepositoryImpl implements ItemCustomRepository{

    private final EntityManager em;

    @Override
    public List<Item> findAllByItemNameAndCategory(String itemStatus, String name, String large, String mid) {
        String jpql = "SELECT i " +
                "FROM Item i, Category c " +
                "WHERE i.category.CategoryId = c.CategoryId ";

        if (large != null) {
            jpql += "AND c.large = :large ";
        }

        if (mid != null) {
            jpql += "AND c.mid = :mid ";
        }

        if (name != null) {
            jpql += "AND i.name LIKE :name ";
        }

        jpql += "AND i.itemStatus = :itemStatus";

        TypedQuery<Item> query = em.createQuery(jpql, Item.class);

        if (large != null) {
            query.setParameter("large", large);
        }

        if (mid != null) {
            query.setParameter("mid", mid);
        }

        if (name != null) {
            query.setParameter("name", "%" + name + "%");
        }

        query.setParameter("itemStatus", itemStatus);

        return query.getResultList();
    }
}

