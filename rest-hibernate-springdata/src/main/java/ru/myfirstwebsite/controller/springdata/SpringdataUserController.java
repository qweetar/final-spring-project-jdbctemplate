package ru.myfirstwebsite.controller.springdata;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.myfirstwebsite.controller.request.UserCreateRequest;
import ru.myfirstwebsite.domain.HibernateRole;
import ru.myfirstwebsite.domain.HibernateUser;
import ru.myfirstwebsite.repository.springdata.SpringdataRoleRepository;
import ru.myfirstwebsite.repository.springdata.SpringdataUserRepository;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@CrossOrigin
@RequestMapping(value = "/rest/users")
public class SpringdataUserController {

    @Autowired
    private SpringdataUserRepository springdataUserRepository;

    @Autowired
    private SpringdataRoleRepository springdataRoleRepository;


//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
//                    value = "Results page you want to retrieve (0..N)"),
//            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
//                    value = "Number of records per page."),
//            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
//                    value = "Sorting criteria in the format: property(,asc|desc). " +
//                            "Default sort order is ascending. " +
//                            "Multiple sort criteria are supported.")
//    })
    @GetMapping("/springdata/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<HibernateUser>> getUsersSpringdata(@ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(springdataUserRepository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/springdata/by_login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateUser> getUserByLoginSptingdata(String login) {
        return new ResponseEntity<>(springdataUserRepository.findByLogin(login), HttpStatus.OK);
    }

    @GetMapping("/springdata/by_id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateUser> getUserByIdSpringdata(Long id) {
        return new ResponseEntity<>(springdataUserRepository.findByUserId(id), HttpStatus.OK);
    }

    @PostMapping("/springdata/create")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HibernateUser> createUserSpringdata(@RequestBody UserCreateRequest request, String role) {
        HibernateUser user = new HibernateUser();
        user.setUserName(request.getUserName());
        user.setSurName(request.getUserSurname());
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setSex(request.getSex());
        user.setMobilePhone(request.getMobilePhone());
        user.setEmail(request.getEmail());
        user.setBlocked(request.getBlocked());

        HibernateUser savedUser = springdataUserRepository.save(user);
        springdataRoleRepository.save(new HibernateRole(savedUser, role));

        return new ResponseEntity<>(savedUser, HttpStatus.OK);

    }

    @RequestMapping(value = "/springdata/update_phone/{id}", method = RequestMethod.PUT)
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateUser> updateUserPhoneSpringdata(@PathVariable("id") Long id,
                                                                   @RequestBody String phone) {
        HibernateUser user = springdataUserRepository.findByUserId(id);
        user.setMobilePhone(phone);
        return new ResponseEntity<>(springdataUserRepository.save(user), HttpStatus.OK);
    }

    @DeleteMapping("/springdata/delete/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteUserSpringgata(@PathVariable("id") Long id) {
        HibernateUser user = springdataUserRepository.findByUserId(id);
        springdataUserRepository.delete(user);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }



}


