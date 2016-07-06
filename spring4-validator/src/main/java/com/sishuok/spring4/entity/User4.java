package com.sishuok.spring4.entity;

import com.sishuok.spring4.validator.Forbidden;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * User4.java
 *
 * @author congye
 * @create 2016/07/06 2:35 PM
 */
public class User4 implements Serializable {

    @NotNull
    private Long id;

    @NotBlank
    @Forbidden
    private String name;

    @NotBlank
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
