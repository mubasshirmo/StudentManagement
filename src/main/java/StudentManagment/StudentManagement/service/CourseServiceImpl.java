package StudentManagment.StudentManagement.service;

import StudentManagment.StudentManagement.entity.Course;
import StudentManagment.StudentManagement.exception.CourseException;
import StudentManagment.StudentManagement.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    CourseRepo courseRepo;
    @Override
    public Course getCourseById(Integer courseId) throws CourseException {
        return courseRepo.findById(courseId).orElseThrow(()->new CourseException("Course is not found with id:"+courseId));
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepo.findAll();
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public Course updateCourse(Course course,Integer courseId) {
        Course existingCourse=courseRepo.findById(courseId).get();
        if(existingCourse!=null){
            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setCourseType(course.getCourseType());
            existingCourse.setDuration(course.getDuration());
        }
        return courseRepo.save(existingCourse);
    }

    @Override
    public Course removeCourse(Integer courseId) throws CourseException {
        Course course=getCourseById(courseId);
        courseRepo.delete(course);
        return course;
    }

    @Override
    public List<Course> getCourse(String courseName) {
        return courseRepo.findByCourseName(courseName);
    }
}
