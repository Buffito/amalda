package com.shade;

// κλασση που βαζει σε καθε μαθημα το φοιτητη που το διδασκεται και το αναποδο
// αυτο γινεται με λιστες που εχουν δημιουργηθει στις αντιστοιχες κλασσεις Student και Lesson

public class StudentLesson {
    Student student;
    Lesson lesson;

    public StudentLesson(Student student, Lesson lesson){
        this.lesson = lesson;
        this.student = student;

        try {
            Student.lessons.add(lesson);
            Lesson.students.add(student);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }


}
