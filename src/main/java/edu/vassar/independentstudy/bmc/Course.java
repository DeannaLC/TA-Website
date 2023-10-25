package edu.vassar.independentstudy.bmc;

import com.mongodb.lang.NonNull;

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
    ArrayList<Student> assigned = new ArrayList<>();

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

    public ArrayList<Student> getAssigned(){
        return this.assigned;
    }

    public void setAssigned(ArrayList<Student> assigned){
        this.assigned = assigned;
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

    public void resetCoursePref(){
        this.coursePrefs = new ArrayList<>();
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
        ret.setAssigned(this.assigned);
        return ret;
    }

    public int coursePrefsSize(){
        return this.coursePrefs.size();
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

    /**
     * remove coursePrefs w/ students that are unavailable/not interested
     */
    public void filterCoursePrefs(){
        CoursePref cur;
        ArrayList<CoursePref> filtered = new ArrayList<>();
        for (int i = 0; i < this.coursePrefs.size(); i = i + 1){
            cur = this.coursePrefs.get(i);
            if (cur.getAvailable() && cur.getStuPref() != 0 && !cur.checkStuChosen()){
                filtered.add(cur);
            }
        }
        this.coursePrefs = filtered;
    }

    public void assignTwoStudents(){
        Course act = this.courseCopy();
        act.filterCoursePrefs();
        act.sortCoursePrefs();
        Student first;
        Student second;
        if (act.coursePrefs.size() > 0){
            first = act.coursePrefs.get(0).getStu();
            first.setChosen(true);
            this.assigned.add(first);
        }
        if (act.coursePrefs.size() > 1) {
            second = act.coursePrefs.get(1).getStu();
            second.setChosen(true);
            this.assigned.add(second);
        }
    }

    public void assignOneStudent(){
        Course act = this.courseCopy();
        act.filterCoursePrefs();
        act.sortCoursePrefs();
        Student one;
        if (act.coursePrefs.size() > 0){
            one = act.coursePrefs.get(0).getStu();
            one.setChosen(true);
            this.assigned.add(one);
        }
    }

    public void setChosenStudents(Student s){
        String name = s.getName();
        int id = s.getVassarID();
        CoursePref cur;
        for (int i = 0; i < this.coursePrefs.size(); i = i + 1){
            cur = this.coursePrefs.get(i);
            if (cur.getStu().getName().equals(name) && cur.getStu().getVassarID() == id){
                cur.getStu().setChosen(true);
                break;
            }
        }
    }

    /**
     * will switch later to better sorting algorithm
     *
     * greatest to least sorting
     */
    public void sortCoursePrefs(){
        CoursePref cur;
        CoursePref max;
        ArrayList<CoursePref> sort = new ArrayList<>();
        while (this.coursePrefs.size() != 0){
        //for (int i = 0; i < this.coursePrefs.size(); i = i + 1){
            max = new CoursePref();
            for (int k = 0; k < this.coursePrefs.size(); k = k + 1){
                cur = this.coursePrefs.get(k);
                if (cur.totalPref() > max.totalPref())
                    max = cur;
            }
            sort.add(max);
            this.coursePrefs.remove(max);
        }
        this.coursePrefs = sort;
    }

    public int courseVal(){
        return this.section + Integer.parseInt(this.name.substring(this.name.length() - 3));
    }

    public String assignedStudents(){
        String ret = this.name + ": ";
        Student s;
        for (int i = 0; i < this.assigned.size(); i = i + 1){
            s = this.assigned.get(i);
            if (i != this.assigned.size() - 1)
                ret = ret + s.getName() + ", " ;
            else
                ret = ret + s.getName();
        }
        return ret;
    }

    public void filterWithNameID(String name, int id){
        ArrayList<CoursePref> ret = new ArrayList<>();
        CoursePref cur;
        for (int i = 0; i < this.coursePrefs.size(); i = i + 1){
            cur = this.coursePrefs.get(i);
            if (cur.getStu().getName().equals(name) && cur.getStu().getVassarID() == id)
                ret.add(cur);
        }
        this.coursePrefs = ret;
    }

    public void transferAssigned(Course transfer){
        this.assigned = transfer.assigned;
    }

    public void fixName(Course course){
        this.name = course.getName();
    }

    public String toString(){
        String ret = " Section: " + this.section;
        System.out.println("SIZE" + coursePrefs.size());
        CoursePref cur;
        for (int i = 0; i < this.coursePrefs.size(); i = i + 1){
            cur = this.coursePrefs.get(i);
            ret = ret + cur.toString();
        }
        return ret;
    }

}
