package ru.myfirstwebsite.controller.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.myfirstwebsite.controller.request.ApplicationCreateRequest;
import ru.myfirstwebsite.domain.HibernateApplication;
import ru.myfirstwebsite.repository.springdata.SpringdataApplicationRepository;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/applications")
public class SpringdataApplicationController {

    @Autowired
    private SpringdataApplicationRepository springdataApplicationRepository;

    @GetMapping("/springdata/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<HibernateApplication>> getApplicationsSpringdata(@ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(springdataApplicationRepository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/springdata/by_user_id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateApplication>> getApplicationByUserIdSpringdata(Integer id) {
        return new ResponseEntity<>(springdataApplicationRepository.findByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/springdata/by_date_from")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateApplication>> getApplicationByDateFromSpringdata(Date dateFrom) {
        return new ResponseEntity<>(springdataApplicationRepository.findByDateFrom(dateFrom), HttpStatus.OK);
    }

    @GetMapping("/springdata/by_id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateApplication> getApplicationByIdSprindata(Integer id) {
        return new ResponseEntity<>(springdataApplicationRepository.findByApplicationId(id), HttpStatus.OK);
    }

    @PostMapping("/springdata/create")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HibernateApplication> createApplicationSpringdata(@RequestBody ApplicationCreateRequest request) {
        HibernateApplication application = new HibernateApplication();
        application.setDateFrom(request.getDateFrom());
        application.setDateTo(request.getDateTo());
        application.setRoomClass(request.getRoomClass());
        application.setRoomSize(request.getRoomSize());
        application.setUserId(request.getUserId());

        return new ResponseEntity<>(springdataApplicationRepository.save(application), HttpStatus.OK);
    }

    @RequestMapping(value = "/springdata/update/{id}", method = RequestMethod.PUT)
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateApplication> updateApplicationSpringdata(@PathVariable("id") Integer id,
                                                                            @RequestBody ApplicationCreateRequest request) {
        HibernateApplication application = springdataApplicationRepository.findByApplicationId(id);
        application.setDateFrom(request.getDateFrom());
        application.setDateTo(request.getDateTo());
        application.setRoomClass(request.getRoomClass());
        application.setRoomSize(request.getRoomSize());
        application.setUserId(request.getUserId());
        application.setApplicationId(id);

        return new ResponseEntity<>(springdataApplicationRepository.save(application), HttpStatus.OK);
    }

    @DeleteMapping("/springdata/delete/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteApplicationSpringdata(@PathVariable("id") Integer id) {
        HibernateApplication application = springdataApplicationRepository.findByApplicationId(id);
        springdataApplicationRepository.delete(application);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
