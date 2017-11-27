package com.example.securityexample;

import org.springframework.data.repository.CrudRepository;

public interface JobOfferRepository extends CrudRepository<JobOffer, Long>{

    Iterable<JobOffer>
    findAllBySkill1ContainingAndSkill2ContainingAndSkill3ContainingAndSkill4ContainingAndSkill5Containing(
            String skill1,String skill2,String skill3,String skill4,String skill5);
}
