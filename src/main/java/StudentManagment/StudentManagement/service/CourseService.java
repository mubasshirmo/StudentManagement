package StudentManagment.StudentManagement.service;

import StudentManagment.StudentManagement.entity.Course;
import StudentManagment.StudentManagement.exception.CourseException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourseService {


    Course getCourseById(Integer courseId) throws CourseException;

    List<Course> getAllCourse();

    Course addCourse(Course course);

    Course updateCourse(Course course,Integer courseId);

    Course removeCourse(Integer courseId) throws CourseException;

    List<Course> getCourse(String CourseName);
}
