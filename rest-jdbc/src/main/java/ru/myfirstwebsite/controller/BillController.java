package ru.myfirstwebsite.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.myfirstwebsite.controller.requests.BillCreateRequest;
import ru.myfirstwebsite.domain.Bill;
import ru.myfirstwebsite.repository.BillDao;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/bills")
public class BillController {

    @Autowired
    private BillDao billDao;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Bill>> getBills() {
        return new ResponseEntity<>(billDao.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Bill> getBillById(@PathVariable Integer id) {
        Bill bill = billDao.findById(id);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Bill> createBill(@RequestBody BillCreateRequest request) {
        Bill bill = new Bill();
        bill.setPrice(request.getPrice());
        bill.setApplicationId(request.getApplicationId());
        bill.setUserId(request.getUserId());

        Bill savedBill = billDao.save(bill);
        return new ResponseEntity<>(savedBill, HttpStatus.OK);
    }

    @ApiOperation(value = "Update bill by roomId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful bill update"),
            @ApiResponse(code = 400, message = "Invalid bill ID supplied"),
            @ApiResponse(code = 404, message = "HibernateBill was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Bill> updateBill(@PathVariable("id") Integer billId,
                                           @RequestBody BillCreateRequest request) {
        Bill bill = billDao.findById(billId);
        bill.setPrice(request.getPrice());
        bill.setApplicationId(request.getApplicationId());
        bill.setUserId(request.getUserId());

        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteBill(@PathVariable("id") Integer billId) {
        billDao.delete(billId);
        return new ResponseEntity<>(billId, HttpStatus.OK);
    }
}
