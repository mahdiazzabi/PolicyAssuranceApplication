package com.tinubu.assurance.policyAssurance.domain.assurance.service;

import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AssurancePolicyService {

    AssurancePolicyDTO createAssurancePolicy(AssurancePolicyDTO policyDto);

    AssurancePolicyDTO updateAssurancePolicy(AssurancePolicyDTO policyDto);

    Optional<AssurancePolicyDTO> getAssurancePolicy(Integer id);

    List<AssurancePolicyDTO> getAllAssurancePolicies();
}
