package com.example.securityexample;

import jdk.nashorn.internal.scripts.JO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.*;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EdExperienceRepository edExperienceRepository;

    @Autowired
    JobExperienceRepository jobExperienceRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    JobOfferRepository jobOfferRepository;


    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new DataUser());
        return "registeruser";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") DataUser user,
                                          BindingResult result, @RequestParam String role, Model model) {
        if (result.hasErrors()) {
            return "registeruser";
        } else {

            if (userRepository.findByUsername(user.getUsername()) == null) {
                if (role.equalsIgnoreCase("USER")) {
                    userService.saveUser(user);
                } else if (role.equalsIgnoreCase("RECRUITER")) {
                    userService.saveRecruiter(user);
                }
                model.addAttribute("message", "User Account Successfully Created");
            } else if (userRepository.findByUsername(user.getUsername()) == null) {
                model.addAttribute("error", true);
                model.addAttribute("error_message", "Username already exists. Try again!");

                return "registeruser";
            }
            System.out.println(user.getFirstName());
            model.addAttribute("user", user);
            return "login";
        }
    }

    @RequestMapping("/index")
    public String welcome(Principal principal, Model model){
        DataUser user = userRepository.findByUsername(principal.getName());

        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping("/")
    public String index(){
       return "homepage";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/secure")
    public String secure(HttpServletRequest request, Authentication authentication, Principal principal){
        Boolean isRecruiter = request.isUserInRole("RECRUITER");
        Boolean isUser = request.isUserInRole("USER");

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = principal.getName();
        return "index";
    }

    @RequestMapping("/user")
    public String showUser(Principal principal, Model model){

        DataUser user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "useraccount";
    }

    @RequestMapping("/recruiter")
    public String showRecruiter(Principal principal, Model model){

        DataUser user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "recruiterprofile";
    }

    @RequestMapping("/user/addeducation")
    public String addEducation(Model model){
        EducationalExperience education = new EducationalExperience();
        model.addAttribute("education", education );
        return "addeducation";
    }

    @PostMapping("/user/addeducation")
    public String processEducation(Principal principal, @Valid @ModelAttribute("education") EducationalExperience educationalExperience,
                                   BindingResult bindingresult, Model model){
        DataUser user = userRepository.findByUsername(principal.getName());
        if(bindingresult.hasErrors())
        {
            return "addeducation";
        }else {
            edExperienceRepository.save(educationalExperience);
            user.addEducttionalExperience(educationalExperience);
            userRepository.save(user);
            model.addAttribute("user", user);

            return "useraccount";

        }
   }


    @RequestMapping("/user/addjobexperience")
    public String addJobExperience(Model model){
        JobExperience jobExperience = new JobExperience();
        model.addAttribute("jobExperience", jobExperience );

        return "addjobexp";
    }

    @PostMapping("/user/addjobexperience")
    public String processJobExperience(Principal principal, @Valid @ModelAttribute("jobExperience") JobExperience jobExperience,
                                       BindingResult bindingresult, Model model){
        DataUser user = userRepository.findByUsername(principal.getName());
        if(bindingresult.hasErrors())
        {
            return "addjobexp";
        }else {
            try{
                int month = Integer.parseInt(jobExperience.getStartMonth());
            String startMonth = parseMonth(month);
            jobExperience.setStartMonth(startMonth);

                month = Integer.parseInt(jobExperience.getEndMonth());
                String endMonth = parseMonth(month);
                jobExperience.setEndMonth(endMonth);

                jobExperienceRepository.save(jobExperience);
            user.addJobExperience(jobExperience);
            userRepository.save(user);
            model.addAttribute("user", user);

            return "useraccount";
            }catch( Exception e){
                System.out.println("error parsing month in job experience");
                return "addjobexp";
            }
        }
    }


    @RequestMapping("/user/addskill")
    public String addSkill(Model model){
        Skill skill = new Skill();
        model.addAttribute("skill", skill );
        return "addskill";
    }

    @PostMapping("/user/addskill")
    public String processSkill(Principal principal, @Valid @ModelAttribute("skill") Skill skill,
                               BindingResult bindingresult, Model model){
        DataUser user = userRepository.findByUsername(principal.getName());
        if(bindingresult.hasErrors())
        {
            return "addskill";
        }else {

            skillRepository.save(skill);
            user.addSkill(skill);
            userRepository.save(user);
            model.addAttribute("user", user);

            return "useraccount";
       }
    }



    @RequestMapping("/recruiter/addjob")
    public String addJob(Model model){
        JobOffer jobOffer = new JobOffer();
        model.addAttribute("jobOffer", jobOffer );
        return "addjoboffer";
    }

    @PostMapping("/recruiter/addjob")
    public String processJob(Principal principal, @Valid @ModelAttribute("jobOffer") JobOffer jobOffer,
                             BindingResult bindingresult, Model model){
        DataUser user = userRepository.findByUsername(principal.getName());


        if(bindingresult.hasErrors())
        {
            return "addjoboffer";
        }else {
            //parse skills
            //Rating hard coded to proficient
            //Skill1 always exists
            Skill skill = new Skill(jobOffer.getSkill1(), "Proficient");
            skillRepository.save(skill);
            jobOffer.addSkill(skill);

            if (!jobOffer.getSkill2().isEmpty()) {
                skill = new Skill(jobOffer.getSkill2(), "Proficient");
                skillRepository.save(skill);
                jobOffer.addSkill(skill);
            }

            if (!jobOffer.getSkill3().isEmpty()) {
                skill = new Skill(jobOffer.getSkill3(), "Proficient");
                skillRepository.save(skill);
                jobOffer.addSkill(skill);
            }

            if (!jobOffer.getSkill4().isEmpty()) {
                skill = new Skill(jobOffer.getSkill4(), "Proficient");
                skillRepository.save(skill);
                jobOffer.addSkill(skill);
            }

            if (!jobOffer.getSkill5().isEmpty()) {
                skill = new Skill(jobOffer.getSkill5(), "Proficient");
                skillRepository.save(skill);
                jobOffer.addSkill(skill);
            }


            jobOfferRepository.save(jobOffer);
            user.addJobOffer(jobOffer);
            userRepository.save(user);
            model.addAttribute("user", user);

            return "recruiterprofile";
        }
    }



    @RequestMapping("/recruiter/search")
    public String searchResumes(){
         return "searchresumes";
    }

    @PostMapping("/recruiter/search")
    public String processSearchResumes(@RequestParam String searchedSkill, Model model){
        Iterable<Skill> skills = skillRepository.findAllByFieldContainingIgnoreCase(searchedSkill);

       // Iterable<Skill> skills = skillRepository.findAll();


        Set<DataUser> users = new HashSet<DataUser>();

        for(Skill skill:skills) {
            // there is only one user by skill
            System.out.println(skill.getField());
            System.out.println(skill.getUsers().size());
            for(DataUser user:skill.getUsers()){
                System.out.println(user.getUsername());
                users.add(user);
            }
        }
        model.addAttribute("people", users);
        model.addAttribute("searchStr", searchedSkill);
        return "searchresult";
    }

    @RequestMapping("/user/search")
    public String searchPeople(){
         return "searchpeople";
    }

    @PostMapping("/user/search")
    public String processSearchPeople(@RequestParam String searchedname, Model model){
        Iterable<DataUser> people = userRepository.findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchedname, searchedname);
        model.addAttribute("people", people);
        model.addAttribute("searchStr", searchedname);
        return "searchresult";
    }

    @RequestMapping("/user/details/{id}")
    public String showUserResume(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userRepository.findOne(id));
        return "showresume";
    }

    @RequestMapping("/recruiter/details/{id}")
    public String showUserResume2(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userRepository.findOne(id));
        return "showresume";
    }

    public String parseMonth(int number){
        if(number==1) {return "January";}
        else if (number==2) {return "February";}
        else if (number==3) {return "Mars";}
        else if (number==4) {return "April";}
        else if (number==5) {return "May";}
        else if (number==6) {return "June";}
        else if (number==7) {return "July";}
        else if (number==8) {return "August";}
        else if (number==9) {return "September";}
        else if (number==10) {return "October";}
        else if (number==11) {return "November";}
        else if (number==12) {return "December";}
        else{return "error";}
    }


}
