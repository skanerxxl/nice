package ru.skanerxxl.rambler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skanerxxl.rambler.database.dao.ConsumerCategoryDAO;
import ru.skanerxxl.rambler.database.dao.PhotoDAO;
import ru.skanerxxl.rambler.database.dao.ProductCategoryDAO;
import ru.skanerxxl.rambler.database.dao.ProductDAO;
import ru.skanerxxl.rambler.database.entity.ConsumerCategory;
import ru.skanerxxl.rambler.database.entity.Product;
import ru.skanerxxl.rambler.database.entity.ProductCategory;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class ServiceAnonymous {
    @Autowired
    @Qualifier("phJpaDAO")
    private PhotoDAO photoDAO;
    @Autowired
    @Qualifier("prJpaDAO")
    private ProductDAO productDAO;
    @Autowired
    @Qualifier("pcJpaDAO")
    private ProductCategoryDAO productCategoryDAO;
    @Autowired
    @Qualifier("ccJpaDAO")
    private ConsumerCategoryDAO consumerCategoryDAO;


    //-------------------------ConsumerCategory-------------------------//


    @Transactional
    public List<ConsumerCategory> listOfConsumerCategory() {
        return consumerCategoryDAO.listOfConsumerCategory();
    }


    //-------------------------ProductCategory-------------------------//


    @Transactional
    public Set<ProductCategory> listOfProductCategory(long idCC) {
        return productCategoryDAO.listOfProductCategory(idCC);
    }


    //-------------------------Product-------------------------//


    @Transactional
    public Set<Product> listOfProduct(long idPC) {
        return productDAO.listOfProduct(idPC);
    }


    @Transactional
    public Product openProduct(long idPr) {
        return productDAO.openProduct(idPr);
    }


    //-------------------------Product-------------------------//


    @Transactional
    public ResponseEntity<byte[]> viewPhoto(long id) {
        return photoDAO.viewPhoto(id);
    }
}

