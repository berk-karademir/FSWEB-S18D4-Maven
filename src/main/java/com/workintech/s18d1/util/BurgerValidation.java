package com.workintech.s18d1.util;

import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import org.springframework.http.HttpStatus;

public class BurgerValidation {



    public static void checkName(String name) {
        if(name == null || name.isEmpty()) {
            throw new BurgerException("Burger name can not be null or empty !", HttpStatus.BAD_REQUEST);
        }
    }


    public static void checkId(Long id) {
        if(id == null ) {
            throw new BurgerException("ID can not be null or empty !", HttpStatus.BAD_REQUEST);
        }

        if(id < 0) {
            throw new BurgerException("ID can not be lower than 0 !", HttpStatus.BAD_REQUEST);

        }
    }

    public static void checkBody(Burger burger) {
        if (burger == null) {
            throw new BurgerException("Data can not be null or empty",HttpStatus.BAD_REQUEST);
        }
    }




}
