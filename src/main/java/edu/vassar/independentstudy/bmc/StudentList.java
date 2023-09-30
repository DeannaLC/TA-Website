package edu.vassar.independentstudy.bmc;

import java.util.*;

public class StudentList {
    ArrayList<Student> studentList = new ArrayList<Student>();

    public StudentList(){
    }

    public void addStudent(Student student){
        this.studentList.add(student);
    }

    public Student findStudent(String name){
        Student ret = null;
        Student cur;
        for (int i = 0; i < this.studentList.size(); i = i + 1) {
            cur = this.studentList.get(i);
            if (cur.getName().equals(name)) {
                ret = cur;
                break;
            }
        }
        return ret;
    }

    public void removeStudent(Student student){
        this.studentList.remove(student);
    }

    public ArrayList<Student> filteredList(String professor){
        ArrayList<Student> ret = new ArrayList<Student>();
        ArrayList<Course> studentCourses;
        Student cur;
        Course check;
        for (int i = 0; i < this.studentList.size(); i = i + 1){
            cur = this.studentList.get(i);
            studentCourses = cur.getClassSelection();
            for (int k = 0; k < studentCourses.size(); i = i + 1){
                check = studentCourses.get(k);
                if (check.getProfessor().equals(professor)) {
                    ret.add(cur);
                    break;
                }
            }
        }

        return ret;
    }
}
