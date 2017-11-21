package com.example.securityexample;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<DataUser, Long>{
    DataUser findByUsername(String username);
    DataUser findByEmail(String email);
    Long countByEmail(String email);
    Long countByUsername(String username);

}
