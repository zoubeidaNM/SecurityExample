package com.example.securityexample;

import org.springframework.data.repository.CrudRepository;

public interface JobExperienceRepository extends CrudRepository<JobExperience, Long>{
    JobExperience findByPlaceContaining(String place);
}
