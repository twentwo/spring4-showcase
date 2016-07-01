package com.sishuok.spring4.entity;

import com.sishuok.spring4.group.First;
import com.sishuok.spring4.group.Second;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-12-13
 * <p>Version: 1.0
 */
public class Organization0 implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "{org.id.null}", groups = {First.class})
    private Long id;

    @Length(min = 5, max = 20, message = "{org.name.length.illegal}", groups = {Second.class})
    @Pattern(regexp = "[a-zA-Z]{5,20}", message = "{org.name.illegal}", groups = {Second.class})
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization0 that = (Organization0) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
