package ru.skanerxxl.rambler.database.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class ConsumerCategory {
    @Id
    @GeneratedValue
    private long id;

    private String nameConsumerCategory;


    @OneToMany(mappedBy = "consumerCategory", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ProductCategory> listOfProductCategory = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Photo photo;

    public ConsumerCategory() {
    }

    public ConsumerCategory(String nameConsumerCategory, Photo photo) {
        this.nameConsumerCategory = nameConsumerCategory;
        this.photo = photo;
    }

    public void addProductCategory(ProductCategory productCategory) {
        productCategory.setConsumerCategory(this);
        listOfProductCategory.add(productCategory);
    }

    public void deleteProductCategory(ProductCategory productCategory) {
        listOfProductCategory.remove(productCategory);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameConsumerCategory() {
        return nameConsumerCategory;
    }

    public void setNameConsumerCategory(String nameConsumerCategory) {
        this.nameConsumerCategory = nameConsumerCategory;
    }

    public Set<ProductCategory> getListOfProductCategory() {
        return listOfProductCategory;
    }

    public void setListOfProductCategory(Set<ProductCategory> listOfProductCategory) {
        this.listOfProductCategory = listOfProductCategory;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
