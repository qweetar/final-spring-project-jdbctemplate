package ru.myfirstwebsite.controller.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.myfirstwebsite.controller.request.BillCreateRequest;
import ru.myfirstwebsite.domain.HibernateBill;
import ru.myfirstwebsite.repository.hibernate.HibernateBillDao;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/bills")
public class HibernateBillController {

    @Autowired
    private HibernateBillDao hibernateBillDao;

    @GetMapping("/hibernate/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateBill>> getBillsHibernate() {
        return new ResponseEntity<>(hibernateBillDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/hibernate/by_user_id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateBill>> getBillByUserIdHibernate (Integer id) {
        return new ResponseEntity<>(hibernateBillDao.findByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/hibernate/by_id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateBill> getBillByIdHibernate(Integer id) {
        return new ResponseEntity<>(hibernateBillDao.findById(id), HttpStatus.OK);
    }

    @PostMapping("/hibernate/create")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HibernateBill> createBillHibernate(@RequestBody BillCreateRequest request) {
        HibernateBill bill = new HibernateBill();
        bill.setUserId(request.getUserId());
        bill.setPrice(request.getPrice());
        bill.setApplicationId(request.getApplicationId());

        return new ResponseEntity<>(hibernateBillDao.save(bill), HttpStatus.OK);
    }

    @RequestMapping(value = "/hibernate/update/{id}", method = RequestMethod.PUT)
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateBill> updateBillHibernate(@PathVariable("id") Integer id,
                                                             @RequestBody BillCreateRequest request) {
        HibernateBill bill = hibernateBillDao.findById(id);
        bill.setApplicationId(request.getApplicationId());
        bill.setPrice(request.getPrice());
        bill.setUserId(request.getUserId());
        bill.setBillId(id);

        return new ResponseEntity<>(hibernateBillDao.update(bill), HttpStatus.OK);
    }

    @DeleteMapping("/hibernate/delete/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteBillHibernate(@PathVariable("id")Integer id) {
        hibernateBillDao.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
