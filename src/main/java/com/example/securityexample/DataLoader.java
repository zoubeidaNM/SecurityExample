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

    @Override
    public void run(String... strings) throws Exception{
        System.out.println("Loading data . . .");

        roleRepository.save(new UserRole("USER"));
        roleRepository.save(new UserRole("ADMIN"));

        UserRole adminRole = roleRepository.findByRole("ADMIN");
        UserRole userRole = roleRepository.findByRole("USER");

        DataUser user = new DataUser("bob@bob.com", true,   "bobberson", "Bob",   "bob", "bob" );
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new DataUser("jim@jim.com", true, "jim",  "jimmerson", "jim",   "jim" );
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new DataUser("admin@adm.com", true, "Admin",  "User",  "pass",   "admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        user = new DataUser("sam@ev.com", true, "Sam",  "Everyman",  "pass", "sam");
        user.setRoles(Arrays.asList(userRole, adminRole));
        userRepository.save(user);

    }
}
