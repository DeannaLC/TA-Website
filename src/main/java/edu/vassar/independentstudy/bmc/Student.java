package edu.vassar.independentstudy.bmc;

public class Student {
    String name;
    String comments = "blank";
    int vassarID;

    public Student(){}

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

    public int getVassarID() {
        return vassarID;
    }

    public void setVassarID(int vassarID) {
        this.vassarID = vassarID;
    }

    public String toString(){
        return "Name: " + this.name + " Vassar ID: " + this.vassarID + " Comments: " + this.comments;
    }
}
