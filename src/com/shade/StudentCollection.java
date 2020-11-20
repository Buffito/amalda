package com.shade;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.shade.Main.print;

// οπως και οι αλλες 2 collection classes, η student collection ειναι για να κανουμε handle λιστα και αρχεια αποθηκευσης student objects

public class StudentCollection implements Serializable {
    private List<Student> students = new ArrayList<>();
    final static long serialVersionUID = -7749941340766883545L;

    public void addStudent(Student student) {
        if (students.size() > 0){
            Iterator<Student> it = students.iterator();
            while (it.hasNext()) {
                Student s = it.next();
                if (s.getId().equals(student.getId())){
                    print("Υπαρχει ηδη.\n");
                    break;
                }else {
                    if (student.getId().length() != 5){
                        print("Ο αριθμος μητρωου δεν ειναι σωστος\n");
                        break;
                    }else {
                        students.add(student);
                        break;
                    }

                }

            }
        }else
            students.add(student);
    }

    public void deleteStudent(String id) {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getId().equals(id)){
                it.remove();
                print("Επιτυχης διαγραφη μαθητη\n");
            }
        }
    }

    public void writeToFile(File file) {
        if (students.size() > 0){
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(String.valueOf(file)));
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                for (Student s : students) {
                    objectOutputStream.writeObject(s);
                }

                fileOutputStream.close();
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public List<Student> readFromFile(File file) {
        if (file.length() != 0){
            boolean cont = true;
            try {
                FileInputStream fileInputStream = new FileInputStream((String.valueOf(file)));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                while(cont){
                    Student s=null;
                    try {
                        s = (Student) objectInputStream.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if(s != null)
                        students.add(s);
                    else
                        cont = false;
                }
                fileInputStream.close();
                objectInputStream.close();
            } catch (IOException e){
                // e.printStackTrace();
            }
        }else
            print("Αδειο αρχειο.\n");

        return students;
    }

    public Student getStudent(String id){
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getId().equals(id)){
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return students.toString();
    }
}
