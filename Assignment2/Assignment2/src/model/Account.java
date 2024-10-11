/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.Account to edit this template
 */
package model;

/**
 *
 * @author linyuxiao
 */
public class Account {
    
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;  
    private int age;  
    private Address homeAddress;  
    private Address workAddress;  

    public Account() {
        this.homeAddress = new Address();
        this.workAddress = new Address();
    }

   
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHomeAddress(String streetAddress, String unitNumber, String city, String state, String zipcode, String phoneNumber) {
        this.homeAddress.setStreetAddress(streetAddress);
        this.homeAddress.setUnitNumber(unitNumber);
        this.homeAddress.setCity(city);
        this.homeAddress.setState(state);
        this.homeAddress.setZipcode(zipcode);
        this.homeAddress.setPhoneNumber(phoneNumber);
    }

    
    public void setWorkAddress(String streetAddress, String unitNumber, String city, String state, String zipcode, String phoneNumber) {
        this.workAddress.setStreetAddress(streetAddress);
        this.workAddress.setUnitNumber(unitNumber);
        this.workAddress.setCity(city);
        this.workAddress.setState(state);
        this.workAddress.setZipcode(zipcode);
        this.workAddress.setPhoneNumber(phoneNumber);
    }
    
    
    public String[] getHomeAddressDetails() {
        return new String[] {
            homeAddress.getStreetAddress(),
            homeAddress.getUnitNumber(),
            homeAddress.getCity(),
            homeAddress.getState(),
            homeAddress.getZipcode(),
            homeAddress.getPhoneNumber()
        };
    }
    
    public String[] getWorkAddressDetails() {
        return new String[] {
            workAddress.getStreetAddress(),
            workAddress.getUnitNumber(),
            workAddress.getCity(),
            workAddress.getState(),
            workAddress.getZipcode(),
            workAddress.getPhoneNumber()
        };
    }

    

    
    private class Address {
        private String streetAddress;
        private String unitNumber;
        private String city;
        private String state;
        private String zipcode;
        private String phoneNumber;

        
        public String getStreetAddress() {
            return streetAddress;
        }

        public void setStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
        }

        public String getUnitNumber() {
            return unitNumber;
        }

        public void setUnitNumber(String unitNumber) {
            this.unitNumber = unitNumber;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }
}
