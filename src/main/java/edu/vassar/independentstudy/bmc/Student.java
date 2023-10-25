package edu.vassar.independentstudy.bmc;

public class Student {
    String name;
    String comments = "blank";
    int vassarID;
    boolean chosen = false;

    public Student(){}

    public void setChosen(boolean chosen){
        this.chosen = chosen;
    }

    public boolean getChosen(){
        return this.chosen;
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

    public int getVassarID() {
        return vassarID;
    }

    public void setVassarID(int vassarID) {
        this.vassarID = vassarID;
    }

    public Student studentCopy(){
        Student s = new Student();
        s.vassarID = this.vassarID;
        s.name = this.name;
        s.chosen = this.chosen;
        s.comments = this.comments;
        return s;
    }

    public String toString(){
        return "Name: " + this.name + " Vassar ID: " + this.vassarID + " Comments: " + this.comments;
    }
}
