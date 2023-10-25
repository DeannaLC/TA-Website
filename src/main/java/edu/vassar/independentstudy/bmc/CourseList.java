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

    public void resetAllCoursePrefs(){
        for (int i = 0; i < this.courses.size(); i = i + 1){
            this.courses.get(i).resetCoursePref();
        }
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

    public CourseList filteredCopy(String name, int id){
        CourseList ret = new CourseList();
        CourseList check = this.courseListCopy();
        Course cur;
        for (int i = 0; i < check.courses.size(); i = i + 1){
            cur = check.courses.get(i);
            cur.filterWithNameID(name, id);
            ret.addCourse(cur);
        }
        return ret;
    }

    public void fixAllNames(CourseList fix){
        for (int i = 0; i < this.courses.size(); i = i + 1){
            this.courses.get(i).fixName(fix.courses.get(i));
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

    public void sortAllCoursePrefs(){
        Course cur;
        for (int i = 0; i < this.courses.size(); i = i + 1){
            cur = this.courses.get(i);
            cur.sortCoursePrefs();
        }
    }

    public void filterAllCoursePrefs(){
        Course cur;
        for (int i = 0; i < this.courses.size(); i = i + 1){
            cur = this.courses.get(i);
            cur.filterCoursePrefs();
        }
    }

    public void setAllChosenStudents(Student s){
        Course cur;
        for (int i = 0; i < this.courses.size(); i = i + 1){
            cur = this.courses.get(i);
            cur.setChosenStudents(s);
        }
    }

    /**
     * will change later to a better sorting algorithm but this for now
     *
     * least to greatest sorting for how many coursePrefs(students signed up)
     */
    public void sortBySize(){
        Course cur;
        Course min = new Course();
        int minInit;
        ArrayList<Course> cl = new ArrayList<>();
       // for (int i = 0; i < this.courses.size(); i = i + 1) {
        while (this.courses.size() != 0){
            minInit = Integer.MAX_VALUE;
            for (int k = 0; k < this.courses.size(); k = k + 1) {
                cur = this.courses.get(k);
                System.out.println(cur.toString());
                if (cur.getCoursePrefs().size() < minInit) {
                    minInit = cur.getCoursePrefs().size();
                    min = cur;
                }
            }
            cl.add(min);
            this.courses.remove(min);
        }
        //System.out.println("cl" + cl.toString());
        this.courses = cl;
    }

    public void sortCourses(){
        ArrayList<Course> cl = new ArrayList<>();
        Course min = new Course();
        Course cur;
        int minVal;
        while (this.courses.size() != 0){
            minVal = Integer.MAX_VALUE;
            for (int k = 0; k < this.courses.size(); k = k + 1) {
                cur = this.courses.get(k);
                System.out.println(cur.toString());
                if (cur.courseVal() < minVal) {
                    minVal = cur.courseVal();
                    min = cur;
                }
            }
            cl.add(min);
            this.courses.remove(min);
        }
        //System.out.println("cl" + cl.toString());
        this.courses = cl;
    }

    /**public void assignAll(){
        this.filterAllCoursePrefs();
        System.out.println(1 + this.coursesList());
        this.sortBySize();
        System.out.println(2 + this.coursesList());
        Course cur;
        Student stu1;
        Student stu2;
        for (int i = 0; i < this.courses.size(); i = i + 1){
            cur = this.courses.get(i);
            if (i != 0){
                cur.filterCoursePrefs();
            }
            cur.assignTwoStudents();
            if (cur.getCoursePrefs().size() > 0) {
                stu1 = cur.getAssigned().get(0);
                this.setAllChosenStudents(stu1);
            }
            if (cur.getCoursePrefs().size() > 1) {
                stu2 = cur.getAssigned().get(1);
                this.setAllChosenStudents(stu2);
            }
            System.out.println(3 + this.coursesList());
        }
        for (int i = 0; i < this.courses.size(); i = i + 1){
            cur = this.courses.get(i);
            if (i != 0){
                cur.filterCoursePrefs();
            }
            cur.assignOneStudent();
            if (cur.getCoursePrefs().size() > 0) {
                stu1 = cur.getAssigned().get(0);
                this.setAllChosenStudents(stu1);
            }
        }
    }**/





    public void assignAll(){
        CourseList act = this.courseListCopy();
        act.filterAllCoursePrefs();
        System.out.println(1 + act.coursesList());
        act.sortBySize();
        System.out.println(2 + act.coursesList());
        Course cur;
        Student stu1;
        Student stu2;
        for (int i = 0; i < act.courses.size(); i = i + 1){
            cur = act.courses.get(i);
            if (i != 0){
                cur.filterCoursePrefs();
            }
            cur.assignTwoStudents();
            if (cur.getCoursePrefs().size() > 0) {
                stu1 = cur.getAssigned().get(0);
                act.setAllChosenStudents(stu1);
            }
            if (cur.getCoursePrefs().size() > 1) {
                stu2 = cur.getAssigned().get(1);
                act.setAllChosenStudents(stu2);
            }
            System.out.println(3 + act.coursesList());
        }
        for (int i = 0; i < act.courses.size(); i = i + 1){
            cur = act.courses.get(i);
            if (i != 0){
                cur.filterCoursePrefs();
            }
            cur.assignOneStudent();
            if (cur.getCoursePrefs().size() > 0) {
                stu1 = cur.getAssigned().get(0);
                act.setAllChosenStudents(stu1);
            }
        }
        Course orig;
        Course transfer;
        for (int i = 0; i < act.courses.size(); i = i + 1){
            transfer = act.courses.get(i);
            orig = this.findCourse(transfer.getName(), transfer.getSection());
            orig.transferAssigned(transfer);
        }
    }

    public String allAssignedStudents(){
        String ret = "";
        for (Course course: courses){
            ret = ret + course.assignedStudents();
        }
        return ret;
    }

    public void removeByNameSection(String name, int section){
        Course remove = this.findCourse(name, section);
        this.removeCourse(remove);
    }

    public String toString(){
        String ret = "";
        for (int i = 0; i < this.courses.size(); i = i + 1){
            ret = ret + "Course Name: " + this.courses.get(i).getName() + " " + this.courses.get(i).toString();
        }
        return ret;
    }

    public String coursesList(){
        String ret = "";
        for (int i = 0; i < this.courses.size(); i = i + 1){
            ret = ret + "Course Name: " + this.courses.get(i).getName() + " ";
        }
        return ret;
    }
}
