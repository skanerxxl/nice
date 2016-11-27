package ru.skanerxxl.rambler.database.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Deal {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String phone;

    private String email;

    private boolean exist = true;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private AddressOfDelivery addressOfDelivery;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "deal_id")
    private Set<MyOrder> myOrders = new HashSet<>();

    public Deal() {
    }

    public Deal(String name, String phone, String email, AddressOfDelivery addressOfDelivery) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.addressOfDelivery = addressOfDelivery;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressOfDelivery getAddressOfDelivery() {
        return addressOfDelivery;
    }

    public void setAddressOfDelivery(AddressOfDelivery addressOfDelivery) {
        this.addressOfDelivery = addressOfDelivery;
    }

    public Set<MyOrder> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(Set<MyOrder> myOrders) {
        this.myOrders = myOrders;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
}




