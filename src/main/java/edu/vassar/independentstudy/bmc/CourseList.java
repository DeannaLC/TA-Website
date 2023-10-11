package edu.vassar.independentstudy.bmc;

import java.util.ArrayList;
import java.util.List;

public class CourseList {
    List<Course> courses = new ArrayList<>();

    public CourseList(){}

    public void addCourse(Course add){
        this.courses.add(add);
    }

    public void removeCourse(Course remove){
        this.courses.remove(remove);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public CourseList courseListCopy(){
        CourseList ret = new CourseList();
        Course cur;
        for (int i = 0; i < this.courses.size(); i = i + 1){
            cur = courses.get(i);
            ret.addCourse(cur.courseCopy());
        }
        return ret;
    }

    public void addCurPrefs(){
        for (int i = 0; i < courses.size(); i = i + 1){
            this.courses.get(i).addCurPref();
        }
    }

    public void setAllCurPrefs(){
        Course cur;
        for (int i = 0; i < this.courses.size(); i = i + 1){
            cur = this.courses.get(i);
            cur.resetCurPref();
        }
    }

    public void setStudents(Student s){
        Course cur;
        for (int i = 0; i < this.courses.size(); i = i + 1){
            cur = this.courses.get(i);
            cur.setCurStudent(s);
        }
    }

    public Course findCourse(String courseName, int section){
        Course ret = null;
        Course cur;
        for (int i = 0; i < this.courses.size(); i = i + 1) {
            cur = this.courses.get(i);
            if (cur.getName().equals(courseName) && (cur.getSection() == section)) {
                ret = cur;
                break;
            }
        }
        return ret;
    }

    public void transferAllData(CourseList courseList){
        Course orig;
        Course transfer;
        for (int i = 0; i < this.courses.size(); i = i + 1){
            orig = this.courses.get(i);
            transfer = courseList.courses.get(i);
            orig.transferData(transfer);
        }
    }

    public void transferAllStuPrefs(CourseList combine){
        Course orig;
        Course transfer;
        for (int i = 0; i < combine.courses.size(); i = i + 1) {
            orig = this.courses.get(i);
            transfer = combine.courses.get(i);
            orig.transferStuPrefs(transfer);
        }
    }

    public void transferAllFacPrefs(CourseList combine){
        Course orig;
        Course transfer;
        for (int i = 0; i < combine.courses.size(); i = i + 1) {
            orig = this.courses.get(i);
            transfer = combine.courses.get(i);
            orig.transferFacPrefs(transfer);
        }
    }

    public void combineAllPrefs(CourseList combine){
        Course orig;
        Course transfer;
        for (int i = 0; i < combine.courses.size(); i = i + 1){
            orig = this.courses.get(i);
            transfer = combine.courses.get(i);
            orig.combinePrefs(transfer);
        }
    }

    public void setAllFacPrefBackups(){
        Course cur;
        for (int i = 0; i < this.courses.size(); i = i + 1){
            cur = this.courses.get(i);
            cur.setFacPrefBackups();
        }
    }

    public void changeAllFacPrefs(){
        Course cur;
        for (int i = 0; i < this.courses.size(); i = i + 1){
            cur = this.courses.get(i);
            cur.changeFacPrefs();
        }
    }

    public String toString(){
        String ret = "";
        for (int i = 0; i < this.courses.size(); i = i + 1){
            ret = ret + "Course Name: " + this.courses.get(i).getName() + " " + this.courses.get(i).toString();
        }
        return ret;
    }
}
