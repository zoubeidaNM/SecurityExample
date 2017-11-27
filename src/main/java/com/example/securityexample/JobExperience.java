package com.example.securityexample;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class JobExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String titleOfPosition;
    String place;
    String startMonth;
    String startYear;
    String endMonth;
    String endYear;
    String description;

    @ManyToMany(mappedBy = "jobExperiences")
    private Set<DataUser> users;


    public JobExperience(){
        users = new HashSet<DataUser>();
     }

    public JobExperience(String titleOfPosition, String place, String startMonth, String startYear, String endMonth, String endYear, String description) {
        this.titleOfPosition = titleOfPosition;
        this.place = place;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.description = description;
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

    public String getTitleOfPosition() {
        return titleOfPosition;
    }

    public void setTitleOfPosition(String titleOfPosition) {
        this.titleOfPosition = titleOfPosition;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addUser(DataUser user){
        users.add(user);
    }


}
