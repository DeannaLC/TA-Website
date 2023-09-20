package edu.vassar.independentstudy.bmc;

public class Course{
    String className;
    String professor;
    int section;
    //even indexes are class time in military: 1000 = 10:00 am, 13:55 = 1:55 pm
    //odd is day of the week(0 = monday, 4 = friday)
    String[] classTime = new String[4];
    String[] labTime = new String[3];
    int numCoaches;

    public Course(String className, String professor, int section, String[] classTime, String[] labTime, int numCoaches){
        this.className = className;
        this.professor = professor;
        this.section = section;
        this.classTime = classTime;
        this.labTime = labTime;
        this.numCoaches = numCoaches;
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

    public String[] getClassTime(){
        return classTime;
    }

    public String[] getLabTime(){
        return labTime;
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

    public void setClassTime(String[] classTime) {
        this.classTime = classTime;
    }

    public void setLabTime(String[] labTime) {
        this.labTime = labTime;
    }

    public void setNumCoaches(int numCoaches) {
        this.numCoaches = numCoaches;
    }

    public String toString(){
        return String.format("%s Section 0%s \nProfessor %s \nClass Times: %s - %s, %s, %s \nLab: %s - %s %s\n", this.className, this.section, this.professor, this.classTime[0], this.classTime[1], this.classTime[2], this.classTime[3], this.labTime[0], this.labTime[1], this.labTime[2]);
    }

    public static void main(String args[]){
        String[] x = new String[]{"10:30", "11:45", "Tuesday", "Thursday"};
        String[] y = new String[]{"9:00", "11:00", "Friday"};

        Course test = new Course("CMPU-101", "Smith", 2, x, y, 3);
        System.out.println(test.toString());
    }
}