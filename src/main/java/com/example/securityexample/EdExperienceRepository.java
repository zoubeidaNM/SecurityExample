package com.example.securityexample;

import org.springframework.data.repository.CrudRepository;

import java.security.acl.LastOwnerException;

public interface EdExperienceRepository extends CrudRepository<EducationalExperience, LastOwnerException>{

    EducationalExperience findByDegree(String degree);
}
