package com.slim.javaee.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;


/**
 * @author Salim CHAMI salim.chami@protonmail.com
 */
@Entity
@XmlRootElement
public class Customer extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    @Column
    @Temporal(TemporalType.DATE)
    private Date date;

    /**
     * default constructor.
     */
    public Customer() {
        //empty constructor
    }

    public Customer(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) obj;
        return !(getId() != null && !getId().equals(other.getId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + " ";
    }
}