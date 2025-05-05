package com.tinubu.assurance.policyAssurance.core.service;

import com.tinubu.assurance.policyAssurance.domain.assurance.service.AssurancePolicyService;
import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyDTO;
import com.tinubu.assurance.policyAssurance.core.mapper.AssurancePolicyInfraMapper;
import com.tinubu.assurance.policyAssurance.persistance.model.AssurancePolicyEntity;
import com.tinubu.assurance.policyAssurance.persistance.model.PolicyStatus;
import com.tinubu.assurance.policyAssurance.persistance.repository.AssurancePolicyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AssurancePolicyServiceImpl implements AssurancePolicyService {


     private final AssurancePolicyRepository assurancePolicyRepository;

    public AssurancePolicyServiceImpl(AssurancePolicyRepository repository) {
        this.assurancePolicyRepository = repository;
    }

    /**
     * Create a new assurance policy.
     *
     * @param policyDto the assurance policy to create
     * @return the created assurance policy
     */
    @Override
    public AssurancePolicyDTO createAssurancePolicy(AssurancePolicyDTO policyDto) {
        return AssurancePolicyInfraMapper.toDTO(assurancePolicyRepository.save(AssurancePolicyInfraMapper.toEntity(policyDto)));
    }

    /**
     * Update an existing assurance policy.
     *
     * @param policyDto the assurance policy to update
     * @return the updated assurance policy
     */
    @Override
    @Transactional
    public AssurancePolicyDTO updateAssurancePolicy(AssurancePolicyDTO policyDto) {
        if (policyDto.getId() == null) {
            throw new IllegalArgumentException("Policy ID is required for update.");
        }

        AssurancePolicyEntity existingEntity = assurancePolicyRepository.findById(policyDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Policy not found with ID: " + policyDto.getId()));

        existingEntity.setPolicyName(policyDto.getPolicyName());
        existingEntity.setStatus(PolicyStatus.valueOf(policyDto.getStatus().name()));
        existingEntity.setCoverageStartDate(new Date(policyDto.getCoverageStartDate().getTime()));
        existingEntity.setCoverageEndDate(new Date(policyDto.getCoverageEndDate().getTime()));
        existingEntity.setUpdatedDate(new Date(System.currentTimeMillis()));

        return AssurancePolicyInfraMapper.toDTO(assurancePolicyRepository.save(existingEntity));
    }

    /**
     * Retrieve an assurance policy by its ID.
     *
     * @param id the ID of the assurance policy
     * @return the assurance policy, if found
     */
    @Override
    public Optional<AssurancePolicyDTO> getAssurancePolicy(Integer id) {
        return assurancePolicyRepository.findById(id)
                .map(AssurancePolicyInfraMapper::toDTO);
    }

    /**
     * Retrieve all assurance policies.
     *
     * @return a list of all assurance policies
     */
    @Override
    public List<AssurancePolicyDTO> getAllAssurancePolicies() {
        return AssurancePolicyInfraMapper.toDTO(assurancePolicyRepository.findAll());
    }
}
