package StudentManagment.StudentManagement.repo;

import StudentManagment.StudentManagement.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Integer> {
}
