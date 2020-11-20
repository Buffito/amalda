package com.shade;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    // ολες οι μεταβλητες που θα χρειαστουμε
    // η αρχικοποιηση γινεται στην init

    private static File studentsFile,teachersFile,lessonsFile;
    private static StudentCollection studentCollection;
    private static TeacherCollection teacherCollection;
    private static LessonCollection lessonCollection;
    static List<Student> students;
    static List<Teacher> teachers;
    static List<Lesson> lessons;
    static Scanner scanner;
    static String tempId,tempName,tempNumber,tempEmail,temp,menu,studentMenu,teacherMenu,lessonMenu;

    public static void main(String[] args) {
        init();
        readFromFiles();
        createObjects();

        while (true) {
            readFromFiles();
            print(menu);
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) { // switch-case για τη δημιουργια του μενου
                case 1: // Φοιτητες/Εμφανιση των φοιτητων και μενου
                    print(studentCollection.toString());
                    print(studentMenu);
                    switch (Integer.parseInt(scanner.nextLine())) {
                        case 1:
                            studentInsertionMenu();
                            break;
                        case 2:
                            studentCorrectionMenu();
                            break;
                        case 3:
                            studentDeletionMenu();
                            break;
                        case 4:
                            print("Αριθμος μητρωου μαθητη:");
                            tempId = scanner.nextLine();
                            printStudentLessons(tempId);
                            break;
                        default:
                            break;
                    }
                    break;
                case 2: // Καθηγητες/Εμφανιση των καθηγητων και μενου
                    print(teacherCollection.toString());
                    print(teacherMenu);
                    switch (Integer.parseInt(scanner.nextLine())) {
                        case 1:
                            teacherInsertionMenu();
                            break;
                        case 2:
                            teacherCorrectionMenu();
                            break;
                        case 3:
                            teacherDeletionMenu();
                            break;
                        case 4:
                            print("Κωδικος καθηγητη:");
                            tempId = scanner.nextLine();
                            printTeacherLessons(tempId);
                            break;
                        default:
                            break;
                    }
                    break;
                case 3: // Μαθηματα/Εμφανιση των μαθηματων και μενου
                    print(lessonCollection.toString());
                    print(lessonMenu);
                    switch (Integer.parseInt(scanner.nextLine())) {
                        case 1:
                            lessonInsertionMenu();
                            break;
                        case 2:
                            lessonCorrectionMenu();
                            break;
                        case 3:
                            lessonDeletionMenu();
                            break;
                        default:
                            break;
                    }
                    break;
                case 4: // Μαθηματα καθηγητων
                    teacherLessons();
                    break;
                case 5:// Μαθηματα φοιτητων
                    studentLessons();
                    break;
                case 6:// Βαθμολογια φοιτητων
                    studentGrade();
                    break;
                case 7:// ΜΟ μαθηματων και φοιτητων
                    float sumLesson = 0,sumStudent = 0;
                    for (Lesson lesson:Student.lessons){
                        sumLesson += lesson.getGrade();
                        for (Student student: Lesson.students){
                            if (student.toString().contains(lesson.getId())){
                                sumStudent += lesson.getGrade();
                            }
                            sumStudent = sumStudent/Lesson.students.size();
                            print(student.getId()+ "+ " + sumStudent);
                        }
                        sumLesson = sumLesson/Student.lessons.size();
                        print(lesson.getId()+ "+ " + sumLesson);
                    }
                    
                    break;
                case 8:// εγγραφη δεδομενων στα αρχεια και εξοδος απο την εφαρμογη
                    writeToFiles();
                    System.exit(0);
                default:
                    print("Παρακαλω πληκτρολογηστε σωστο αριθμο.");
                    break;
            }
        }
    }

    // βοηθητικη μεθοδος (γιατι ποιος γραφει ολη την ωρα "System.out.println"
    public static void print(String str){
        System.out.println(str);
    }

    /*  μεθοδος για την αρχικοποιηση των μεταβλητων
        χρησιμοποιειται για να μην ειναι η main τοσο γεματη (για να φαινεται πιο καθαρη)   */
    static void init(){
        // αρχικοποιηση αρχειων και αντικειμενων
        studentsFile = new File("students.txt");
        teachersFile = new File("teachers.txt");
        lessonsFile = new File("lessons.txt");
        studentCollection = new StudentCollection();
        teacherCollection = new TeacherCollection();
        lessonCollection = new LessonCollection();
        scanner = new Scanner(System.in);

        // δημιουργεια των καταλληλων αρχειων αν δεν υπαρχουν
        try {
            studentsFile.createNewFile();
            teachersFile.createNewFile();
            lessonsFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // strings για τη δημιουργεια μενου
        menu = "\n" + "1.Φοιτητές\n" + "2.Καθηγητές\n" + "3.Μαθηματα\n" + "4.Ανάθεση μαθήματος/μαθημάτων σε καθηγητή\n" +
                "5.Ανάθεση μαθήματος/μαθημάτων σε φοιτητή\n" + "6.Καταχώρηση βαθμολογίας σε φοιτητή\n" +
                "7.Εμφάνιση στατιστικών (μέσου όρου) βαθμολογιών\n" + "8.Έξοδος από την εφαρμογή";
        studentMenu = "1.Εισαγωγή φοιτητή\n" + "2.Διόρθωση στοιχείων φοιτητή\n" + "3.Διαγραφή φοιτητή\n" + "4.Εμφανιση μαθηματων και βαθμολογιων\n";
        teacherMenu = "1.Εισαγωγή καθηγητή\n" + "2.Διόρθωση στοιχείων καθηγητή\n" + "3.Διαγραφή καθηγητή\n" + "4.Εμφανιση μαθηματων\n";
        lessonMenu = "1.Εισαγωγή μαθηματος\n" + "2.Διόρθωση στοιχείων μαθηματος\n" + "3.Διαγραφή μαθηματος\n";


    }

    // μεθοδος για την εγγραφη των δεδομενων στα αρχεια που πρεπει
    static void writeToFiles(){
        studentCollection.writeToFile(studentsFile);
        teacherCollection.writeToFile(teachersFile);
        lessonCollection.writeToFile(lessonsFile);
    }

    // αρχικοποιηση λιστων και γεμισμα τους, απο τα αρχεια, με τη χρηση readFromFile μεθοδου
    static void readFromFiles(){
        students = new ArrayList<>();
        students = studentCollection.readFromFile(studentsFile);
        teachers = new ArrayList<>();
        teachers = teacherCollection.readFromFile(teachersFile);
        lessons = new ArrayList<>();
        lessons = lessonCollection.readFromFile(lessonsFile);
    }

    // βοηθητικη μεθοδος ελεγχου για το αν ενα αντικειμενο ειναι null
    public static boolean isNull(Object obj){
        if (obj == null)
            return true;
        return false;
    }

    // μεθοδος εμφανισης μενου για τη δημιουργια φοιτητη
    static void studentInsertionMenu(){
        print("Εισαγωγή στοιχείων φοιτητή.\n");
        print("Αριθμός μητρώου:\n");
        tempId = scanner.nextLine();
        print("Ονοματεπώνυμο:\n");
        tempName = scanner.nextLine();
        print("E-mail:\n");
        tempEmail = scanner.nextLine();
        print("Τηλέφωνο:\n");
        tempNumber = scanner.nextLine();
        print("Εξάμηνο:\n");
        temp = scanner.nextLine();
        Student s = new Student(tempId,tempName,tempNumber,tempEmail);
        s.setSemester(temp);
        studentCollection.addStudent(s);

    }

    // μεθοδος εμφανισης μενου για τη διορθωση στοιχειων φοιτητη
    static void studentCorrectionMenu(){
        print("Αριθμός μητρώου για διόρθωση στοιχείων:\n");
        tempId = scanner.nextLine();
        studentCollection.deleteStudent(tempId);
        print("Ονοματεπώνυμο:\n");
        tempName = scanner.nextLine();
        print("E-mail:\n");
        tempEmail = scanner.nextLine();
        print("Τηλέφωνο:\n");
        tempNumber = scanner.nextLine();
        print("Εξάμηνο:\n");
        temp = scanner.nextLine();
        Student s = new Student(tempId,tempName,tempNumber,tempEmail);
        s.setSemester(temp);
        studentCollection.addStudent(s);
    }

    // μεθοδος για τη διαγραφη φοιτητη
    static void studentDeletionMenu(){
        print("Αριθμός μητρώου για διαγραφή φοιτητή:\n");
        tempId = scanner.nextLine();
        studentCollection.deleteStudent(tempId);
    }

    // μεθοδος για τη δημιουργια καθηγητη
    static void teacherInsertionMenu(){
        print("Εισαγωγή στοιχείων καθηγητή.\n");
        print("Κωδικός καθηγητή:\n");
        tempId = scanner.nextLine();
        print("Ονοματεπώνυμο:\n");
        tempName = scanner.nextLine();
        print("E-mail:\n");
        tempEmail = scanner.nextLine();
        print("Τηλέφωνο:\n");
        tempNumber = scanner.nextLine();
        print("Ειδικότητα:\n");
        temp = scanner.nextLine();
        Teacher t = new Teacher(tempId,tempName,tempNumber,tempEmail);
        t.setSpecialty(temp);
        teacherCollection.addTeacher(t);
    }

    // μεθοδος για τη διορθωση στοιχειων καθηγητη
    static void teacherCorrectionMenu(){
        print("Κωδικός καθηγητή για διόρθωση στοιχείων:\n");
        tempId = scanner.nextLine();
        teacherCollection.deleteTeacher(tempId);
        print("Ονοματεπώνυμο:\n");
        tempName = scanner.nextLine();
        print("E-mail:\n");
        tempEmail = scanner.nextLine();
        print("Τηλέφωνο:\n");
        tempNumber = scanner.nextLine();
        print("Ειδικότητα:\n");
        temp = scanner.nextLine();
        Teacher t = new Teacher(tempId,tempName,tempNumber,tempEmail);
        t.setSpecialty(temp);
        teacherCollection.addTeacher(t);
    }

    // μεθοδος για τη διαγραφη καθηγητη
    static void teacherDeletionMenu(){
        print("Κωδικος καθηγητη για διαγραφή:\n");
        tempId = scanner.nextLine();
        teacherCollection.deleteTeacher(tempId);
    }

    // μεθοδος για τη δημιουργια μαθηματος
    static void lessonInsertionMenu(){
        print("Εισαγωγή στοιχείων μαθηματος.\n");
        print("Κωδικός μαθηματος:\n");
        tempId = scanner.nextLine();
        print("Ονομα:\n");
        tempName = scanner.nextLine();
        print("Εξαμηνο:\n");
        temp = scanner.nextLine();
        Lesson l = new Lesson(tempId,tempName,temp);
        lessonCollection.addLesson(l);
    }

    // μεθοδος για τη διορθωση στοιχειων μαθηματος
    static void lessonCorrectionMenu(){
        print("Κωδικός μαθηματος για διόρθωση στοιχείων:\n");
        tempId = scanner.nextLine();
        lessonCollection.deleteLesson(tempId);
        print("Ονομα:\n");
        tempName = scanner.nextLine();
        print("Εξαμηνο:\n");
        temp = scanner.nextLine();
        Lesson l = new Lesson(tempId,tempName,temp);
        lessonCollection.addLesson(l);
    }

    // μεθοδος για τη διαγραφη μαθηματος
    static void lessonDeletionMenu(){
        print("Κωδικος μαθηματος για διαγραφή:\n");
        tempId = scanner.nextLine();
        lessonCollection.deleteLesson(tempId);
    }

    // μεθοδος αναθεσης μαθηματων σε φοιτητη
    static void studentLessons(){
        print("Ανάθεση μαθήματος/μαθημάτων σε φοιτητη.");
        print("Αριθμος μητρωου φοιτητη:\n");
        StudentLesson studentLesson;
        tempId = scanner.nextLine();
        Student s = studentCollection.getStudent(tempId);
        do {
            print("Κωδικός μαθηματος:\n");
            temp = scanner.nextLine();
            Lesson l = lessonCollection.getLesson(temp);
            if (isNull(s)){
                print("Δε βρεθηκε ο φοιτητης.\n");
                break;
            }else if (isNull(l) && !temp.equals("1")){
                print("Δε βρεθηκε το μαθημα.\n");
                break;
            }else if (temp.equals("1"))
                break;
            else {
                studentLesson = new StudentLesson(s,l);
            }
        }while (true);

        writeToFiles();
    }

    // μεθοδος εμφανισης των μαθηματων και βαθμολογιων για επιλεγμενο φοιτητη
    static void printStudentLessons(String id){
        try {
            Student student = studentCollection.getStudent(id);
            print("Αριθμος μητρωου φοιτητη: " + student.getId() + "\n");
            for (Lesson lesson: Student.lessons){
                print("Ονομα μαθηματος: " + lesson.getTitle() + " Βαθμολογια: " + lesson.getGrade());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    // μεθοδος αναθεσης μαθηματων σε καθηγητη
    static void teacherLessons(){
        print("Ανάθεση μαθήματος/μαθημάτων σε καθηγητή.");
        print("Κωδικός καθηγητή:\n");
        TeacherLesson teacherLesson;
        tempId = scanner.nextLine();
        Teacher t = teacherCollection.getTeacher(tempId);
        do {
            print("Κωδικός μαθηματος:\n");
            temp = scanner.nextLine();
            Lesson l = lessonCollection.getLesson(temp);
            if (isNull(t)){
                print("Δε βρεθηκε ο καθηγητης.\n");
                break;
            }else if (isNull(l) && !temp.equals("1")){
                print("Δε βρεθηκε το μαθημα.\n");
                break;
            }else if (temp.equals("1"))
                break;
            else {
                teacherLesson = new TeacherLesson(t,l);
            }
        }while (true);

        writeToFiles();
    }

    // μεθοδος εμφανισης των μαθηματων για επιλεγμενο καθηγητη
    static void printTeacherLessons(String id){
        try {
            Teacher teacher = teacherCollection.getTeacher(id);
            print("Κωδικος καθηγητη: " + teacher.getId() + "\n");
            for (Lesson lesson: Teacher.lessons){
                print("Ονομα μαθηματος: " + lesson.getTitle()+ "\n");
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    // μεθοδος αναθεσης βαθμολογιων μαθηματων σε φοιτητη
    static void studentGrade(){
        print("Καταχώρηση βαθμολογίας σε φοιτητή");
        print("Αριθμος μητρωου φοιτητη:\n");
        StudentLesson studentLesson;
        tempId = scanner.nextLine();
        Student s = studentCollection.getStudent(tempId);
        do {
            print("Κωδικός μαθηματος:\n");
            temp = scanner.nextLine();
            Lesson l = lessonCollection.getLesson(temp);
            print("Βαθμολογια:\n");
            float grade = Integer.parseInt(scanner.nextLine());
            if (isNull(s)){
                print("Δε βρεθηκε ο φοιτητης.\n");
                break;
            }else if (isNull(l) && !temp.equals("1")){
                print("Δε βρεθηκε το μαθημα.\n");
                break;
            }else if (grade < 0 || grade > 10){
                print("Η βαθμολογια δεν ηταν εγκυρη.\n");
                break;
            }else if (temp.equals("1"))
                break;
            else {
                l.setGrade(grade);
                studentLesson = new StudentLesson(s,l);
            }
        }while (true);

        writeToFiles();
    }

    // μεθοδος δημιουργιας αντικειμενων κατα την πρωτη εκτελεση της εφαρμογης
    static void createObjects(){
        if (studentsFile.length() == 0){
            Student s1 = new Student("12345","123","1234","qwe");
            s1.setSemester("1");
            Student s2 = new Student("22345","123","1234","qwe");
            s2.setSemester("1");
            Student s3 = new Student("32345","123","1234","qwe");
            s3.setSemester("1");
            Student s4 = new Student("42345","123","1234","qwe");
            s4.setSemester("1");
            studentCollection.addStudent(s1);
            studentCollection.addStudent(s2);
            studentCollection.addStudent(s3);
            studentCollection.addStudent(s4);
        }

        if (teachersFile.length() == 0){
            Teacher t1 = new Teacher("12345","123","1234","qwe");
            t1.setSpecialty("qwe");
            Teacher t2 = new Teacher("22345","123","1234","qwe");
            t2.setSpecialty("qwe");
            Teacher t3 = new Teacher("32345","123","1234","qwe");
            t3.setSpecialty("qwe");
            Teacher t4 = new Teacher("42345","123","1234","qwe");
            t4.setSpecialty("qwe");
            teacherCollection.addTeacher(t1);
            teacherCollection.addTeacher(t2);
            teacherCollection.addTeacher(t3);
            teacherCollection.addTeacher(t4);
        }

        if (lessonsFile.length() == 0){
            Lesson l1 = new Lesson("12345","qwe","1");
            Lesson l2 = new Lesson("22345","qwe","1");
            Lesson l3 = new Lesson("32345","qwe","2");
            Lesson l4 = new Lesson("42345","qwe","3");
            lessonCollection.addLesson(l1);
            lessonCollection.addLesson(l2);
            lessonCollection.addLesson(l3);
            lessonCollection.addLesson(l4);
        }
    }
}
