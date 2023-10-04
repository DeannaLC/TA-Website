package edu.vassar.independentstudy.bmc;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class StudentList {
    ArrayList<Student> studentList = new ArrayList<Student>();

    public StudentList(){
    }

    public void addStudent(Student student){
        this.studentList.add(student);
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
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

    public ArrayList<Student> studentsCopy(){
        ArrayList<Student> ret = new ArrayList<>();
        Student cur;
        Student x;
        for (int i = 0; i < this.studentList.size(); i = i + 1) {
            x = new Student();
            cur = this.studentList.get(i);
            System.out.println(cur);
            x.setName(cur.getName());
            x.setClassSelection(cur.getCoursesCopy());
            x.setVassarID(cur.getVassarID());
            x.setComments(cur.getComments());
            x.setFacultyPreference(cur.getFacultyPreference());
            ret.add(x);
        }
        return ret;
    }

    public ArrayList<Student> filteredList(String professor){
        ArrayList<Student> copy = this.studentsCopy();
        ArrayList<Student> ret = new ArrayList<Student>();
        ArrayList<Course> studentCourses;
        Student cur;
        Course check;
        for (int i = 0; i < copy.size(); i = i + 1){
            cur = copy.get(i);
            studentCourses = cur.getClassSelection();
            for (int k = 0; k < studentCourses.size(); k = k + 1){
                check = studentCourses.get(k);
                if (check.getAvailable() && check.getProfessor().equals(professor)){
                    ret.add(cur);
                    break;
                }
            }
        }

        return ret;
    }

    public String toString(){
        String ret = "";
        for (int i = 0; i < this.studentList.size(); i = i + 1){
            ret = ret + this.studentList.get(i).toString();
        }
        return ret;
    }

    public static void main(String args[]){
        StudentList students = new StudentList();
        Student stu1 = new Student("hi");
        Student stu2 = new Student("hi again");

        Course a = new Course();
        Course b = new Course();
        Course c = new Course();

        a.setProfessor("a");
        b.setProfessor("a");
        c.setProfessor("a");

        a.setAvailable(false);
        a.setAvailable(true);
        a.setAvailable(true);
    }
}
