package com.example.securityexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public DataUser findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public DataUser findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Long countByEmail(String email){
        return userRepository.countByEmail(email);
    }

    public void saveUser(DataUser user) {
        user.setRoles((Arrays.asList(roleRepository.findByRole("USER"))));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void saveRecruiter(DataUser user){
        user.setRoles((Arrays.asList(roleRepository.findByRole("RECRUITER"))));
        user.setEnabled(true);
        userRepository.save(user);
    }


}
