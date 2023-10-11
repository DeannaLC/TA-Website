package edu.vassar.independentstudy.bmc;

import java.util.ArrayList;

public class Course {
    String name;
    int section;
    String professor;
    Day labDay;
    String labStart;
    String labEnd;
    int coachAmt;
    ArrayList<CoursePref> coursePrefs = new ArrayList<>();
    CoursePref curPref = new CoursePref();

    enum Day{
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday
    }

    public Course(){};

    public void addCoursePref(CoursePref c){
        this.coursePrefs.add(c);
    }

    public void newCoursePref(){
        CoursePref add = new CoursePref();
        coursePrefs.add(add);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Day getLabDay() {
        return labDay;
    }

    public void setLabDay(Day labDay) {
        this.labDay = labDay;
    }

    public String getLabStart() {
        return labStart;
    }

    public void setLabStart(String labStart) {
        this.labStart = labStart;
    }

    public String getLabEnd() {
        return labEnd;
    }

    public void setLabEnd(String labEnd) {
        this.labEnd = labEnd;
    }

    public int getCoachAmt() {
        return coachAmt;
    }

    public void setCoachAmt(int coachAmt) {
        this.coachAmt = coachAmt;
    }

    public ArrayList<CoursePref> getCoursePrefs() {
        return coursePrefs;
    }

    public void setCoursePrefs(ArrayList<CoursePref> coursePrefs) {
        this.coursePrefs = coursePrefs;
    }

    public CoursePref getCurPref() {
        return curPref;
    }

    public void setCurPref(CoursePref curPref) {
        this.curPref = curPref;
    }

    public void setCurStudent(Student s){
        this.curPref.setStu(s);
    }

    public void addCurPref(){
        this.coursePrefs.add(this.curPref.coursePrefCopy());
    }

    public void resetCurPref(){
        this.curPref = new CoursePref();
    }

    public Course courseCopy(){
        Course ret = new Course();
        ret.setProfessor(this.professor);
        ret.setName(this.name);
        ret.setSection(this.section);
        ret.setCoursePrefs(this.coursePrefs);
        ret.setLabStart(this.labStart);
        ret.setLabDay(this.labDay);
        ret.setLabEnd(this.labEnd);
        ret.setCoachAmt(this.coachAmt);
        ret.setCurPref(this.curPref);
        return ret;
    }

    public void transferData(Course ret){
        ret.setProfessor(this.professor);
        ret.setName(this.name);
        ret.setSection(this.section);
        ret.setLabStart(this.labStart);
        ret.setLabDay(this.labDay);
        ret.setLabEnd(this.labEnd);
        ret.setCoachAmt(this.coachAmt);
    }

    public void combinePrefs(Course combine){
        for (int i = 0; i < combine.coursePrefs.size(); i = i + 1){
            this.coursePrefs.add(combine.coursePrefs.get(i));
        }
    }

    public void transferFacPrefs(Course course){
        CoursePref adding;
        CoursePref orig;
        for (int i = 0; i < this.coursePrefs.size(); i = i + 1){
            adding = course.coursePrefs.get(i);
            orig = this.coursePrefs.get(i);
            orig.setFacPref(adding.getFacPref());
        }
    }

    public void transferStuPrefs(Course course){
        CoursePref adding;
        CoursePref orig;
        for (int i = 0; i < this.coursePrefs.size(); i = i + 1){
            adding = course.coursePrefs.get(i);
            orig = this.coursePrefs.get(i);
            orig.setStuPref(adding.getStuPref());
        }
    }

    public void setFacPrefBackups(){
        CoursePref cur;
        for (int i = 0; i < this.coursePrefs.size(); i = i + 1){
            cur = this.coursePrefs.get(i);
            cur.setFacPrefBackup();
        }
    }

    public void changeFacPrefs(){
        CoursePref cur;
        for (int i = 0; i < this.coursePrefs.size(); i = i + 1){
            cur = this.coursePrefs.get(i);
            cur.changeFacPref();
        }
    }

    public void transformTime(){
        int hourStart = Integer.parseInt(labStart.substring(0, 2));
        int hourEnd = Integer.parseInt(labEnd.substring(0, 2));

        if (hourStart >= 12){
            if (hourStart > 12){
                hourStart = hourStart - 12;
            }
            labStart = "" + hourStart + labStart.substring(2, 5) + " PM";
        }
        else{
            if (labStart.substring(0, 1).equals("0")){
                labStart = labStart.substring(1, 5) + " AM";
            }
            else{
                labStart = labStart + " AM";
            }
        }

        if (hourEnd >= 12){
            if (hourEnd > 12){
                hourEnd = hourEnd - 12;
            }
            labEnd = "" + hourEnd + labEnd.substring(2, 5) + " PM";
        }
        else{
            if (labEnd.substring(0, 1).equals("0")){
                labEnd = labEnd.substring(1, 5) + " AM";
            }
            else{
                labEnd = labEnd + " AM";
            }
        }
    }

    public String toString(){
        String ret = "Course Name: " + this.name + " Section: " + this.section;
        /**CoursePref cur;
        for (int i = 0; i < this.coursePrefs.size(); i = i + 1){
            cur = this.coursePrefs.get(i);
            ret = ret + cur.toString();
        }
        return ret;**/
        return ret;
    }

}
