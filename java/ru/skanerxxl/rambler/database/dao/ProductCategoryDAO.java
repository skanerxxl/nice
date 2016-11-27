package ru.skanerxxl.rambler.database.dao;

import ru.skanerxxl.rambler.database.entity.ProductCategory;

import java.util.Set;

public interface ProductCategoryDAO {

    void create(ProductCategory productCategory, long idCC);

    void change(long idPC, ProductCategory productCategory);

    void delete(long idPC, long idCC);

    Set<ProductCategory> listOfProductCategory(long idCC);
}
