package ru.myfirstwebsite.controller.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.myfirstwebsite.controller.request.SearchCriteria;
import ru.myfirstwebsite.controller.request.UserCreateRequest;
import ru.myfirstwebsite.domain.HibernateRole;
import ru.myfirstwebsite.domain.HibernateUser;
import ru.myfirstwebsite.repository.hibernate.HibernateRoleDao;
import ru.myfirstwebsite.repository.hibernate.HibernateUserDao;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/users")
public class HibernateUserController {

    @Autowired
    private HibernateUserDao hibernateUserDao;

    @Autowired
    private HibernateRoleDao hibernateRoleDao;

    @GetMapping("/hibernate/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateUser>> getUsersHibernate() {
        return new ResponseEntity<>(hibernateUserDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/hibernate/by_login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateUser> getUserByLoginHibernate(String login) {
        return new ResponseEntity<>(hibernateUserDao.findByLogin(login), HttpStatus.OK);
    }

    @GetMapping("/hibernate/by_id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateUser> getUserByIdHibernate(Long id) {
        return new ResponseEntity<>(hibernateUserDao.findById(id), HttpStatus.OK);
    }

    @GetMapping("/hibernate/search")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateUser>> searchUserHibernate(@ModelAttribute @Valid SearchCriteria search) {
        return new ResponseEntity<>(hibernateUserDao.search(search.getQuery(), search.getLimit(), search.getOffset()), HttpStatus.OK);
    }

    @PostMapping("/hibernate/create")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HibernateUser> createUserHibernate(@RequestBody UserCreateRequest request, String role) {
        HibernateUser user = new HibernateUser();
        user.setUserName(request.getUserName());
        user.setSurName(request.getUserSurname());
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setSex(request.getSex());
        user.setMobilePhone(request.getMobilePhone());
        user.setEmail(request.getEmail());
        user.setBlocked(request.getBlocked());

        HibernateUser savedUser = hibernateUserDao.save(user);
        hibernateRoleDao.save(new HibernateRole(savedUser, role));

        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/hibernate/update/{id}", method = RequestMethod.PUT)
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateUser> updateUserHibernate(@PathVariable("id") Long id,
                                                             @RequestBody UserCreateRequest request) {
        HibernateUser user = hibernateUserDao.findById(id);
        user.setUserName(request.getUserName());
        user.setSurName(request.getUserSurname());
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setSex(request.getSex());
        user.setMobilePhone(request.getMobilePhone());
        user.setEmail(request.getEmail());
        user.setBlocked(request.getBlocked());
        user.setUserId(id);

        return new ResponseEntity<>(hibernateUserDao.update(user), HttpStatus.OK);
    }

    @DeleteMapping("/hibernate/delete/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteUserHibernate(@PathVariable("id") Long id) {
        hibernateUserDao.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
