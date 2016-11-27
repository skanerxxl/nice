package ru.skanerxxl.rambler.database.dao;

import ru.skanerxxl.rambler.database.entity.ConsumerCategory;
import ru.skanerxxl.rambler.database.entity.Photo;

import java.util.List;

public interface ConsumerCategoryDAO {

    void create(ConsumerCategory consumerCategory);

    void change(long idCC, String newNameCC, Photo photo);

    void delete(long idCC);

    List<ConsumerCategory> listOfConsumerCategory();
}
