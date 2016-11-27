package ru.skanerxxl.rambler.database.dao;

import ru.skanerxxl.rambler.database.entity.MyOrder;
import ru.skanerxxl.rambler.database.entity.User;

import java.util.Set;

public interface UserDAO {

    boolean saveUser(User user, String name_role);

    User getUser(String login);

    Set<MyOrder> userBasketDeals(String nameUser, boolean toggle);

    void addOrderInBasket(String nameUser, MyOrder myOrder);

    void addInDeals(String nameUser, long[] idOr);

    void deleteMyOrders(String nameUser, long[] idOr);

    void deleteMyOrder(String nameUser, long idOr);

}
