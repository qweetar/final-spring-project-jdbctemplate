package ru.myfirstwebsite.controller.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.myfirstwebsite.controller.request.ApplicationCreateRequest;
import ru.myfirstwebsite.domain.HibernateApplication;
import ru.myfirstwebsite.repository.hibernate.HibernateApplicationDao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/applications")
public class HibernateApplicationController {

    @Autowired
    private HibernateApplicationDao hibernateApplicationDao;

    @GetMapping("/hibernate/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateApplication>> getApplicationsHibernate() {
        return new ResponseEntity<>(hibernateApplicationDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/hibernate/by_user_id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateApplication>> getApplicationByUserIdHibernate(Integer userId) {
        return new ResponseEntity<>(hibernateApplicationDao.findByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/hibernate/by_date_from")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateApplication>> getApplicationByDateFromHibernate(@RequestParam("dateFrom")
                                                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date dateFrom) {
        return new ResponseEntity<>(hibernateApplicationDao.findByDateFrom(dateFrom), HttpStatus.OK);
    }

    @GetMapping("/hibernate/by_id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateApplication> getApplicationByIdHibernate(Integer id) {
        return new ResponseEntity<>(hibernateApplicationDao.findById(id), HttpStatus.OK);
    }

    @PostMapping("/hibernate/create")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HibernateApplication> createApplicationHibernate (@RequestBody ApplicationCreateRequest request) {
        HibernateApplication application = new HibernateApplication();
        application.setRoomClass(request.getRoomClass());
        application.setRoomSize(request.getRoomSize());
        application.setUserId(request.getUserId());
        application.setDateFrom(new Timestamp(new Date().getTime()));
        application.setDateTo(new Timestamp(new Date().getTime()));

        return new ResponseEntity<>(hibernateApplicationDao.save(application), HttpStatus.OK);
    }

    @RequestMapping(value = "/hibernate/update/{id}", method = RequestMethod.PUT)
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateApplication> updateApplicationHibernate(@PathVariable("id") Integer id,
                                                                           @RequestBody ApplicationCreateRequest request) {
       HibernateApplication application = hibernateApplicationDao.findById(id);

       application.setUserId(request.getUserId());
       application.setDateFrom(new Timestamp(new Date().getTime()));;
       application.setDateTo(new Timestamp(new Date().getTime()));;
       application.setRoomSize(request.getRoomSize());
       application.setRoomClass(request.getRoomClass());
       application.setApplicationId(id);

       return new ResponseEntity<>(hibernateApplicationDao.update(application), HttpStatus.OK);
    }

    @DeleteMapping("/hibernate/delete/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteApplicationHibernate(@PathVariable("id") Integer id) {
        hibernateApplicationDao.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }



}
