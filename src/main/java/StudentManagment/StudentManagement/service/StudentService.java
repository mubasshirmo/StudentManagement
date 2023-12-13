package StudentManagment.StudentManagement.service;

import StudentManagment.StudentManagement.entity.Student;
import StudentManagment.StudentManagement.exception.StudentException;

import java.util.List;

public interface StudentService {

    //get student by id
    Student getStudentById(Integer studentId) throws StudentException;

    //add student
    Student addStudent(Student student);

    //update student
    Student updateStudent(Student student) throws Exception;

    //remove the student
    Student removeStudent(Integer studentId) throws StudentException;

    Student mapCourseToStudent(List<Integer> courseIds,Integer studentId) throws StudentException;

    Student removeCourseFromStudent(Integer studentId,List<Integer> courseIds) throws StudentException;


}
