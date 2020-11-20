package com.shade;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.shade.Main.print;

// οπως και οι αλλες 2 collection classes, η teacher collection ειναι για να κανουμε handle λιστα και αρχεια αποθηκευσης teacher objects

public class TeacherCollection implements Serializable{
    private List<Teacher> teachers = new ArrayList<>();
    final static long serialVersionUID = -7396153130328469389L;

    public void addTeacher(Teacher teacher) {
        if (teachers.size() > 0){
            Iterator<Teacher> it = teachers.iterator();
            while (it.hasNext()) {
                Teacher t = it.next();
                if (t.getId().equals(teacher.getId())){
                    print("Υπαρχει ηδη.\n");
                    break;
                }else {
                    if (teacher.getId().length() != 5){
                        print("Ο κωδικος καθηγητη δεν ειναι σωστος\n");
                        break;
                    }else {
                        teachers.add(teacher);
                        break;
                    }

                }

            }
        }else
            teachers.add(teacher);
    }

    public void deleteTeacher(String id) {
        Iterator<Teacher> it = teachers.iterator();
        while (it.hasNext()) {
            Teacher t = it.next();
            if (t.getId().equals(id)){
                it.remove();
                print("Επιτυχης διαγραφη καθηγητη.\n");
            }

        }

    }

    public void writeToFile(File file) {
        if (teachers.size() > 0){
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(String.valueOf(file)));
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                for (Teacher t : teachers) {
                    objectOutputStream.writeObject(t);
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

    public List<Teacher> readFromFile(File file) {
        if (file.length() != 0){
            boolean cont = true;
            try {
                FileInputStream fileInputStream = new FileInputStream((String.valueOf(file)));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                while(cont){
                    Teacher t = null;
                    try {
                        t = (Teacher) objectInputStream.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if(t != null)
                        teachers.add(t);
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

        return teachers;
    }

    public Teacher getTeacher(String id){
        Iterator<Teacher> it = teachers.iterator();
        while (it.hasNext()) {
            Teacher t = it.next();
            if (t.getId().equals(id)){
                return t;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return teachers.toString();
    }
}
