package com.slim.javaee.core.model;

import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BaseEntity<P extends Serializable> implements Serializable {

    @Id
    @Target(value = Long.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private P id;

    @Version
    @Column(name = "version")
    private int version;
    public P getId() {
        return id;
    }

    public void setId(P id) {
        this.id = id;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }
}
