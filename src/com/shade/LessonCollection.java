package com.shade;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.shade.Main.print;

//κλασση για τον χειρισμο των lesson objects
public class LessonCollection implements Serializable {
    private List<Lesson> lessons = new ArrayList<>();
    final static long serialVersionUID = 9095878634617707957L;

    // βαζει ενα μαθημα στη λιστα για να καταγραφει στο αρχειο

    public void addLesson(Lesson lesson) {
        if (lessons.size() > 0){
            Iterator<Lesson> it = lessons.iterator();
            while (it.hasNext()) {
                Lesson l = it.next();
                if (l.getId().equals(lesson.getId())){
                    print("Υπαρχει ηδη.\n");
                    break;
                }else {
                    if (lesson.getId().length() != 5){
                        print("Ο κωδικος μαθηματος δεν ειναι σωστος\n");
                        break;
                    }else {
                        lessons.add(lesson);
                        break;
                    }

                }

            }
        }else
            lessons.add(lesson);
    }

    // διαγραφει μαθημα απο τη λιστα και το αρχειο

    public void deleteLesson(String id) {
        Iterator<Lesson> it = lessons.iterator();
        while (it.hasNext()) {
            Lesson l = it.next();
            if (l.getId().equals(id)){
                it.remove();
                print("Επιτυχης διαγραφη μαθηματος.\n");
            }

        }
    }

    // αποθηκευση των lesson objects με serialization
    // αν και πιο περιεργος τροπος αποθηκευσηςς, μας κανει πιο ευκολη το print

    public void writeToFile(File file) {
        if (lessons.size() > 0){
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(String.valueOf(file)));
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                for (Lesson l : lessons) {
                    objectOutputStream.writeObject(l);
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

    // διαβαζουμε απο το αρχειο με serialization τα lesson objects

    public List<Lesson> readFromFile(File file) {
        if (file.length() != 0){
            boolean cont = true;
            try {
                FileInputStream fileInputStream = new FileInputStream((String.valueOf(file)));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                while(cont){
                    Lesson l = null;
                    try {
                        l = (Lesson) objectInputStream.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if(l != null)
                        lessons.add(l);
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

        return lessons;
    }

    // απλη μεθοδος που με id μας βρησκει το αντιστοιχο lesson απο τη λιστα

    public Lesson getLesson(String id){
        Iterator<Lesson> it = lessons.iterator();
        while (it.hasNext()) {
            Lesson l = it.next();
            if (l.getId().equals(id)){
                return l;
            }

        }
        return null;
    }

    @Override
    public String toString() {
        return lessons.toString();
    }
}
