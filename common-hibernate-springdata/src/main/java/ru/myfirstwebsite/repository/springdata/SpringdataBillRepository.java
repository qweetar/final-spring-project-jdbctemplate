package ru.myfirstwebsite.repository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.myfirstwebsite.domain.HibernateBill;

import java.util.List;

public interface SpringdataBillRepository extends
        CrudRepository<HibernateBill, Integer>,
        JpaRepository<HibernateBill, Integer>,
        PagingAndSortingRepository<HibernateBill, Integer> {

    List<HibernateBill> findByUserId(Integer id);

    HibernateBill findByBillId(Integer id);
}
