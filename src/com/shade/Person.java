package com.shade;

import java.io.Serializable;

public class Person implements Serializable {
    // αριθμος που χρησιμοποιειται απο τον compiler για το serialization
    final static long serialVersionUID = 6833889057621585400L;

    private String id;
    private String name;
    private String phoneNumber;
    private String email;

    // person constructor

    public Person(String id, String name, String phoneNumber, String email){
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
