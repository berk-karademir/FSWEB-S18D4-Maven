package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;

import java.util.List;
import java.util.Optional;

public interface BurgerDao {

    Burger save(Burger burger);

    List<Burger> findAll();

    Burger findById(Long id);

    Burger update(Burger burger);

    Burger remove(Long id);

    List<Burger> findByPrice(Double price);

    List<Burger> findByBreadType(BreadType breadType);

    List<Burger> findByContent(String content);


}