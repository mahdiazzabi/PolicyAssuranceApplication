package com.tinubu.assurance.policyAssurance.api.mapper;

import com.tinubu.assurance.policyAssurance.api.request.CreatePolicyRequest;
import com.tinubu.assurance.policyAssurance.api.response.PolicyResponse;
import com.tinubu.assurance.policyAssurance.api.response.PolicyStatusResponse;
import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyDTO;
import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyStatusDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AssurancePolicyApiMapper {

    private AssurancePolicyApiMapper() {
        // Private constructor to prevent instantiation
    }

    public static AssurancePolicyDTO toDto(CreatePolicyRequest request) {
        if (request == null) {
            return null;
        }

        return new AssurancePolicyDTO.Builder()
                .policyName(request.getPolicyName())
                .status(mapStatus(request.getStatus()))
                .coverageStartDate(request.getCoverageStartDate())
                .coverageEndDate(request.getCoverageEndDate())
                .build();
    }

    private static AssurancePolicyStatusDTO mapStatus(String status) {
        if (status == null) {
            return null;
        }
        try {
            return AssurancePolicyStatusDTO.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status value: " + status);
        }
    }

    private static PolicyStatusResponse mapResponseStatus(AssurancePolicyStatusDTO status) {
        if (status == null) {
            return null;
        }
        try {
            return PolicyStatusResponse.valueOf(status.name());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status value: " + status);
        }
    }

    public static PolicyResponse toResponse(AssurancePolicyDTO dto) {
        return new PolicyResponse(
                dto.getPolicyName(),
                mapResponseStatus(dto.getStatus()),
                dto.getCoverageStartDate(),
                dto.getCoverageEndDate()
        );
    }

    public static List<PolicyResponse> toResponseList(List<AssurancePolicyDTO> dtoList) {
        return dtoList.stream()
                .map(AssurancePolicyApiMapper::toResponse)
                .collect(Collectors.toList());
    }
}
