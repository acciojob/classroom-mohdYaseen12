package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String,Student> studentDB = new HashMap<>(); // studentName : student obj
    HashMap<String, Teacher> teacherDB = new HashMap<>(); //teacherName : teacher obj
    HashMap<String, List<String>> teacherAndItsDB = new HashMap<>(); // teacherName : list of students
    public void addStudent(Student student) {
        studentDB.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDB.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        // Adding existing pair so check if it is available or not
        if(studentDB.containsKey(student) && teacherDB.containsKey(teacher)){
            List<String> studentList = new ArrayList<>();
            if(teacherAndItsDB.containsKey(teacher)) // if the list already exist
                studentList = teacherAndItsDB.get(teacher);

            studentList.add(student); // add student in list
            teacherAndItsDB.put(teacher,studentList);
        }
    }

    public Student getStudentByName(String name) {
        if(studentDB.containsKey(name))
            return studentDB.get(name);
        else return null;
    }

    public Teacher getTeacherByName(String name) {
        if(teacherDB.containsKey(name))
            return teacherDB.get(name);
        else return null;
    }

    public List<String> getStudentByTeacherName(String teacher) {
        if(teacherAndItsDB.containsKey(teacher))
            return teacherAndItsDB.get(teacher);
        else return null;
    }

    public List<String> getAllStudents() {
        List<String> list = new ArrayList<>();
        for(String name : studentDB.keySet())
            list.add(name);
        return list;
    }

    public void deleteTeacherByName(String teacher) {
        if(teacherDB.containsKey(teacher))
            teacherDB.remove(teacher);
        if(teacherAndItsDB.containsKey(teacher)){
            List<String> list = teacherAndItsDB.get(teacher);
            for(String studentName : list)
                studentDB.remove(studentName);
            teacherAndItsDB.remove(teacher);
        }
    }

    public void deleteAllTeachers() {
        for(String teacherName : teacherDB.keySet())
            deleteTeacherByName(teacherName);
    }
}
