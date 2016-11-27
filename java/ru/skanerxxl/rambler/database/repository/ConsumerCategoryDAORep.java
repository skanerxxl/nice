package ru.skanerxxl.rambler.database.repository;

import org.springframework.stereotype.Repository;
import ru.skanerxxl.rambler.database.dao.ConsumerCategoryDAO;
import ru.skanerxxl.rambler.database.entity.ConsumerCategory;
import ru.skanerxxl.rambler.database.entity.Photo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ConsumerCategoryDAORep implements ConsumerCategoryDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(ConsumerCategory consumerCategory) {
        entityManager.merge(consumerCategory);
    }

    @Override
    public void change(long idCC, String newNameCC, Photo photo) {
        ConsumerCategory consumerCategory = entityManager.find(ConsumerCategory.class, idCC);
        if (!newNameCC.equals("")) {
            consumerCategory.setNameConsumerCategory(newNameCC);
        }
        if (photo.getBody().length != 0) {
            consumerCategory.setPhoto(photo);
        }
    }

    @Override
    public void delete(long idCC) {
        ConsumerCategory consumerCategory = entityManager.find(ConsumerCategory.class, idCC);
        entityManager.remove(consumerCategory);
    }

    @Override
    public List<ConsumerCategory> listOfConsumerCategory() {
        Query query = entityManager.createQuery("SELECT c FROM ConsumerCategory c", ConsumerCategory.class);
        return (List<ConsumerCategory>) query.getResultList();
    }
}
