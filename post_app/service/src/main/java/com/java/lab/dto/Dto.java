package com.java.lab.dto;

import java.io.Serializable;
import java.util.Objects;

public class Dto implements Serializable {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Dto{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dto dto = (Dto) o;
        return id == dto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
