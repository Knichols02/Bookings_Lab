package com.example.codeclan.courseBooking.controllers;

import com.example.codeclan.courseBooking.models.Course;
import com.example.codeclan.courseBooking.models.Customer;
import com.example.codeclan.courseBooking.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping(value="/courses")
    public ResponseEntity<List<Course>> getAllCourses(
            @RequestParam(name="starRating", required = false) Integer starRating,
            @RequestParam(name="customerName", required = false) String customerName
    ){
        if (starRating != null){
            return new ResponseEntity<>(courseRepository.findByStarRating(starRating), HttpStatus.OK);
        }
        if (customerName != null){
            return new ResponseEntity<>(courseRepository.findByBookingsCustomerName(customerName), HttpStatus.OK);
        }
        return new ResponseEntity<>(courseRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/courses/{id}")
    public ResponseEntity<Optional<Course>> getCourse(@PathVariable Long id){
        return new ResponseEntity<>(courseRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        courseRepository.save(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @PutMapping(value="/courses/{id}")
    public ResponseEntity<Course> putCourse(@RequestBody Course course, @PathVariable Long id){
        Course courseToUpdate = courseRepository.findById(id).get();
        courseToUpdate.setName(course.getName());
        courseToUpdate.setBookings(course.getBookings());
        courseToUpdate.setStarRating(course.getStarRating());
        courseToUpdate.setTown(course.getTown());
        courseRepository.save(courseToUpdate);
        return new ResponseEntity<>(courseToUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value="/courses/{id}")
    public ResponseEntity<Long> deleteCourse(@PathVariable Long id){
        courseRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
