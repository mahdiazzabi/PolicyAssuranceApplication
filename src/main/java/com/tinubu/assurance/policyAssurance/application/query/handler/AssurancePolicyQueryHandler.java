package com.tinubu.assurance.policyAssurance.application.query.handler;

import com.tinubu.assurance.policyAssurance.application.query.GetAssurancePolicyByIdQuery;
import com.tinubu.assurance.policyAssurance.application.query.GetAllAssurancePoliciesQuery;
import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyDTO;
import com.tinubu.assurance.policyAssurance.domain.assurance.service.AssurancePolicyService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssurancePolicyQueryHandler {

    private final AssurancePolicyService assurancePolicyService;

    public AssurancePolicyQueryHandler(AssurancePolicyService assurancePolicyService) {
        this.assurancePolicyService = assurancePolicyService;
    }

    /**
     * Handles the GetAllAssurancePoliciesByIdQuery to retrieve a list of assurance policies by ID.
     *
     * @param query the query containing the ID of the assurance policy
     * @return a list of AssurancePolicyDTO objects
     */
    //@QueryHandler
    public AssurancePolicyDTO handle(GetAssurancePolicyByIdQuery query) {
        return assurancePolicyService.getAssurancePolicy(query.getId())
                .orElseThrow(() -> new RuntimeException("Assurance policy not found"));
    }

    /**
     * Handles the GetAssurancePolicyQuery to retrieve all assurance policies.
     *
     * @param query the query to retrieve all assurance policies
     * @return a list of AssurancePolicyDTO objects
     */
    //@QueryHandler
    public List<AssurancePolicyDTO> handle(GetAllAssurancePoliciesQuery query) {
        return assurancePolicyService.getAllAssurancePolicies();
    }

}
