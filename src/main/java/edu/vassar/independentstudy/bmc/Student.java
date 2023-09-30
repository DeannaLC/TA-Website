package edu.vassar.independentstudy.bmc;

import java.util.*;
public class Student {
    String name;
    String comments;
    int facultyPreference;
    int vassarID;
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

    public int getFacultyPreference() {
        return facultyPreference;
    }

    public void setFacultyPreference(int facultyPreference) {
        this.facultyPreference = facultyPreference;
    }

    public int getVassarID() {
        return vassarID;
    }

    public void setVassarID(int vassarID) {
        this.vassarID = vassarID;
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
            ret = ret + "Class " + i + ": Grade = " + this.classSelection.get(i).getGrade() + ", Ranking: " + this.classSelection.get(i).getClassPreference() + "\n";
        }
        return ret;
    }
}
