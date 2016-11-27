package ru.skanerxxl.rambler.database.repository;

import org.springframework.stereotype.Repository;
import ru.skanerxxl.rambler.database.dao.ProductDAO;
import ru.skanerxxl.rambler.database.entity.Product;
import ru.skanerxxl.rambler.database.entity.ProductCategory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

@Repository
public class ProductDAORep implements ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Product product, long idPC) {
        ProductCategory productCategory = entityManager.find(ProductCategory.class, idPC);
        productCategory.addListOfProduct(product);
    }

    @Override
    public void change(Product newProduct, long idPr) {
        Product prEntity = entityManager.find(Product.class, idPr);
        if (!newProduct.getName().equals("")) {
            prEntity.setName(newProduct.getName());
        }
        if (newProduct.getPhoto().getBody().length != 0) {
            prEntity.setPhoto(newProduct.getPhoto());
        }
        if (newProduct.getPrice() != 0) {
            prEntity.setPrice(newProduct.getPrice());
        }
        if (!newProduct.getColor().equals("")) {
            prEntity.setColor(newProduct.getColor());
        }
        if (!newProduct.getSize().equals("")) {
            prEntity.setSize(newProduct.getSize());
        }
        if (!newProduct.getSeason().equals("")) {
            prEntity.setSeason(newProduct.getSeason());
        }
        if (!newProduct.getZastezhka().equals("")) {
            prEntity.setZastezhka(newProduct.getZastezhka());
        }
        if (!newProduct.getLength().equals("")) {
            prEntity.setLength(newProduct.getLength());
        }
        if (!newProduct.getSleeve().equals("")) {
            prEntity.setSleeve(newProduct.getSleeve());
        }
        if (!newProduct.getMaterial().equals("")) {
            prEntity.setMaterial(newProduct.getMaterial());
        }
        if (!newProduct.getDescription().equals("")) {
            prEntity.setDescription(newProduct.getDescription());
        }
    }

    @Override
    public void delete(long idPC, long idPr) {
        ProductCategory productCategory = entityManager.find(ProductCategory.class, idPC);
        Product product = entityManager.find(Product.class, idPr);
        productCategory.deleteProduct(product);
    }

    @Override
    public Product openProduct(long idPr) {
        return entityManager.find(Product.class, idPr);
    }

    @Override
    public Set<Product> listOfProduct(long idPC) {
        ProductCategory productCategory = entityManager.find(ProductCategory.class, idPC);
        return productCategory.getListOfProduct();
    }
}
