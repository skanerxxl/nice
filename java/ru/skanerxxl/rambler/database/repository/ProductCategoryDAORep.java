package ru.skanerxxl.rambler.database.repository;

import org.springframework.stereotype.Repository;
import ru.skanerxxl.rambler.database.dao.ProductCategoryDAO;
import ru.skanerxxl.rambler.database.entity.ConsumerCategory;
import ru.skanerxxl.rambler.database.entity.ProductCategory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

@Repository
public class ProductCategoryDAORep implements ProductCategoryDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(ProductCategory productCategory, long idCC) {
        ConsumerCategory consumerCategory = entityManager.find(ConsumerCategory.class, idCC);
        consumerCategory.addProductCategory(productCategory);
    }

    @Override
    public void change(long idPC, ProductCategory productCategory) {
        ProductCategory pCEntity = entityManager.find(ProductCategory.class, idPC);
        if (!productCategory.getNameProductCategory().equals("")) {
            pCEntity.setNameProductCategory(productCategory.getNameProductCategory());
        }
        if (productCategory.getPhoto().getBody().length != 0) {
            pCEntity.setPhoto(productCategory.getPhoto());
        }
    }

    @Override
    public void delete(long idPC, long idCC) {
        ConsumerCategory consumerCategory = entityManager.find(ConsumerCategory.class, idCC);
        ProductCategory productCategory = entityManager.find(ProductCategory.class, idPC);
        consumerCategory.deleteProductCategory(productCategory);
    }

    @Override
    public Set<ProductCategory> listOfProductCategory(long idCC) {
        ConsumerCategory consumerCategory = entityManager.find(ConsumerCategory.class, idCC);
        return consumerCategory.getListOfProductCategory();
    }
}
