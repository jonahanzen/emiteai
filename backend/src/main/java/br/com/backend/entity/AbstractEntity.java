package br.com.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * Abstract base class for all entities in the application.
 * This class provides a common structure for entities, including
 * an ID and a UUID field.
 */
@MappedSuperclass
@Getter
@Setter
public class AbstractEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = IDENTITY)
    protected Long id;

    @Length(min = 36, max = 36)
    @Column(length = 36, updatable = false, unique = true, nullable = false)
    protected String uuid;

    @PrePersist
    protected void prePersistUuid() {
        if (this.uuid == null) {
            this.uuid = java.util.UUID.randomUUID().toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || !getClass().equals(Hibernate.getClass(o)))
            return false;
        AbstractEntity that = (AbstractEntity) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId() == null ? 0 : getId().intValue();
    }
}
