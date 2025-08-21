package org.example.Contact;

import org.example.validation.Validation;

public class Contact {
    private String id;
    private String firstName;
    private String lastName;
    private String number;
    private String address;

    public Contact(String id, String firstName, String lastName, String number, String address) throws Exception {
        setId(id);


        setFirstName(firstName);
        setLastName(lastName);
        setNumber(number);
        setAddress(address);
    }

    public String getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exception {

        Validation.validateNotNull(firstName, "first name");
        Validation.validateNotBlank(firstName, "first name");
        Validation.validateLength(firstName, "first name", 1, 10);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws Exception{

        Validation.validateNotNull(lastName, "last name");
        Validation.validateNotBlank(lastName, "last name");
        Validation.validateLength(lastName, "last name", 1, 10);
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) throws Exception {
        Validation.validateNotNull(number, "number");
        Validation.validateNumber(number, "number");
        Validation.validatePhone(number, "number");

        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws Exception {
        Validation.validateNotNull(address, "address");
        Validation.validateNotBlank(address, "address");
        Validation.validateLength(address, "address", 1, 30);
        this.address = address;
    }

    private void setId(String id) throws Exception{

        Validation.validateNotNull(id, "id");
        Validation.validateNotBlank(id, "id");
        Validation.validateLength(id, "id", 1, 10);
        this.id = id;
    }
}
