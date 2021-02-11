package com.example.codeclan.courseBooking.controllers;

import com.example.codeclan.courseBooking.models.Customer;
import com.example.codeclan.courseBooking.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(value="/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestParam(name="courseName", required = false) String courseName
    ){
        if(courseName != null){
            return new ResponseEntity<>(customerRepository.findByBookingsCourseName(courseName), HttpStatus.OK);
        }
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<Optional<Customer>> getCustomer(@PathVariable Long id){
        return new ResponseEntity<>(customerRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Long> deleteCustomer(@PathVariable Long id){
        customerRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Customer> putCustomer(@RequestBody Customer customer, @PathVariable Long id){
        Customer foundCustomer = customerRepository.findById(id).get();
        foundCustomer.setName(customer.getName());
        foundCustomer.setAge(customer.getAge());
        foundCustomer.setBookings(customer.getBookings());
        foundCustomer.setTown(customer.getTown());
        customerRepository.save(foundCustomer);
        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }
}
