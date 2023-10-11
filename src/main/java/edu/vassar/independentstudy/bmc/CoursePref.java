package edu.vassar.independentstudy.bmc;
public class CoursePref {
    boolean available;
    Grade grade;
    int stuPref;
    int facPref = 0;
    int facPrefBackup = 0;
    Student stu;

    enum Grade{
        A,
        B,
        C,
        D,
        F
    }

    public CoursePref(){}

    public CoursePref(Student stu){
        this.stu = stu;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public int getStuPref() {
        return stuPref;
    }

    public void setStuPref(int stuPref) {
        this.stuPref = stuPref;
    }

    public int getFacPref() {
        return facPref;
    }

    public void setFacPref(int facPref) {
        this.facPref = facPref;
    }

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    public CoursePref coursePrefCopy(){
        CoursePref ret = new CoursePref();
        ret.setAvailable(available);
        ret.setGrade(grade);
        ret.setFacPref(facPref);
        ret.setStuPref(stuPref);
        ret.setStu(stu);
        return ret;
    }

    public void setFacPrefBackup(){
        this.facPrefBackup = this.facPref;
    }

    public void changeFacPref(){
        if (this.facPref == 0 && this.facPrefBackup != 0){
            this.facPref = this.facPrefBackup;
        }
    }


    public String toString(){
        return "Student Preference: " + this.stuPref + " Faculty Preference: " + this.facPref;
    }
}
