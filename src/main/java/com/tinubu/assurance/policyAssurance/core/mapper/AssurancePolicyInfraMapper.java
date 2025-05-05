package com.tinubu.assurance.policyAssurance.core.mapper;

import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyDTO;
import com.tinubu.assurance.policyAssurance.persistance.model.AssurancePolicyEntity;

import java.sql.Date;
import java.util.List;

public class AssurancePolicyInfraMapper {

    public static AssurancePolicyDTO toDTO(AssurancePolicyEntity entity) {
        return new AssurancePolicyDTO.Builder()
                .id(entity.getId())
                .policyName(entity.getPolicyName())
                .status(PolicyStatusMapper.toDto(entity.getStatus()))
                .coverageStartDate(entity.getCoverageStartDate())
                .coverageEndDate(entity.getCoverageEndDate())
                .build();
    }

    public static List<AssurancePolicyDTO> toDTO(List<AssurancePolicyEntity> entities) {
        return entities.stream()
                .map(AssurancePolicyInfraMapper::toDTO)
                .toList();
    }

    public static AssurancePolicyEntity toEntity(AssurancePolicyDTO dto) throws IllegalArgumentException {
        if (dto == null) {
            throw new IllegalArgumentException("AssurancePolicyDTO cannot be null");
        }
        AssurancePolicyEntity entity = new AssurancePolicyEntity();
        entity.setId(dto.getId());
        entity.setPolicyName(dto.getPolicyName());
        entity.setStatus(PolicyStatusMapper.toEntity(dto.getStatus()));
        entity.setCoverageStartDate(new Date(dto.getCoverageStartDate().getTime()));
        entity.setCoverageEndDate(new Date(dto.getCoverageEndDate().getTime()) );
        return entity;
    }
}
