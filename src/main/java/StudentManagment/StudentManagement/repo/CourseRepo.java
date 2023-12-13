package StudentManagment.StudentManagement.repo;

import StudentManagment.StudentManagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course,Integer> {
    List<Course> findByCourseName(String name);
}
