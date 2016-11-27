package ru.skanerxxl.rambler.database.repository;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.skanerxxl.rambler.database.dao.UserDAO;
import ru.skanerxxl.rambler.database.entity.MyOrder;
import ru.skanerxxl.rambler.database.entity.Product;
import ru.skanerxxl.rambler.database.entity.Role;
import ru.skanerxxl.rambler.database.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Repository
public class UserDAORep implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean saveUser(User user, String name_role) {
        if (getUser(user.getUsername()) != null) {
            return false;
        }
        Role role;

        Query query = entityManager.createQuery("SELECT c FROM Role c WHERE c.role LIKE :name_role", Role.class);
        query.setParameter("name_role", "%" + name_role + "%");
        try {
            role = (Role) query.getSingleResult();
            String pssw = user.getPassword();
            ShaPasswordEncoder encoder = new ShaPasswordEncoder();
            String pssw_sha1 = encoder.encodePassword(pssw, null);
            user.setPassword(pssw_sha1);

            role.addUser(user);
            return true;
        } catch (NoResultException e) {
            role = new Role(name_role);
            role.addUser(user);
            entityManager.merge(role);
            return true;
        }
    }

    @Override
    public User getUser(String login) {
        User user;
        Query query = entityManager.createQuery("SELECT c FROM User c WHERE c.username LIKE :login", User.class);
        query.setParameter("login", "%" + login + "%");
        try {
            user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Set<MyOrder> userBasketDeals(String nameUser, boolean toggle) {
        User user = getUser(nameUser);
        Set<MyOrder> set = new HashSet<>();
        for (MyOrder myOrder : user.getMyOrders()) {
            if (!checkProduct(myOrder.getProductKey())) {// not exist
                myOrder.setExist(false);
            }
            if ((!toggle) && (!myOrder.isStatus())) {// basket
                set.add(myOrder);
            }
            if ((toggle) && (myOrder.isStatus())) {// deals
                set.add(myOrder);
            }
        }
        return set;
    }

    private boolean checkProduct(long productKey) {
        Product product = entityManager.find(Product.class, productKey);
        return (product == null) ? false : true;
    }

    @Override
    public void addOrderInBasket(String nameUser, MyOrder myOrder) {
        User user = getUser(nameUser);
        user.getMyOrders().add(myOrder);
    }

    @Override
    public void addInDeals(String nameUser, long[] idOr) {// переводим флажок ордера в статус: оформлено
        User user = getUser(nameUser);
        for (int i = 0; i < idOr.length; i++) {
            for (MyOrder myOrder : user.getMyOrders()) {
                if (myOrder.getId() == idOr[i]) {
                    myOrder.setStatus(true);
                }
            }
        }
    }

    @Override
    public void deleteMyOrders(String nameUser, long[] idOr) {
        User user = getUser(nameUser);
        MyOrder mask = new MyOrder("mask", 0, 0, new Date(), false);
        for (int i = 0; i < idOr.length; i++) {
            mask.setId(idOr[i]);
            Iterator<MyOrder> iterator = user.getMyOrders().iterator();
            while (iterator.hasNext()) {
                if (iterator.next().equals(mask)) {
                    iterator.remove();
                }
            }
        }
    }

    @Override
    public void deleteMyOrder(String nameUser, long idOr) {
        User user = getUser(nameUser);
        MyOrder myOrder = entityManager.find(MyOrder.class, idOr);
        user.getMyOrders().remove(myOrder);
    }
}
