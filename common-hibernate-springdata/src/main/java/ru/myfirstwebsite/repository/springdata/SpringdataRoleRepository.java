package ru.myfirstwebsite.repository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.myfirstwebsite.domain.HibernateRole;

public interface SpringdataRoleRepository extends JpaRepository<HibernateRole, Integer>,
        CrudRepository<HibernateRole, Integer>,
        PagingAndSortingRepository<HibernateRole, Integer> {
}
