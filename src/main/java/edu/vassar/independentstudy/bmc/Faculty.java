package edu.vassar.independentstudy.bmc;

import java.util.*;

public class Faculty {
    String name;
    String comments;
    ArrayList<Student> studentChoices;
    //ArrayList<Course> courses;
    //int[] studentPreferences;

    public Faculty(){}

    public Faculty(ArrayList<Student> studentChoices){
        this.studentChoices = studentChoices;
    }

    /*public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ArrayList<Student> getStudentChoices() {
        return studentChoices;
    }

    public void setStudentChoices(ArrayList<Student> studentChoices) {
        this.studentChoices = studentChoices;
    }

    /*public void setStudentPreferences(int[] studentPreferences){
        this.studentPreferences = studentPreferences;
    }

    public void setStudentPreferences(int size){
        this.studentPreferences = new int[size];
    }

    public int[] getStudentPreferences(){
        return this.studentPreferences;
    }*/

    public String toString(){
        String ret = "";
        Student cur;
        if (this.name != null)
            ret = ret + this.name;
        if (this.comments != null)
            ret = ret + " Comments: " + this.comments;
        for (int i = 0; i < this.studentChoices.size(); i = i + 1){
            cur = studentChoices.get(i);
            ret = ret + cur.toString() + "Preference: " + cur.getFacultyPreference();
        }
        return ret;
    }
}
