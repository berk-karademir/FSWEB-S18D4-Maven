package com.workintech.s18d1.util;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class BurgerValidation {


    public static void checkName(String name) {
        if (name == null || name.isEmpty()) {
            throw new BurgerException("Burger name can not be null or empty !", HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkPrice(Double price) {
        if (price == null || price.isInfinite() || price.isNaN()) {
            throw new BurgerException("Invalid burger price, check the price field !", HttpStatus.BAD_REQUEST);
        } else if (price <= 29.89) {
            throw new BurgerException("Burger price can be 29.90 minimum !", HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkBreadType(BreadType breadType) {
        if (breadType == null) {
            throw new BurgerException("BreadType cannot be null", HttpStatus.BAD_REQUEST);
        }
        boolean isValidBread = Arrays.stream(BreadType.values())
                .anyMatch(type -> type.name().equals(breadType.name()));
        if (!isValidBread) {
            throw new BurgerException("Invalid Bread Type value: " + breadType, HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkId(Long id) {
        if (id == null) {
            throw new BurgerException("ID can not be null or empty !", HttpStatus.BAD_REQUEST);
        }

        if (id < 0) {
            throw new BurgerException("ID can not be lower than 0 !", HttpStatus.BAD_REQUEST);

        }
    }


    public static void checkIsVegan(Boolean isVegan) {
        if (isVegan == null) {
            throw new BurgerException("Specify the burger if it is vegan or not by (true or false)!", HttpStatus.BAD_REQUEST);

        }
    }

    public static void checkContents(String content) {
        if (content == null || content.isEmpty()) {
            throw new BurgerException("Burger must has it contents ", HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkBody(Burger burger) {
        if (burger == null) {
            throw new BurgerException("Invisible burger ?? Burger of void ?? ", HttpStatus.BAD_REQUEST);
        }

        checkName(burger.getName());
        checkPrice(burger.getPrice());
        checkIsVegan(burger.getIsVegan());
        checkBreadType(burger.getBreadType());
        checkContents(burger.getContents());

    }


}
