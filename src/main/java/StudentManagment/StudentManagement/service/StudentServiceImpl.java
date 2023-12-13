package StudentManagment.StudentManagement.service;

import StudentManagment.StudentManagement.entity.Course;
import StudentManagment.StudentManagement.entity.Student;
import StudentManagment.StudentManagement.exception.StudentException;
import StudentManagment.StudentManagement.repo.CourseRepo;
import StudentManagment.StudentManagement.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private CourseRepo courseRepo;
    @Override
    public Student getStudentById(Integer studentId) throws StudentException {
        return studentRepo.findById(studentId).orElseThrow(()-> new StudentException("Student not found with id:"+studentId));
    }

    @Override
    public Student addStudent(Student student) {
        Student newStudent=studentRepo.save(student);
        return newStudent;
    }

    @Override
    public Student updateStudent(Student student) throws Exception {
        Student existingStudent=studentRepo.findById(student.getStudent_code()).orElseThrow(()-> new StudentException("Student not found with ID:"+ student.getStudent_code()));

        existingStudent.setName(student.getName());
        existingStudent.setGender(student.getGender());
        existingStudent.setDate_of_birth(student.getDate_of_birth());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setMobile_no(student.getMobile_no());
        existingStudent.setParents_name(student.getParents_name());
        // Update other fields...

        // Save and return the updated student
        return studentRepo.save(existingStudent);
    }

    @Override
    public Student removeStudent(Integer studentId) throws StudentException {
      Student student=getStudentById(studentId);
      studentRepo.delete(student);
      return student;
    }

    @Override
    public Student mapCourseToStudent(List<Integer> courseIds, Integer studentId) throws StudentException {
        Student student=getStudentById(studentId);
        List<Course> courses=courseRepo.findAllById(courseIds);
        student.getCourses().addAll(courses);
        return studentRepo.save(student);
    }

    @Override
    public Student removeCourseFromStudent(Integer studentId, List<Integer> courseIds) throws StudentException {
        Student student=getStudentById(studentId);
        List<Course> courses=student.getCourses();
        for(int i=0;i<courses.size();i++){
            for(int j=0;j<courseIds.size();j++){
                if(courseIds.get(j)==courses.get(i).getId()){
                    courses.remove(i);
                }
            }
        }
        return studentRepo.save(student);
    }
}
