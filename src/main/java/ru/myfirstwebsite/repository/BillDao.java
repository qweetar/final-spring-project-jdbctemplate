package ru.myfirstwebsite.repository;

import ru.myfirstwebsite.domain.to.Bill;

import java.util.List;

public interface BillDao extends GenericDao <Bill, Integer> {


    List<Bill> getBill(Integer roomId);
}
