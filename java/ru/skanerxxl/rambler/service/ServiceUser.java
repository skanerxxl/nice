package ru.skanerxxl.rambler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skanerxxl.rambler.database.dao.DealDAO;
import ru.skanerxxl.rambler.database.dao.UserDAO;
import ru.skanerxxl.rambler.database.entity.Deal;
import ru.skanerxxl.rambler.database.entity.MyOrder;
import ru.skanerxxl.rambler.database.entity.User;

import java.util.Set;

@Service
public class ServiceUser {
    @Autowired
    @Qualifier("usJpaDAO")
    private UserDAO userDAO;
    @Autowired
    @Qualifier("dlJpaDAO")
    private DealDAO dealDAO;


    @Transactional
    public boolean saveUser(User user, String name_role) {
        return userDAO.saveUser(user, name_role);
    }

    @Transactional
    public User getUser(String login) {
        return userDAO.getUser(login);
    }

    @Transactional
    public Set<MyOrder> userBasketDeals(String nameUser, boolean toggle) {
        return userDAO.userBasketDeals(nameUser, toggle);
    }

    @Transactional
    public void addOrderInBasket(String nameUser, MyOrder myOrder) {
        userDAO.addOrderInBasket(nameUser, myOrder);
    }

    @Transactional
    public void addInDeals(String nameUser, long[] idOr) {
        userDAO.addInDeals(nameUser, idOr);
    }

    @Transactional
    public void deleteMyOrders(String nameUser, long[] idOr) {
        userDAO.deleteMyOrders(nameUser, idOr);
    }

    @Transactional
    public void deleteMyOrder(String nameUser, long idOr) {
        userDAO.deleteMyOrder(nameUser, idOr);
    }

    @Transactional
    public void checkDeal(Deal deal, long[] idOr) {
        dealDAO.checkDeal(deal, idOr);
    }
}
