package com.tinubu.assurance.policyAssurance.persistance.repository;

import com.tinubu.assurance.policyAssurance.persistance.model.AssurancePolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssurancePolicyRepository extends JpaRepository<AssurancePolicyEntity, Integer> {

    Optional<AssurancePolicyEntity> findById(Integer policyId);

}
