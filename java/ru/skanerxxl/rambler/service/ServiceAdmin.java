package ru.skanerxxl.rambler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.skanerxxl.rambler.database.dao.ConsumerCategoryDAO;
import ru.skanerxxl.rambler.database.dao.DealDAO;
import ru.skanerxxl.rambler.database.dao.ProductCategoryDAO;
import ru.skanerxxl.rambler.database.dao.ProductDAO;
import ru.skanerxxl.rambler.database.entity.*;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ServiceAdmin {
    @Autowired
    @Qualifier("prJpaDAO")
    private ProductDAO productDAO;
    @Autowired
    @Qualifier("pcJpaDAO")
    private ProductCategoryDAO productCategoryDAO;
    @Autowired
    @Qualifier("ccJpaDAO")
    private ConsumerCategoryDAO consumerCategoryDAO;
    @Autowired
    @Qualifier("dlJpaDAO")
    private DealDAO dealDAO;


    //-------------------------Product-------------------------//

    @Transactional
    public void createProduct(Product product, long idPC) {
        productDAO.create(product, idPC);
    }

    @Transactional
    public void changeProduct(Product newProduct, long idPr) {
        productDAO.change(newProduct, idPr);
    }

    @Transactional
    public void deleteProduct(long idPC, long idPr) {
        productDAO.delete(idPC, idPr);
    }


    //-------------------------ProductCategory-------------------------//


    @Transactional()
    public void createProductCategory(ProductCategory productCategory, long idCC) {
        productCategoryDAO.create(productCategory, idCC);
    }

    @Transactional
    public void changeProductCategory(long idPC, ProductCategory productCategory) {
        productCategoryDAO.change(idPC, productCategory);
    }

    @Transactional
    public void deleteProductCategory(long idPC, long idCC) {
        productCategoryDAO.delete(idPC, idCC);
    }


    //-------------------------ConsumerCategory-------------------------//


    @Transactional
    public void createConsumerCategory(ConsumerCategory consumerCategory) {
        consumerCategoryDAO.create(consumerCategory);
    }

    @Transactional
    public void changeConsumerCategory(long idCC, String newNameCC, Photo photo) {
        consumerCategoryDAO.change(idCC, newNameCC, photo);
    }

    @Transactional
    public void deleteConsumerCategory(long idCC) {
        consumerCategoryDAO.delete(idCC);
    }


    //-------------------------Deal-------------------------//


    @Transactional
    public List<Deal> dealsList() {
        return dealDAO.dealsList();
    }

    @Transactional
    public void deleteDeal(long idDeal) {
        dealDAO.deleteDeal(idDeal);
    }

    @Transactional
    public Deal openDeal(long idDeal) {
        return dealDAO.openDeal(idDeal);
    }
}
