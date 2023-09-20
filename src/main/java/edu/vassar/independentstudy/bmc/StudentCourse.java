package edu.vassar.independentstudy.bmc;

public class StudentCourse {
    String courseName;
    int ranking;
    char grade;

    public StudentCourse(String courseName, int ranking, char grade) {
        this.courseName = courseName;
        this.ranking = ranking;
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }
}
