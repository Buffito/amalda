package com.shade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
// κλασση student με getter και setter που κανει extend την κλασση person γιατι ειμαι pleb και βαριεμαι να γραφω παραπανω κωδικα
public class Student extends Person implements Serializable{
    // αριθμος που χρησιμοποιειται απο τον compiler για το serialization
    final static long serialVersionUID = -7749941340766883545L;
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private String semester;
    static List<Lesson> lessons = new ArrayList<>();

    public Student(String id, String name, String phoneNumber, String email) {
        super(id,name, phoneNumber, email);
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }


    @Override
    public String toString() {
        return new StringBuffer("Αριθμός μητρώου: ").append(getId()).
                append(" Ονοματεπώνυμο: ").append(getName()).
                append(" Τηλέφωνο: ").append(getPhoneNumber()).
                append(" Email: ").append(getEmail()).
                append(" Εξάμηνο: ").append(getSemester()).
                append("\n").toString();

    }
}
