package edu.vassar.independentstudy.bmc;
import org.springframework.data.annotation.Id;

public class Course{

    @Id
    String id;
    String className;
    String professor;
    int section;
    String labStart;
    String labEnd;
    Day labDay;
    int numCoaches;
    String grade;
    boolean available;
    int classPreference;
    int facultyPreference;

    public Course(String className, String professor, int section, String labStart, String labEnd, int numCoaches){
        this.className = className;
        this.professor = professor;
        this.section = section;
        this.labStart = labStart;
        this.labEnd = labEnd;
        this.numCoaches = numCoaches;
    }

    public Course courseCopy(){
        Course c = new Course();
        c.setGrade(this.grade);
        c.setAvailable(this.available);
        c.setLabStart(this.labStart);
        c.setLabEnd(this.labEnd);
        c.setLabDay(this.labDay);
        c.setClassPreference(this.classPreference);
        c.setSection(this.section);
        c.setProfessor(this.professor);
        c.setClassName(this.className);
        return c;
    }
    enum Day{
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getClassPreference() {
        return this.classPreference;
    }

    public void setClassPreference(int classPreference) {
        this.classPreference = classPreference;
    }

    public Course(){}

    public String getClassName(){
        return className;
    }

    public String getProfessor(){
        return professor;
    }

    public int getSection(){
        return section;
    }

    public int getNumCoaches(){
        return numCoaches;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public void setNumCoaches(int numCoaches) {
        this.numCoaches = numCoaches;
    }

    public String toString(){
        return String.format("%s Section 0%s \nProfessor %s \nLab: %s - %s %s\n", this.className, this.section, this.professor, this.labStart, this.labEnd, this.labDay);
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

    public Day getLabDay() {
        return labDay;
    }

    public void setLabDay(Day labDay) {
        this.labDay = labDay;
    }

    public boolean getAvailable(){
        return this.available;
    }

    public static void main(String args[]){
        String[] y = new String[]{"9:00", "11:00", "Friday"};

    }
}