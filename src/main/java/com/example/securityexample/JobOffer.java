package com.example.securityexample;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String companyName;
    private String location;
    private String type;

    @Column(length=2000)
    private String description;
    private String education;

    @NotNull
    @NotEmpty
    private String skill1;

    private String skill2;
    private String skill3;
    private String skill4;
    private String skill5;

    @ManyToMany(mappedBy = "jobOffers")
    private Set<DataUser> users;

    @ManyToMany
    private Set<Skill> jobOfferSkills;



    public JobOffer() {
        users = new HashSet<DataUser>();
        jobOfferSkills = new HashSet<Skill>();

    }

    public String getTitle() {
        return title;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getSkill4() {
        return skill4;
    }

    public void setSkill4(String skill4) {
        this.skill4 = skill4;
    }

    public String getSkill5() {
        return skill5;
    }

    public void setSkill5(String skill5) {
        this.skill5 = skill5;
    }

    public Set<Skill> getJobOfferSkills() {
        return jobOfferSkills;
    }

    public void setJobOfferSkills(Set<Skill> jobOfferSkills) {
        this.jobOfferSkills = jobOfferSkills;
    }

    public void addUser(DataUser user){
        users.add(user);
    }

    public void addSkill(Skill skill) {jobOfferSkills.add(skill);}

}
