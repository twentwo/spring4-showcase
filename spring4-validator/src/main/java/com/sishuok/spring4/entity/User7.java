package com.sishuok.spring4.entity;

import com.sishuok.spring4.validator.Composition;

import java.io.Serializable;

/**
 * User7.java
 *
 * @author congye
 * @create 2016/07/06 6:38 PM
 */
public class User7 implements Serializable {

    @Composition
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
