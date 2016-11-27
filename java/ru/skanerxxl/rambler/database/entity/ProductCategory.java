package ru.skanerxxl.rambler.database.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class ProductCategory {
    @Id
    @GeneratedValue
    private long id;

    private String nameProductCategory;

    @ManyToOne
    @JoinColumn
    private ConsumerCategory consumerCategory;

    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Product> listOfProduct = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Photo photo;

    public ProductCategory() {
    }

    public ProductCategory(String nameCategory, Photo photo) {
        this.nameProductCategory = nameCategory;
        this.photo = photo;
    }

    public void addListOfProduct(Product product) {
        product.setProductCategory(this);
        listOfProduct.add(product);
    }

    public void deleteProduct(Product product) {
        listOfProduct.remove(product);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameProductCategory() {
        return nameProductCategory;
    }

    public void setNameProductCategory(String nameProductCategory) {
        this.nameProductCategory = nameProductCategory;
    }

    public ConsumerCategory getConsumerCategory() {
        return consumerCategory;
    }

    public void setConsumerCategory(ConsumerCategory consumerCategory) {
        this.consumerCategory = consumerCategory;
    }

    public Set<Product> getListOfProduct() {
        return listOfProduct;
    }

    public void setListOfProduct(Set<Product> listOfProduct) {
        this.listOfProduct = listOfProduct;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductCategory that = (ProductCategory) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
