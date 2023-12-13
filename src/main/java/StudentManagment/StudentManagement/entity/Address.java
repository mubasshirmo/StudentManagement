package StudentManagment.StudentManagement.entity;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Integer id;
    private String area;
    private String state;
    private String district;
    private String pinCode;
    private String addressType;

    @ManyToOne
    private Student student;

    public Address(){

    }

    public Address(Integer id, String area, String state, String district, String pinCode, String addressType, Student student) {
        this.id = id;
        this.area = area;
        this.state = state;
        this.district = district;
        this.pinCode = pinCode;
        this.addressType = addressType;
        this.student = student;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) throws Exception {
        //pinCode must have 6digit from 0 to 9
        if(pinCode.matches("[0-9]{6}")){
            this.pinCode = pinCode;
        }else{
            throw new Exception("pinCode should have 6 numbers!!");
        }

    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
