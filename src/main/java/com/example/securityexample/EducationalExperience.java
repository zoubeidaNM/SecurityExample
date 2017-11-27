package com.example.securityexample;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class EducationalExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String schoolName;
    private String yearCompletetion;
    private String degree;
    private String gpa;

    @ManyToMany(mappedBy = "educationalExperiences")
    private Set<DataUser> users;

    public EducationalExperience(){
        users = new HashSet<DataUser>();

    }

    public EducationalExperience(String schoolName, String yearCompletetion, String degree, String gpa) {
        this.schoolName = schoolName;
        this.yearCompletetion = yearCompletetion;
        this.degree = degree;
        this.gpa = gpa;
        users = new HashSet<DataUser>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<DataUser> getUsers() {
        return users;
    }

    public void setUsers(Set<DataUser> users) {
        this.users = users;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getYearCompletetion() {
        return yearCompletetion;
    }

    public void setYearCompletetion(String yearCompletetion) {
        this.yearCompletetion = yearCompletetion;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public void addUser(DataUser user){
        users.add(user);
    }

    public String write(){
        return degree + ",\n" +
                schoolName + ", " + yearCompletetion + "\n";
    }
}
