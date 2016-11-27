package ru.skanerxxl.rambler.database.entity;

import javax.persistence.*;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Photo photo;

    @Column(nullable = true)
    private double price;

    private String color;
    private String size;
    private String season;
    private String zastezhka;
    private String length;
    private String sleeve;
    private String material;
    private String description;

    @ManyToOne
    @JoinColumn
    private ProductCategory productCategory;

    public Product() {
    }

    public Product(String name) {// конструктор для хлебных крошек
        this.name = name;
    }

    public Product(String name, Photo photo, double price, String color, String size,
                   String season, String zastezhka, String length, String sleeve, String material, String description) {
        this.name = name;
        this.photo = photo;
        this.price = price;
        this.color = color;
        this.size = size;
        this.season = season;
        this.zastezhka = zastezhka;
        this.length = length;
        this.sleeve = sleeve;
        this.material = material;
        this.description = description;
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

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getZastezhka() {
        return zastezhka;
    }

    public void setZastezhka(String zastezhka) {
        this.zastezhka = zastezhka;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getSleeve() {
        return sleeve;
    }

    public void setSleeve(String sleeve) {
        this.sleeve = sleeve;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id == product.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
