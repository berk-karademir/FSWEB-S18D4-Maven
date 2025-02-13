package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import com.workintech.s18d1.util.BurgerValidation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class BurgerDaoImpl implements BurgerDao {

    private final EntityManager entityManager;


    public BurgerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public List<Burger> findAll() {

        TypedQuery<Burger> query = entityManager.createQuery("SELECT b from Burger b", Burger.class);
        if(query==null) {
            throw new BurgerException("Can not find any burger", HttpStatus.NOT_FOUND);
        }
        return query.getResultList();
    }

    @Override
    public Burger findById(Long id) {
        BurgerValidation.checkId(id);
        Burger foundBurger = entityManager.find(Burger.class, id);
        if(foundBurger == null) {
            throw new BurgerException("Can not found any burger with id: " + id, HttpStatus.NOT_FOUND);
        }
        return foundBurger;
    }


    @Transactional
    @Override
    public Burger update(Burger burger) {

        return entityManager.merge(burger);
    }

    @Override
    public Burger remove(Long id) {
        BurgerValidation.checkId(id);
        Burger foundBurger = findById(id);
        entityManager.remove(foundBurger);
        return foundBurger;
    }

    @Override
    public List<Burger> findByPrice(Double price) {
        BurgerValidation.checkPrice(price);
        TypedQuery<Burger> foundList = entityManager.createQuery("SELECT b FROM Burger b WHERE b.price > :price ORDER BY b.price DESC", Burger.class);
        foundList.setParameter("price", price);
        return foundList.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        BurgerValidation.checkBreadType(breadType);
        TypedQuery<Burger> foundList = entityManager.createQuery("SELECT b FROM Burger b WHERE b.breadType = :breadType ORDER BY b.name DESC", Burger.class);
        foundList.setParameter("breadType", breadType);
        return foundList.getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        BurgerValidation.checkContents(content);
        TypedQuery<Burger> foundList = entityManager.createQuery("SELECT b FROM Burger b WHERE b.contents LIKE CONCAT('%',:content,'%')  ORDER BY b.name", Burger.class);
        foundList.setParameter("content", content);
        return foundList.getResultList();
    }
}