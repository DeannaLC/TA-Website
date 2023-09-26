package edu.vassar.independentstudy.bmc;

import java.sql.Array;
import java.util.*;
public class Student {
    String name;
    String comments;
    ArrayList<Course> classSelection = new ArrayList<Course>();

    public Student(String name){
        this.name = name.toLowerCase();
    }

    public Student(){}

    public Student(ArrayList<Course> classSelection){
        this.classSelection = classSelection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Course> getClassSelection() {
        return classSelection;
    }

    public void setClassSelection(ArrayList<Course> classSelection) {
        this.classSelection = classSelection;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public static String courseArrayToString(ArrayList<Course> classes){
        String ret = "Courses: \n";
        for (int i = 0; i < classes.size(); i = i + 1){
            ret = "\n" + ret + classes.get(i).toString();
        }
        return ret;
    }

    public String toString(){
        String ret = "Name: " + this.name + ", Comments: " + this.comments;
        for (int i = 0; i < this.classSelection.size(); i = i + 1){
            ret = ret + "Class " + i + ": Grade = " + this.classSelection.get(i).getGrade() + ", Ranking: " + this.classSelection.get(i).getPreference() + "\n";
        }
        return ret;
    }
}
