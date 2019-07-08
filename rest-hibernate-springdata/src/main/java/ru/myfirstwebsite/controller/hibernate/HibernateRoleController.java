package ru.myfirstwebsite.controller.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.myfirstwebsite.controller.request.RoleCreateRequest;
import ru.myfirstwebsite.domain.HibernateRole;
import ru.myfirstwebsite.repository.hibernate.HibernateRoleDao;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/roles")
public class HibernateRoleController {

    @Autowired
    private HibernateRoleDao hibernateRoleDao;

    @GetMapping("/hibernate/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateRole>> getRoleHibernate() {
        return new ResponseEntity<>(hibernateRoleDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/hibernate/by_role_name")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateRole>> getRoleByNameHibernate(String roleName) {
        return new ResponseEntity<>(hibernateRoleDao.findByRoleName(roleName), HttpStatus.OK);
    }

    @GetMapping("/hibernate/by_id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateRole> getRoleByIdHibernate(Long id) {
        return new ResponseEntity<>(hibernateRoleDao.findById(id), HttpStatus.OK);
    }

    @PostMapping("/hibernate/create")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HibernateRole> createRoleHibernate(@RequestBody RoleCreateRequest request) {
        HibernateRole role = new HibernateRole();
        role.setUser(request.getUser());
        role.setRoleName(request.getRoleName());

        return new ResponseEntity<>(hibernateRoleDao.save(role), HttpStatus.OK);
    }

    @RequestMapping(value = "/hibernate/update/{id}", method = RequestMethod.PUT)
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateRole> updateRoleHibernate(@PathVariable("id") Long id,
                                                             @RequestBody RoleCreateRequest request) {
        HibernateRole role = hibernateRoleDao.findById(id);
        role.setRoleName(request.getRoleName());
        role.setUser(request.getUser());
        role.setRoleId(id);

        return new ResponseEntity<>(hibernateRoleDao.update(role), HttpStatus.OK);
    }

    @DeleteMapping("/hibernate/delete/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteRoleHibernate(@PathVariable("id") Long id) {
        hibernateRoleDao.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


}
