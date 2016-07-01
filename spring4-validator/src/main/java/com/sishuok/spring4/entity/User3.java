package com.sishuok.spring4.entity;

import com.sishuok.spring4.group.First;
import com.sishuok.spring4.group.Second;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.ConvertGroup;
import java.io.Serializable;

/**
 * User3.java
 *
 * @author congye
 * @create 2016/07/01 4:40 PM
 */
public class User3 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Valid
    @NotNull(message = "{org.o.null}", groups = {First.class})
    @ConvertGroup(from=First.class, to=Second.class)
    private Organization0 o;

    @NotNull(message = "{user.id.null}", groups = {First.class})
    private Long id;

    @Length(min = 5, max = 20, message = "{user.name.length.illegal}", groups = {Second.class})
    @Pattern(regexp = "[a-zA-Z]{5,20}", message = "{user.name.illegal}", groups = {Second.class})
    private String name;

    @NotNull(message = "{user.password.null}", groups = {First.class, Second.class})
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

    public Organization0 getO() {
        return o;
    }

    public void setO(Organization0 o) {
        this.o = o;
    }
}
