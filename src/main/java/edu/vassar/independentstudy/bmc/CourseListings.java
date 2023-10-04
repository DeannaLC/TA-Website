package edu.vassar.independentstudy.bmc;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Class containing an aggregation of courses to be listed
 */
public class CourseListings{
    ArrayList<Course> courses;

    public CourseListings(){
        this.courses = new ArrayList();
    }

    public void addCourse(Course course){
        this.courses.add(course);
        //this.courseNames.add(course.className);
    }

    public ArrayList<Course> getCoursesCopy(){
        ArrayList<Course> ret = new ArrayList<>();
        Course cur;
        for (int i = 0; i < courses.size(); i = i + 1){
            cur = this.courses.get(i);
            ret.add(cur.courseCopy());
        }
        return ret;
    }

    public ArrayList<Course> getCourses(){
        return this.courses;
    }

    public void removeCourse(Course course){
        this.courses.remove(course);
    }

    public Course findCourse(String courseName, int section){
        Course ret = null;
        Course cur;
        for (int i = 0; i < this.courses.size(); i = i + 1) {
            cur = this.courses.get(i);
            if (cur.getClassName().equals(courseName) && (cur.getSection() == section)) {
                ret = cur;
                break;
            }
        }
        return ret;
    }

    public String toString(){
        String ret = "Courses: \n";
        for (int i = 0; i < this.courses.size(); i = i + 1){
            ret = "\n" + ret + courses.get(i).toString();
        }
        return ret;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
    public static void main(String args[]){
        Course a = new Course();
        Course b = new Course();
        a.setClassName("CMPU");
        b.setClassName("CMPU2");
        CourseListings x = new CourseListings();
        x.addCourse(a);
        x.addCourse(b);

        ArrayList<Course> u = x.getCoursesCopy();
        for (int i = 0; i < 2; i = i + 1){
            System.out.println(u.get(i).toString());
        }

        Student s = new Student(u);
        for (int i = 0; i < 2; i = i + 1){
            System.out.println(s.getClassSelection().get(i).toString());
        }
    }
}