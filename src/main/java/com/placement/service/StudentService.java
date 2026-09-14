package com.placement.service;

import com.placement.dto.StudentRequest;
import com.placement.dto.StudentResponse;
import com.placement.entity.Student;
import com.placement.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    StudentService(StudentRepository studentrepo){
        this.studentRepository=studentrepo;
    }

    public StudentResponse createStudent(StudentRequest request){
        Student student=new Student();

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setJoiningYear(request.getJoiningYear());
        student.setGraduationYear(request.getGraduationYear());
        student.setCgpa(request.getCgpa());
        student.setTenthPercentage(request.getTenthPercentage());
        student.setTwelfthPercentage(request.getTwelfthPercentage());
        student.setBacklogs(request.getBacklogs());
        student.setCurrentSemester(request.getCurrentSemester());
        student.setResumeUrl(request.getResumeUrl());

        Student savedStudent= studentRepository.save(student);

        StudentResponse response=new StudentResponse();

        response.setId(savedStudent.getId());
        response.setName(savedStudent.getName());
        response.setEmail(savedStudent.getEmail());
        response.setDateOfBirth(savedStudent.getDateOfBirth());
        response.setJoiningYear(savedStudent.getJoiningYear());
        response.setGraduationYear(savedStudent.getGraduationYear());
        response.setCgpa(savedStudent.getCgpa());
        response.setTenthPercentage(savedStudent.getTenthPercentage());
        response.setTwelfthPercentage(savedStudent.getTwelfthPercentage());
        response.setBacklogs(savedStudent.getBacklogs());
        response.setCurrentSemester(savedStudent.getCurrentSemester());
        response.setResumeUrl(savedStudent.getResumeUrl());

        return response;
    }
}
