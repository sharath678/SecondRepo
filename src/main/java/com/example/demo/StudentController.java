package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
public class StudentController {

	@Autowired
	private StudentRepo studentRepo;
	
	@PostMapping("/api/students")
	public ResponseEntity<Student> saveStudent(@RequestBody
			Student student){
		Student savedStudent=studentRepo.save(student);
		return new ResponseEntity<Student>(savedStudent,HttpStatus.CREATED);
	}
	@GetMapping("/api/courses")
	public ResponseEntity<List<Student>> getAllStudents(){
		List<Student> student=studentRepo.findAll();
		return new ResponseEntity<List<Student>>(student,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Student> getCourseById(@PathVariable int id){
		Optional<Student> student=studentRepo.findById(id);
		if(student.isPresent()) {
			return new ResponseEntity<Student>(student.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> DeleteCourseById(@PathVariable int id){
		Optional<Student> student=studentRepo.findById(id);
		if(student.isPresent()) {
			studentRepo.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody
	Student updatedStudent) {
	Optional<Student> existingStudent = studentRepo.findById(id);

	if (existingStudent.isPresent()) {
	Student student = existingStudent.get();
	student.setName(updatedStudent.getName());
	student.setDob(updatedStudent.getDob());
	student.setClassess(updatedStudent.getClassess());
	student.setDivision(updatedStudent.getDivision());
	student.setGender(updatedStudent.getGender());
	Student savedStudent = studentRepo.save(student);
	return new ResponseEntity<Student>(savedStudent, HttpStatus.OK);
	} else {
	return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
	}
	}

}
