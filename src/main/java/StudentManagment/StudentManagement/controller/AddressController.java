package StudentManagment.StudentManagement.controller;

import StudentManagment.StudentManagement.assembler.AddressDTOAssembler;
import StudentManagment.StudentManagement.dto.AddressDTO;
import StudentManagment.StudentManagement.entity.Address;
import StudentManagment.StudentManagement.exception.AddressException;
import StudentManagment.StudentManagement.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    AddressDTOAssembler addressAssembler;

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Integer addressId) throws AddressException {

        Address address=addressService.getAddressById(addressId);
        return new ResponseEntity<AddressDTO>(addressAssembler.toDto(address), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDto) throws Exception{

        Address address=addressAssembler.fromDto(addressDto);
        address=addressService.addAddress(address);
        return new ResponseEntity<AddressDTO>(addressAssembler.toDto(address), HttpStatus.CREATED);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDto,@PathVariable Integer addressId) throws Exception{

        Address address=addressAssembler.fromDto(addressDto);
        address=addressService.updateAddress(address,addressId);
        return new ResponseEntity<AddressDTO>(addressAssembler.toDto(address), HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void>removeAddress(Integer addressId) throws AddressException{

           addressService.deleteAddress(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
