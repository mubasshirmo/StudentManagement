package StudentManagment.StudentManagement.controller;

import StudentManagment.StudentManagement.assembler.CourseDTOAssembler;
import StudentManagment.StudentManagement.assembler.StudentDTOAssembler;
import StudentManagment.StudentManagement.dto.CourseDTO;
import StudentManagment.StudentManagement.dto.StudentDTO;
import StudentManagment.StudentManagement.entity.Course;
import StudentManagment.StudentManagement.entity.Student;
import StudentManagment.StudentManagement.service.CourseService;
import StudentManagment.StudentManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    StudentDTOAssembler studentAssembler;

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@RequestParam Integer studentId) throws Exception{

        Student student=studentService.getStudentById(studentId);
        return new ResponseEntity<StudentDTO>(studentAssembler.toDto(student), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDto) throws Exception {

        Student student=studentAssembler.fromDto(studentDto);
        student=studentService.addStudent(student);
        return new ResponseEntity<StudentDTO>(studentAssembler.toDto(student),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDto) throws Exception{

        Student student=studentAssembler.fromDto(studentDto);
        student=studentService.updateStudent(student);
        return new ResponseEntity<StudentDTO>(studentAssembler.toDto(student),HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> removeStudent(@RequestParam Integer studentId) throws Exception {

        studentService.removeStudent(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/map-courses")
    public ResponseEntity<StudentDTO> mapCoursesToStudent(@RequestParam List<Integer> courseIds,@RequestParam Integer studentId) throws Exception{

        Student student=studentService.mapCourseToStudent(courseIds, studentId);
        return new ResponseEntity<StudentDTO>(studentAssembler.toDto(student), HttpStatus.OK);
    }

    @DeleteMapping("/remove-courses")
    public ResponseEntity<StudentDTO> removeCourseFromStudent(@RequestParam  Integer studentId,@RequestParam List<Integer> courseIds) throws Exception{

        Student student=studentService.removeCourseFromStudent(studentId, courseIds);
        return new ResponseEntity<StudentDTO>(studentAssembler.toDto(student), HttpStatus.OK);
    }

}
