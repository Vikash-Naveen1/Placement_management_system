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

    public StudentService(StudentRepository studentrepo){
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

    public StudentResponse updateById(Long id,StudentRequest req){
        Optional<Student> student=studentRepository.findById(id);

        if(student.isPresent()){
            Student s=student.get();
            s.setName(req.getName());
            s.setEmail(req.getEmail());
            s.setDateOfBirth(req.getDateOfBirth());
            s.setJoiningYear(req.getJoiningYear());
            s.setGraduationYear(req.getGraduationYear());
            s.setCgpa(req.getCgpa());
            s.setTenthPercentage(req.getTenthPercentage());
            s.setTwelfthPercentage(req.getTwelfthPercentage());
            s.setBacklogs(req.getBacklogs());
            s.setCurrentSemester(req.getCurrentSemester());
            s.setResumeUrl(req.getResumeUrl());

            Student updated=studentRepository.save(s);
            StudentResponse res=new StudentResponse();
            res.setId(updated.getId());
            res.setName(updated.getName());
            res.setEmail(updated.getEmail());
            res.setDateOfBirth(updated.getDateOfBirth());
            res.setJoiningYear(updated.getJoiningYear());
            res.setGraduationYear(updated.getGraduationYear());
            res.setCgpa(updated.getCgpa());
            res.setTenthPercentage(updated.getTenthPercentage());
            res.setTwelfthPercentage(updated.getTwelfthPercentage());
            res.setBacklogs(updated.getBacklogs());
            res.setCurrentSemester(updated.getCurrentSemester());
            res.setResumeUrl(updated.getResumeUrl());
            return res;
        }
        else{
            throw new ResourceNotFoundException("Student Not found with "+id);
        }
    }

    public void deleteStudentById(Long id){
        Optional<Student> student=studentRepository.findById(id);

        if(student.isPresent()){
            Student s=student.get();
            studentRepository.delete(s);
        }
        else{
            throw new ResourceNotFoundException("Invalid Student id "+ id);
        }
    }
}
