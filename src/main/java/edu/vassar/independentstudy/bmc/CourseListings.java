package edu.vassar.independentstudy.bmc;

import java.util.*;

/**
 * Class containing an aggregation of courses to be listed
 */
public class CourseListings{
    ArrayList<Course> courses;
    ArrayList<String> courseNames;

    public CourseListings(){
        this.courses = new ArrayList();
    }

    public void addCourse(Course course){
        this.courses.add(course);
        //this.courseNames.add(course.className);
    }

    public void removeCourse(Course course){
        this.courses.remove(course);
        //this.courseNames.remove(course.className);
    }

    public Course findCourse(String courseName){
        Course ret = null;
        Course cur;
        for (int i = 0; i < this.courses.size(); i = i + 1) {
            cur = this.courses.get(i);
            if (cur.getClassName().equals(courseName)) {
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

    public static void main(String args[]){
        CourseListings cl = new CourseListings();
        String[] x = new String[]{"10:30", "11:45", "Tuesday", "Thursday"};
        String[] y = new String[]{"9:00", "11:00", "Friday"};

        Course test = new Course("CMPU-101", "Smith", 2, x, y, 3);
        System.out.println(test.toString());

        String[] a = new String[]{"1:30", "2:45", "Tuesday", "Thursday"};
        String[] b = new String[]{"11:00", "1:00", "Friday"};

        Course test2 = new Course("CMPU-102", "Goodwin", 2, a, b, 3);
        System.out.println(test2.toString());

        cl.addCourse(test);
        cl.addCourse(test2);
        System.out.println(cl.toString());

        Course findRemove = cl.findCourse("CMPU-102");
        cl.removeCourse(findRemove);
        System.out.println(cl.toString());
    }
}