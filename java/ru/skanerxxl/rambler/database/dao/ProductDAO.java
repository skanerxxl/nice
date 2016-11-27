package ru.skanerxxl.rambler.database.dao;

import ru.skanerxxl.rambler.database.entity.Product;

import java.util.Set;

public interface ProductDAO {

    void create(Product product, long idPC);

    void change(Product newProduct, long idPr);

    void delete(long idPC, long idPr);

    Product openProduct(long idPr);

    Set<Product> listOfProduct(long idPC);
}
