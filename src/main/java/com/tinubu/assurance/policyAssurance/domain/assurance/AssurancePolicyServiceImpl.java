package com.tinubu.assurance.policyAssurance.domain.assurance;

import com.tinubu.assurance.policyAssurance.domain.assurance.service.AssurancePolicyService;
import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyDTO;
import com.tinubu.assurance.policyAssurance.infra.mapper.AssurancePolicyInfraMapper;
import com.tinubu.assurance.policyAssurance.infra.repository.AssurancePolicyRepository;
import org.springframework.stereotype.Service;

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
