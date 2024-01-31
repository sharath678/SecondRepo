package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	private CourseRepo courseRepo;
	
	@PostMapping("/api/courses")
	public ResponseEntity<Course> saveCourse(@RequestBody
			Course course){
		Course savedCourse=courseRepo.save(course);
		return new ResponseEntity<Course>(savedCourse,HttpStatus.CREATED);
	}
	@GetMapping("/api/courses")
	public ResponseEntity<List<Course>> getAllCourses(){
		List<Course> course=courseRepo.findAll();
		return new ResponseEntity<List<Course>>(course,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable int id){
		Optional<Course> course=courseRepo.findById(id);
		if(course.isPresent()) {
			return new ResponseEntity<Course>(course.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> DeleteCourseById(@PathVariable int id){
		Optional<Course> course=courseRepo.findById(id);
		if(course.isPresent()) {
			courseRepo.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/{id}")
	public ResponseEntity<Course> updateCourseById(@PathVariable int id,
			@RequestBody Course updatedCourse){
		Optional<Course> existingCourse=courseRepo.findById(id);
		if(existingCourse.isPresent()) {
			Course course=existingCourse.get();
			course.setName(updatedCourse.getName());
			Course savedCourse=courseRepo.save(course);
			return new ResponseEntity<Course>(savedCourse,HttpStatus.OK);
		}else {
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		}
	}
}
