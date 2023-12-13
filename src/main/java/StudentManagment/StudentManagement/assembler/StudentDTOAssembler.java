package StudentManagment.StudentManagement.assembler;

import StudentManagment.StudentManagement.dto.StudentDTO;
import StudentManagment.StudentManagement.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentDTOAssembler {

    @Autowired
    AddressDTOAssembler addressAssembler;
    @Autowired
    CourseDTOAssembler courseAssembler;

    public StudentDTO toDto(Student student){
        StudentDTO studentDto=new StudentDTO();
        studentDto.setId(student.getStudent_code());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        studentDto.setMobile_no(student.getMobile_no());
        studentDto.setParents_name(student.getParents_name());
        studentDto.setAddressDtos(addressAssembler.toDtos(student.getAddresses()));
        studentDto.setCourseDtos(courseAssembler.toDtos(student.getCourses()));
        return studentDto;
    }

    public Student fromDto(StudentDTO studentDto) throws Exception {
        Student student=new Student();
        student.setStudent_code(studentDto.getId());
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setMobile_no(studentDto.getMobile_no());
        student.setParents_name(studentDto.getParents_name());
        student.setAddresses(addressAssembler.fromDtos(studentDto.getAddressDtos()));
        student.setCourses(courseAssembler.fromDtos(studentDto.getCourseDtos()));
        return student;

    }
}
