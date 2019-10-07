package br.com.dasp.examgeneratorfront.persistence.model;


import java.io.Serializable;
import java.util.Objects;

public class AbstractEntity implements Serializable {
  protected Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
