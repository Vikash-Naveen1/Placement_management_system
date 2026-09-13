package com.placement.service;

import com.placement.entity.Student;
import com.placement.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentrepository;

    StudentService(StudentRepository studentrepo){
        this.studentrepository=studentrepo;
    }

    public Student createStudent(Student std){
        return studentrepository.save(std);
    }
}
