package com.placement.controller;

import com.placement.dto.StudentRequest;
import com.placement.dto.StudentResponse;
import com.placement.entity.Student;
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
}
