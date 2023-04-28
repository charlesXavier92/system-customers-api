package org.parrolabs.api.customers.controller;

import java.util.List;

import org.parrolabs.api.customers.service.CustomerService;
import org.parrolabs.api.model.customers.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/customers")
public class CustomersController {
    public static final String GET_CUSTOMERS = "/getAll";
    public static final String GET_CUSTOMERS_ID = "/getById/{id}";
    public static final String SAVE_CUSTOMER = "/save";
    public static final String UPDATE_CUSTOMER = "/update/{id}";
    public static final String DELETE_CUSTOMER = "/delete/{id}";

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "Return all customers from data base", response = Customers.class, notes = "General Service")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(GET_CUSTOMERS)
    public ResponseEntity<List<Customers>> getAllCustomers() {
        List<Customers> result = null;
        try {
            result = customerService.getAllCustomers();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Return the Customer by Id", response = Customers.class)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(GET_CUSTOMERS_ID)
    public ResponseEntity<Customers> getCustomerById(
            @PathVariable("id") Long id) {
        Customers result = null;
        try {
            result = customerService.getCustomerById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Create the Customer", response = Customers.class)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping (SAVE_CUSTOMER)
    public ResponseEntity<Customers> saveCustomer(
            @RequestBody Customers customers) {
        Customers result = null;
        try {
            result = customerService.saveCustomer(customers);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update the Customer", response = Customers.class)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping (UPDATE_CUSTOMER)
    public ResponseEntity<Customers> updateCustomer(
            @PathVariable("id") Long id,
            @RequestBody Customers customers) {
        Customers result = null;
        try {
            result = customerService.updateCustomer(id, customers);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete the Customer", response = Customers.class)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping (DELETE_CUSTOMER)
    public ResponseEntity<Boolean> deleteCustomer(
            @PathVariable("id") Long id) {
        Boolean result = false;
        try {
            result = customerService.deleteCustomer(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
