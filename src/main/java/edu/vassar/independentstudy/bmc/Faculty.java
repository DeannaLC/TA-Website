package edu.vassar.independentstudy.bmc;

import java.util.*;

public class Faculty {
    String name;
    String comments;
    ArrayList<Student> studentChoices;

    public Faculty(){}

    public Faculty(ArrayList<Student> studentChoices){
        this.studentChoices = studentChoices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ArrayList<Student> getStudentChoices() {
        return studentChoices;
    }

    public void setStudentChoices(ArrayList<Student> studentChoices) {
        this.studentChoices = studentChoices;
    }
}
