package com.shade;

// κλασση που βαζει σε καθε μαθημα τον καθηγητη που το διδασκει και το αναποδο
// αυτο γινεται με λιστες που εχουν δημιουργηθει στις αντιστοιχες κλασσεις Teacher και Lesson

public class TeacherLesson {
    Teacher teacher;
    Lesson lesson;

    public TeacherLesson(Teacher teacher,Lesson lesson) {
        this.lesson = lesson;
        this.teacher = teacher;

       try {
           Teacher.lessons.add(lesson);
           Lesson.teachers.add(teacher);
       }catch (NullPointerException e){
           e.printStackTrace();
       }
    }


}
