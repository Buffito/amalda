package com.shade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// κλασση lesson με getters και setters
public class Lesson implements Serializable {
    // αριθμος που χρησιμοποιειται απο τον compiler για το serialization
    final static long serialVersionUID = 9095878634617707957L;
    private String id;
    private String title;
    private String semester;
    private float grade;
    static List<Teacher> teachers = new ArrayList<>();
    static List<Student> students = new ArrayList<>();

    public Lesson(String id, String title, String semester) {
        this.id = id;
        this.title = title;
        this.semester = semester;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }


    @Override
    public String toString() {
        return new StringBuffer("Κωδικός μαθηματος: ").append(getId()).
                append(" Τιτλος μαθηματος: ").append(getTitle()).
                append(" Εξαμηνο: ").append(getSemester()).
                append("\n").toString();

    }
}
