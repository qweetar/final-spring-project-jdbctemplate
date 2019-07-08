package ru.myfirstwebsite.controller.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.myfirstwebsite.controller.request.BillCreateRequest;
import ru.myfirstwebsite.domain.HibernateBill;
import ru.myfirstwebsite.repository.springdata.SpringdataBillRepository;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/bills")
public class SpringdataBillController {

    @Autowired
    private SpringdataBillRepository springdataBillRepository;

    @GetMapping("/springdata/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<HibernateBill>> getBillSpringdata(@ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(springdataBillRepository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/springdata/by_user_id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateBill>> getBillByUserIdSpringdata(Integer id) {
        return new ResponseEntity<>(springdataBillRepository.findByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/springdata/by_id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateBill> getBillByIdSpringdata(Integer id) {
        return new ResponseEntity<>(springdataBillRepository.findByBillId(id), HttpStatus.OK);
    }

    @PostMapping("/springdata/create")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HibernateBill> createBillSpringdata(@RequestBody BillCreateRequest request) {
        HibernateBill bill = new HibernateBill();
        bill.setUserId(request.getUserId());
        bill.setPrice(request.getPrice());
        bill.setApplicationId(request.getApplicationId());

        return new ResponseEntity<>(springdataBillRepository.save(bill), HttpStatus.OK);
    }

    @RequestMapping(value = "/springdata/update/{id}", method = RequestMethod.PUT)
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateBill> updateBillSpringdata(@PathVariable("id") Integer id,
                                                              @RequestBody BillCreateRequest request) {
        HibernateBill bill = springdataBillRepository.findByBillId(id);
        bill.setUserId(request.getUserId());
        bill.setPrice(request.getPrice());
        bill.setApplicationId(request.getApplicationId());
        bill.setBillId(id);

        return new ResponseEntity<>(springdataBillRepository.save(bill), HttpStatus.OK);
    }

    @DeleteMapping("/springdata/delete/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteBillSpringdata(@PathVariable("id") Integer id) {
        HibernateBill bill = springdataBillRepository.findByBillId(id);
        springdataBillRepository.delete(bill);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
