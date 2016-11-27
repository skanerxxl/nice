package ru.skanerxxl.rambler.database.dao;

import ru.skanerxxl.rambler.database.entity.Deal;

import java.util.List;

public interface DealDAO {

    void checkDeal(Deal deal, long[] idOr);

    List<Deal> dealsList();

    Deal openDeal(long idDeal);

    void deleteDeal(long idDeal);
}
