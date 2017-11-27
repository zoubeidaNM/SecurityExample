package com.example.securityexample;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @NotNull
    String field;

    String rating;

    @ManyToMany(mappedBy = "skills")
    private Set<DataUser> users;

    @ManyToMany(mappedBy = "jobOfferSkills")
    private Set<JobOffer> jobOffers;

    public Skill() {
        users = new HashSet<DataUser>();
        jobOffers = new HashSet<JobOffer>();

    }

    public Skill(String field, String rating) {
        this.field = field;
        this.rating = rating;
        users = new HashSet<DataUser>();
        jobOffers = new HashSet<JobOffer>();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Set<DataUser> getUsers() {
        return users;
    }

    public void setUsers(Set<DataUser> users) {
        this.users = users;
    }

    public Set<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(Set<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

    public void addUser(DataUser user){
        users.add(user);
    }

    public void addJobOffer(JobOffer jobOffer){ jobOffers.add(jobOffer);}


    public String write(){
        return field + ", " + rating + "\n";

    }
}
