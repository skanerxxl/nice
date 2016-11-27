package ru.skanerxxl.rambler.database.repository;

import org.springframework.stereotype.Repository;
import ru.skanerxxl.rambler.database.dao.DealDAO;
import ru.skanerxxl.rambler.database.entity.Deal;
import ru.skanerxxl.rambler.database.entity.MyOrder;
import ru.skanerxxl.rambler.database.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Repository
public class DealDAORep implements DealDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void checkDeal(Deal deal, long[] idOr) {
        for (int i = 0; i < idOr.length; i++) {
            MyOrder myOrder = entityManager.find(MyOrder.class, idOr[i]);
            deal.getMyOrders().add(myOrder);
        }
        entityManager.merge(deal);
    }

    private boolean checkProduct(long productKey) {
        Product product = entityManager.find(Product.class, productKey);
        if (product == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Deal openDeal(long idDeal) {
        Deal deal = entityManager.find(Deal.class, idDeal);
        Set<MyOrder> myOr = deal.getMyOrders();
        for (MyOrder myOrder : myOr) {
            if (!checkProduct(myOrder.getProductKey())) {
                myOrder.setExist(false);
            }
        }
        return deal;
    }

    @Override
    public List<Deal> dealsList() {
        Query query = entityManager.createQuery("SELECT d FROM Deal d", Deal.class);
        return (List<Deal>) query.getResultList();
    }

    @Override
    public void deleteDeal(long idDeal) {
        Deal deal = entityManager.find(Deal.class, idDeal);
        entityManager.remove(deal);
    }
}
