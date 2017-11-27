package com.example.securityexample;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="USER_DATA")
public class DataUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="email")
    private String email;

    @Column(name="enabled")
    private boolean enabled;


    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="password")
    @NotEmpty
    @NotNull
    @Size(min=2, max=20)
    private String password;

    @Column(name="username", unique=true)
    @NotEmpty
    @NotNull
    @Size(min=2, max=20)
    private String username;

    private String companyName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name ="user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<UserRole> roles;

    @ManyToMany
    private Set<EducationalExperience> educationalExperiences;

    @ManyToMany
    private Set<JobExperience> jobExperiences;

    @ManyToMany
    private Set<Skill> skills;

    @ManyToMany
    private Set<JobOffer> jobOffers;


    public DataUser(String email, boolean enabled, String firstName, String lastName, String password, String username) {
        this.email = email;
        this.enabled = enabled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;

        roles = new HashSet<UserRole>();
        educationalExperiences = new HashSet<EducationalExperience>();
        jobExperiences = new HashSet<JobExperience>();
        skills = new HashSet<Skill>();
        jobOffers = new HashSet<JobOffer>();

    }

    public DataUser() {
        roles = new HashSet<UserRole>();
        educationalExperiences = new HashSet<EducationalExperience>();
        jobExperiences = new HashSet<JobExperience>();
        skills = new HashSet<Skill>();
        jobOffers = new HashSet<JobOffer>();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<UserRole> roles) {
        this.roles = roles;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Set<EducationalExperience> getEducationalExperiences() {
        return educationalExperiences;
    }

    public void setEducationalExperiences(Set<EducationalExperience> educationalExperiences) {
        this.educationalExperiences = educationalExperiences;
    }

    public Set<JobExperience> getJobExperiences() {
        return jobExperiences;
    }

    public void setJobExperiences(Set<JobExperience> jobExperiences) {
        this.jobExperiences = jobExperiences;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(Set<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

    public void addEducttionalExperience(EducationalExperience educationalExperience) {
    educationalExperiences.add(educationalExperience);
    }

    public void addJobExperience(JobExperience jobExperience){
        jobExperiences.add(jobExperience);
    }


    public void addSkill(Skill skill){
        skills.add(skill);
    }

    public void addJobOffer(JobOffer jobOffer){
        jobOffers.add(jobOffer);
    }
}
