package ru.myfirstwebsite.repository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.myfirstwebsite.domain.HibernateApplication;

import java.util.Date;
import java.util.List;

public interface SpringdataApplicationRepository extends
        CrudRepository<HibernateApplication, Integer>,
        JpaRepository<HibernateApplication, Integer>,
        PagingAndSortingRepository<HibernateApplication, Integer> {

    List<HibernateApplication> findByUserId(Integer id);

    List<HibernateApplication> findByDateFrom(Date dateFrom);

    HibernateApplication findByApplicationId(Integer id);
}
