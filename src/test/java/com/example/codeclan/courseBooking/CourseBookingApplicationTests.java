package com.example.codeclan.courseBooking;

import com.example.codeclan.courseBooking.models.Booking;
import com.example.codeclan.courseBooking.models.Course;
import com.example.codeclan.courseBooking.models.Customer;
import com.example.codeclan.courseBooking.repositories.BookingRepository;
import com.example.codeclan.courseBooking.repositories.CourseRepository;
import com.example.codeclan.courseBooking.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class CourseBookingApplicationTests {


	@Autowired
	CourseRepository courseRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BookingRepository bookingRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void canFindAllCourses(){
		List<Course> found = courseRepository.findAll();
		assertEquals(3, found.size());
	}

	@Test
	public void canFindCoursesForStarRating(){
		List<Course> found = courseRepository.findByStarRating(4);
		assertEquals(1, found.size());
		assertEquals("Java For Dafties", found.get(0).getName());
	}

	@Test
	public void canFindAllCustomersForCourseSarcasm101(){
		List<Customer> found = customerRepository.findByBookingsCourseName("Sarcasm 101");
		assertEquals(1, found.size());
		assertEquals("Saerlaith", found.get(0).getName());
	}

	@Test
	public void canGetAllBookingsForADate() {
		List<Booking> found = bookingRepository.findByDate("01-01-2021");
		assertEquals(1, found.size());
	}
}
