package com.shade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person implements Serializable {
    // αριθμος που χρησιμοποιειται απο τον compiler για το serialization
    final static long serialVersionUID = -7396153130328469389L;
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private String specialty;
    static List<Lesson> lessons = new ArrayList<>();

    public Teacher(String id, String name, String phoneNumber, String email) {
        super(id,name, phoneNumber, email);
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return new StringBuffer("Κωδικός καθηγητή: ").append(getId()).
                append(" Ονοματεπώνυμο: ").append(getName()).
                append(" Τηλέφωνο: ").append(getPhoneNumber()).
                append(" Email: ").append(getEmail()).
                append(" Ειδικότητα: ").append(getSpecialty()).
                append("\n").toString();

    }

}
