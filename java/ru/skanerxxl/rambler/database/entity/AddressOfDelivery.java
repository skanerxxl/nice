package ru.skanerxxl.rambler.database.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AddressOfDelivery")
public class AddressOfDelivery {
    @Id
    @GeneratedValue
    private long id;

    private String carrier;

    private String city;

    private String numberOffice;

    public AddressOfDelivery() {
    }

    public AddressOfDelivery(String carrier, String city, String numberOffice) {
        this.carrier = carrier;
        this.city = city;
        this.numberOffice = numberOffice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumberOffice() {
        return numberOffice;
    }

    public void setNumberOffice(String numberOffice) {
        this.numberOffice = numberOffice;
    }
}
