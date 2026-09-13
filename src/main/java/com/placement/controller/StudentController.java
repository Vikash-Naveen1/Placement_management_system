package com.placement.controller;

import com.placement.entity.Student;
import com.placement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/placement")
public class StudentController {

    private final StudentService studentService;

    StudentController(StudentService stdser){
        this.studentService=stdser;
    }

    @PostMapping("/student")
    public ResponseEntity<Student> createStd(@Valid @RequestBody Student std){
        return new ResponseEntity<Student>(studentService.createStudent(std), HttpStatus.CREATED);
    }
}
