package StudentManagment.StudentManagement.service;

import StudentManagment.StudentManagement.entity.Address;
import StudentManagment.StudentManagement.exception.AddressException;
import StudentManagment.StudentManagement.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{


    private final AddressRepo addressRepo;
    @Autowired
    public AddressServiceImpl(AddressRepo addressRepo){
       this.addressRepo=addressRepo;
    }
    @Override
    public Address getAddressById(Integer addressId) throws AddressException {
        return addressRepo.findById(addressId).orElseThrow(()->new AddressException("Address not found this id:"+addressId));
    }

    @Override
    public Address addAddress(Address address) {
        return addressRepo.save(address);
    }

    @Override
    public Address updateAddress(Address address,Integer id) throws Exception {
        Address existingAddress=addressRepo.findById(id).orElseThrow(()-> new AddressException("Address not found for this id:"+id));
        if(existingAddress!=null){
            existingAddress.setAddressType(address.getAddressType());
            existingAddress.setArea(address.getArea());
            existingAddress.setDistrict(address.getDistrict());
            existingAddress.setState(address.getState());
            existingAddress.setPinCode(address.getPinCode());
        }
        return addressRepo.save(existingAddress);
    }

    @Override
    public Address deleteAddress(Integer addressId) {
       Address address= addressRepo.findById(addressId).orElse(null);
       if(address!=null) {
           addressRepo.delete(address);
       }
       return address;
    }


//    NOTE
    /*
    Autowired on Fields vs. Constructors:
    While using @Autowired on fields is valid, consider using constructor injection, which is generally
    considered a better practice. This promotes better testability and makes your class less dependent
    on the Spring framework.
     */
}
