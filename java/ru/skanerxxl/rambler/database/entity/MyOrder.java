package ru.skanerxxl.rambler.database.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class MyOrder {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private long productKey;

    private long photoKey;

    @Temporal(TemporalType.DATE)
    private Date date;

    private boolean status;

    private boolean exist = true;

    public MyOrder() {
    }

    public MyOrder(String name, long productKey, long photoKey, Date date, boolean status) {
        this.name = name;
        this.productKey = productKey;
        this.photoKey = photoKey;
        this.date = date;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getProductKey() {
        return productKey;
    }

    public void setProductKey(long productKey) {
        this.productKey = productKey;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getPhotoKey() {
        return photoKey;
    }

    public void setPhotoKey(long photoKey) {
        this.photoKey = photoKey;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyOrder myOrder = (MyOrder) o;

        return id == myOrder.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
