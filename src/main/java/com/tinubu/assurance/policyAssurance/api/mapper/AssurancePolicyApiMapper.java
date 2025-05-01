package com.tinubu.assurance.policyAssurance.api.mapper;

import com.tinubu.assurance.policyAssurance.api.request.CreatePolicyRequest;
import com.tinubu.assurance.policyAssurance.api.request.UpdatePolicyRequest;
import com.tinubu.assurance.policyAssurance.api.response.PolicyResponse;
import com.tinubu.assurance.policyAssurance.api.response.PolicyStatusResponse;
import com.tinubu.assurance.policyAssurance.application.command.CreateAssurancePolicyCommand;
import com.tinubu.assurance.policyAssurance.application.command.UpdateAssurancePolicyCommand;
import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyDTO;
import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyStatusDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AssurancePolicyApiMapper {

    private AssurancePolicyApiMapper() {
        // Private constructor to prevent instantiation
    }

    public static AssurancePolicyDTO toDto(UpdateAssurancePolicyCommand command) {
        if (command == null) {
            return null;
        }

        return new AssurancePolicyDTO.Builder()
                .id(command.getId())
                .policyName(command.getName())
                .status(command.getStatus())
                .coverageStartDate(command.getStartDate())
                .coverageEndDate(command.getEndDate())
                .build();
    }

    public static CreateAssurancePolicyCommand toCommand(CreatePolicyRequest request) {
        if (request == null) {
            return null;
        }

        return new CreateAssurancePolicyCommand(
                request.getPolicyName(),
                mapStatus(request.getStatus()),
                request.getCoverageStartDate(),
                request.getCoverageEndDate()
        );
    }

    public static UpdateAssurancePolicyCommand toCommand(UpdatePolicyRequest request) {
        if (request == null) {
            return null;
        }

        return new UpdateAssurancePolicyCommand(
                request.getPolicyId(),
                request.getPolicyName(),
                mapStatus(request.getStatus()),
                request.getCoverageStartDate(),
                request.getCoverageEndDate()
        );
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
