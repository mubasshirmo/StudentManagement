package StudentManagment.StudentManagement.service;

import StudentManagment.StudentManagement.entity.Address;
import StudentManagment.StudentManagement.exception.AddressException;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    Address getAddressById(Integer addressId) throws AddressException;

    Address addAddress(Address address);

    Address updateAddress(Address address,Integer id) throws Exception;

    Address deleteAddress(Integer addressId);

}
