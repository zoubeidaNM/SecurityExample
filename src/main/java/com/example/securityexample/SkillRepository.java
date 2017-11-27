package com.example.securityexample;

import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<Skill, Long>{

    Iterable<Skill> findAllByFieldContainingIgnoreCase(String field);
}
