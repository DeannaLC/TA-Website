package edu.vassar.independentstudy.bmc;

import com.mongodb.lang.NonNull;

import jakarta.validation.constraints.Size;



import java.util.*;
public class Student {
    String name;
    String comments;
    int facultyPreference;

    @Size(min=9, max=9)
    @NonNull
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

    public static String arrayToString(ArrayList classes){
        String ret = "Courses: \n";
        for (int i = 0; i < classes.size(); i = i + 1){
            ret = "\n" + ret + classes.get(i).toString();
        }
        return ret;
    }

    public ArrayList<Course> getCoursesCopy(){
        ArrayList<Course> ret = new ArrayList<>();
        Course cur;
        for (int i = 0; i < classSelection.size(); i = i + 1){
            cur = this.classSelection.get(i);
            ret.add(cur.courseCopy());
        }
        return ret;
    }

    public ArrayList<Course> filterCourses(String professor){
        ArrayList<Course> ret = new ArrayList<>();
        ArrayList<Course> copy = this.getCoursesCopy();
        Course cur;
        for (int i = 0; i < copy.size(); i = i + 1) {
            cur = copy.get(i).courseCopy();
            if (cur.getProfessor().equals(professor) && cur.getAvailable()){
                ret.add(cur);
            }
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
