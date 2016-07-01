package com.sishuok.spring4.entity;

import com.sishuok.spring4.group.First;
import com.sishuok.spring4.group.Second;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * User2.java
 *
 * @author congye
 * @create 2016/07/01 3:44 PM
 */
@GroupSequence({First.class, Second.class, User2.class})
public class User2 implements Serializable {

    private Long id;

    @Length(min = 5, max = 20, message = "{user.name.length.illegal}", groups = {First.class})
    @Pattern(regexp = "[a-zA-Z]{5,20}", message = "{user.name.illegal}", groups = {Second.class})
    private String name;

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
