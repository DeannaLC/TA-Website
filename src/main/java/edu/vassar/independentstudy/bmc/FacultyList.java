package edu.vassar.independentstudy.bmc;

import java.util.ArrayList;

public class FacultyList {
    ArrayList<Faculty> facultyMembers = new ArrayList<>();

    public FacultyList(){}

    public void addFaculty(Faculty faculty){
        this.facultyMembers.add(faculty);
    }

    public ArrayList<Faculty> getFacultyMembers() {
        return facultyMembers;
    }

    public void setFacultyMembers(ArrayList<Faculty> facultyMembers) {
        this.facultyMembers = facultyMembers;
    }
}
