package com.tinubu.assurance.policyAssurance.infra.mapper;

import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyStatusDTO;
import com.tinubu.assurance.policyAssurance.infra.model.PolicyStatus;

public class PolicyStatusMapper {


    public static PolicyStatus toEntity(AssurancePolicyStatusDTO dtoStatus) {
        if (dtoStatus == null) return null;
        return switch (dtoStatus) {
            case ACTIVE -> PolicyStatus.ACTIVE;
            case INACTIVE -> PolicyStatus.INACTIVE;
        };
    }

    public static AssurancePolicyStatusDTO toDto(PolicyStatus entityStatus) {
        if (entityStatus == null) return null;
        return switch (entityStatus) {
            case ACTIVE -> AssurancePolicyStatusDTO.ACTIVE;
            case INACTIVE -> AssurancePolicyStatusDTO.INACTIVE;
        };
    }
}
