package com.sumanasaha.hexagonalarchitecturejava.adapter;


import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumanasaha.hexagonalarchitecturejava.domain.Student;
import com.sumanasaha.hexagonalarchitecturejava.inboundport.StudentRestUI;
import com.sumanasaha.hexagonalarchitecturejava.inboundport.StudentService;

import lombok.extern.slf4j.Slf4j;


/**
 * @author ssaha (21.05.20)
 * <p>
 * * This class is the adapter for the Inbound port StudentService
 */

@RestController
@RequestMapping( "/student" )
public class StudentRestController implements StudentRestUI {

    private StudentService studentService;

    public StudentRestController( final StudentService studentService ) {
        this.studentService = studentService;
    }

    @Override
    public ResponseEntity<Void> addStudentDetails( @RequestBody Student student ) {

        studentService.addStudentDetails( student );

        return ok().build();

    }

    @Override
    public Student getStudentDetails( @PathVariable String studentId ) {

        return studentService.getStudentDetails( studentId );
    }

    @Override
    public List<Student> listAllStudents() {

        return studentService.listAllStudents();

    }
}
