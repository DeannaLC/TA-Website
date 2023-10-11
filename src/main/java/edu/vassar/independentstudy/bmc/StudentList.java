package edu.vassar.independentstudy.bmc;

import java.util.ArrayList;

public class StudentList {
    ArrayList<Student> students = new ArrayList<>();

    public StudentList(){}

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void addStudent(Student add){
        this.students.add(add);
    }

    public void removeStudent(Student remove){
        this.students.remove(remove);
    }
}