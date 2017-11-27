package com.example.securityexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class DataLoader implements CommandLineRunner{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JobExperienceRepository jobExperiences;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    EdExperienceRepository edExperiences;

    @Autowired
    JobOfferRepository jobOffers;


    @Override
    public void run(String... strings) throws Exception{
        System.out.println("Loading data . . .");

        roleRepository.save(new UserRole("USER"));
        roleRepository.save(new UserRole("RECRUITER"));

        UserRole recruiterRole = roleRepository.findByRole("RECRUITER");
        UserRole userRole = roleRepository.findByRole("USER");

        DataUser user = new DataUser("bob@bob.com", true,   "bobberson", "Bob",   "bob", "bob" );
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new DataUser("jim@jim.com", true, "jim",  "jimmerson", "jim",   "jim" );
        JobExperience jobExperience = new JobExperience("Enginner","Facebook","june", "1990", "july", "2001",
                "jdKD kHSDKhkH SHDKLDHKLs SKlklsfhkLFHFKH kkhk");
         JobExperience jobExperience2 = new JobExperience("Enginner","google","june", "2001", "july", "2002",
                 "jdKD kHSDKhkH SHDKLDHKLs SKlklsfhkLFHFKH kkhk");
        jobExperiences.save(jobExperience);
        jobExperiences.save(jobExperience2);
         user.addJobExperience(jobExperience);
         user.addJobExperience(jobExperience2);

         Skill skill1 = new Skill("java", "proficient");
         Skill skill2 = new Skill("C++", "familiar");
         Skill skill3 = new Skill("Oracle", "highly skilled");

         skillRepository.save(skill1);
         skillRepository.save(skill2);
         skillRepository.save(skill3);

         user.addSkill(skill1);
         user.addSkill(skill2);
         user.addSkill(skill3);

         EducationalExperience educationalExperience = new EducationalExperience("havard", "2001",
                 "M.S. Computer Science","4.0");

         EducationalExperience educationalExperience2 = new EducationalExperience("Princeton", "2005",
                 "P.H.D. Computer Science","4.0");

         edExperiences.save(educationalExperience);
         edExperiences.save(educationalExperience2);

        user.addEducttionalExperience(educationalExperience);
         user.addEducttionalExperience(educationalExperience2);

         user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new DataUser("admin@adm.com", true, "Admin",  "User",  "pass",   "admin");

        JobOffer jobOffer =  new JobOffer();
        jobOffer.setTitle("Software Engineer");
        jobOffer.setCompanyName("facebook");
        jobOffer.setLocation("Washington DC");
        jobOffer.setType("Full Time");
        jobOffer.setDescription("Currently, we have an outstanding opportunity for a talented and energetic Javascript/UI developer to join our growing product development team. The Javascript/UI developer at Hobsons will be involved in significant development projects for our market-leading K-12 SaaS application. Your work will focus on developing applications using various cutting-edge technologies following an Agile process.\n" +
                "\n" +
                "We are seeking candidates that have strong development experience and who are passionate about the latest industry trends and innovations occurring in SaaS products. " );
        jobOffer.setEducation("M.S in computer science");
        jobOffer.setSkill1("java");

        Skill skill4 = new Skill("java", "proficient");
        Skill skill5 = new Skill("PHP", "familiar");
        Skill skill6 = new Skill("Ruby", "highly skilled");

        skillRepository.save(skill4);
        skillRepository.save(skill5);
        skillRepository.save(skill6);

        jobOffer.addSkill(skill4);
        jobOffer.addSkill(skill5);
        jobOffer.addSkill(skill6);

        user.addSkill(skill4);
        user.addSkill(skill5);
        user.addSkill(skill6);

        jobOffers.save(jobOffer);

        user.addJobOffer(jobOffer);
        user.setRoles(Arrays.asList(recruiterRole));
        user.setCompanyName("facebook");
        userRepository.save(user);

        user = new DataUser("sam@ev.com", true, "Sam",  "Everyman",  "pass", "sam");

        user.setCompanyName("IBM");
        user.setRoles(Arrays.asList(userRole, recruiterRole));
        userRepository.save(user);

    }
}
