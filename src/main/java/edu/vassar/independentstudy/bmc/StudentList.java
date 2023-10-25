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

    public Student findByNameId(String name, int id){
        Student found = null;
        Student cur;
        for (int i = 0; i < this.students.size(); i = i + 1){
            cur = this.students.get(i);
            if (cur.getName().equals(name) && cur.vassarID == id){
                found = cur;
                break;
            }
        }
        return found;
    }

    public void removeByNameId(String name, int id){
        Student found = null;
        Student cur;
        for (int i = 0; i < this.students.size(); i = i + 1){
            cur = this.students.get(i);
            if (cur.getName().equals(name) && cur.vassarID == id){
                found = cur;
                break;
            }
        }
        if (found != null)
            this.students.remove(found);
    }

    public void removeStudent(Student remove){
        this.students.remove(remove);
    }
}