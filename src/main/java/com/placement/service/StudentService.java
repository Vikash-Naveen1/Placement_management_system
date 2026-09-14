package com.placement.service;

import com.placement.dto.StudentRequest;
import com.placement.dto.StudentResponse;
import com.placement.entity.Student;
import com.placement.exception.ResourceNotFoundException;
import com.placement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public StudentResponse getStudentById(Long id){
        Optional<Student> student=studentRepository.findById(id);

        if(student.isPresent()){
            Student s=student.get();
            StudentResponse res=new StudentResponse();
            res.setId(s.getId());
            res.setName(s.getName());
            res.setEmail(s.getEmail());
            res.setDateOfBirth(s.getDateOfBirth());
            res.setJoiningYear(s.getJoiningYear());
            res.setGraduationYear(s.getGraduationYear());
            res.setCgpa(s.getCgpa());
            res.setTenthPercentage(s.getTenthPercentage());
            res.setTwelfthPercentage(s.getTwelfthPercentage());
            res.setBacklogs(s.getBacklogs());
            res.setCurrentSemester(s.getCurrentSemester());
            res.setResumeUrl(s.getResumeUrl());
            return res;
        }
        else{
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
    }

    public List<StudentResponse> getAllStudents(){
        List<Student> student=studentRepository.findAll();
        List<StudentResponse> response=new ArrayList<>();
        int i=0;
        while(i<student.size()){
            Student s=student.get(i);
            StudentResponse res=new StudentResponse();
            res.setId(s.getId());
            res.setName(s.getName());
            res.setEmail(s.getEmail());
            res.setDateOfBirth(s.getDateOfBirth());
            res.setJoiningYear(s.getJoiningYear());
            res.setGraduationYear(s.getGraduationYear());
            res.setCgpa(s.getCgpa());
            res.setTenthPercentage(s.getTenthPercentage());
            res.setTwelfthPercentage(s.getTwelfthPercentage());
            res.setBacklogs(s.getBacklogs());
            res.setCurrentSemester(s.getCurrentSemester());
            res.setResumeUrl(s.getResumeUrl());
            response.add(res);
            i++;
        }
        return response;
    }
}
