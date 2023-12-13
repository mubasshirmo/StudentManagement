package StudentManagment.StudentManagement.controller;

import StudentManagment.StudentManagement.assembler.CourseDTOAssembler;
import StudentManagment.StudentManagement.dto.CourseDTO;
import StudentManagment.StudentManagement.entity.Course;
import StudentManagment.StudentManagement.exception.CourseException;
import StudentManagment.StudentManagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    CourseDTOAssembler courseAssembler;

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Integer courseId) throws CourseException {
        Course course=courseService.getCourseById(courseId);
        return new ResponseEntity<>(courseAssembler.toDto(course), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDto){
        Course course=courseAssembler.fromDto(courseDto);
        course=courseService.addCourse(course);
        return new ResponseEntity<>(courseAssembler.toDto(course),HttpStatus.CREATED);
    }
    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDto,@PathVariable Integer courseId){
        Course course=courseAssembler.fromDto(courseDto);
        course=courseService.updateCourse(course,courseId);
        return new ResponseEntity<>(courseAssembler.toDto(course),HttpStatus.CREATED);
    }
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> removeCourse(@PathVariable Integer courseId) throws CourseException {
        Course course=courseService.removeCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getCourse(@RequestParam String courseName){
        List<Course> courses=courseService.getCourse(courseName);
        return new ResponseEntity<>(courseAssembler.toDtos(courses),HttpStatus.OK);
    }
}
