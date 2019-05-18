package ru.myfirstwebsite.repository;


import ru.myfirstwebsite.domain.to.Application;

import java.util.List;

public interface ApplicationDao extends GenericDao <Application, Integer> {


    List<Application> getUserApplication(Integer userId);
}
