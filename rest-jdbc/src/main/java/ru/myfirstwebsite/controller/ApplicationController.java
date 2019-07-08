package ru.myfirstwebsite.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.myfirstwebsite.controller.requests.ApplicationCreateRequest;
import ru.myfirstwebsite.domain.Application;
import ru.myfirstwebsite.repository.ApplicationDao;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/applications")
public class ApplicationController {

    @Autowired
    private ApplicationDao applicationDao;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Application>> getApplications() {
        return new ResponseEntity<>(applicationDao.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Application> getApplicationById(@PathVariable Integer id) {
        Application application = applicationDao.findById(id);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Application> createApplication(@RequestBody ApplicationCreateRequest request) {
        Application application = new Application();
        application.setRoomClass(request.getRoomClass());
        application.setRoomSize(request.getRoomSize());
        application.setDateFrom(request.getDateFrom());
        application.setDateTo(request.getDateTo());
        application.setUserId(request.getUserId());

        Application savedApplication = applicationDao.save(application);
        return new ResponseEntity<>(savedApplication, HttpStatus.OK);
    }

    @ApiOperation(value = "Update application by applicationId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful application update"),
            @ApiResponse(code = 400, message = "Invalid HibernateApplication ID supplied"),
            @ApiResponse(code = 404, message = "HibernateApplication was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Application> updateApplication(@PathVariable("id") Integer applicationId,
                                                         @RequestBody ApplicationCreateRequest request) {
        Application application = applicationDao.findById(applicationId);
        application.setRoomClass(request.getRoomClass());
        application.setRoomSize(request.getRoomSize());
        application.setDateFrom(request.getDateFrom());
        application.setDateTo(request.getDateTo());
        application.setUserId(request.getUserId());

        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteApplication(@PathVariable("id")Integer applicationId) {
        applicationDao.delete(applicationId);
        return new ResponseEntity<>(applicationId, HttpStatus.OK);
    }
}
