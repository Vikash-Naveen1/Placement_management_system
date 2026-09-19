package com.placement.controller;

import com.placement.dto.StudentRequest;
import com.placement.dto.StudentResponse;
import com.placement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/placement")
public class StudentController {

    private final StudentService studentService;

    StudentController(StudentService stdser){
        this.studentService=stdser;
    }

    @PostMapping("/student")
    public ResponseEntity<StudentResponse> createStd(@Valid @RequestBody StudentRequest stdreq){
        return new ResponseEntity<>(studentService.createStudent(stdreq), HttpStatus.CREATED);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id){
        return new ResponseEntity<>(studentService.getStudentById(id),HttpStatus.OK);
    }

    @GetMapping("/student/getAll")
    public ResponseEntity<List<StudentResponse>> getAllStudents(){
        return new ResponseEntity<>(studentService.getAllStudents(),HttpStatus.OK);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id,@Valid @RequestBody StudentRequest req){
        return new ResponseEntity<>(studentService.updateById(id,req),HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
