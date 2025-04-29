package com.tinubu.assurance.policyAssurance.infra.mapper;

import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyDTO;
import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyStatusDTO;
import com.tinubu.assurance.policyAssurance.infra.model.AssurancePolicyEntity;
import com.tinubu.assurance.policyAssurance.infra.model.PolicyStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AssurancePolicyInfraMapperTest {

    @Test
    @DisplayName("converts entity to DTO successfully")
    void convertsEntityToDtoSuccessfully() {
        AssurancePolicyEntity entity = new AssurancePolicyEntity();
        entity.setId(1);
        entity.setPolicyName("Policy1");
        entity.setStatus(PolicyStatus.ACTIVE);
        entity.setCoverageStartDate(new Date(2023, 1, 1));
        entity.setCoverageEndDate(new Date(2023, 12, 31));

        AssurancePolicyDTO dto = AssurancePolicyInfraMapper.toDTO(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getPolicyName(), dto.getPolicyName());
        assertEquals(entity.getStatus().name(), dto.getStatus().name());
        assertEquals(entity.getCoverageStartDate(), dto.getCoverageStartDate());
        assertEquals(entity.getCoverageEndDate(), dto.getCoverageEndDate());
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("converts list of entities to list of DTOs successfully")
    void convertsListOfEntitiesToListOfDtosSuccessfully() {
        AssurancePolicyEntity entity1 = new AssurancePolicyEntity();
        entity1.setId(1);
        entity1.setPolicyName("Policy1");
        entity1.setStatus(PolicyStatus.ACTIVE);

        AssurancePolicyEntity entity2 = new AssurancePolicyEntity();
        entity2.setId(2);
        entity2.setPolicyName("Policy2");
        entity2.setStatus(PolicyStatus.INACTIVE);

        List<AssurancePolicyEntity> entities = List.of(entity1, entity2);

        List<AssurancePolicyDTO> dtos = AssurancePolicyInfraMapper.toDTO(entities);

        assertEquals(entities.size(), dtos.size());
        assertEquals(entities.get(0).getId(), dtos.get(0).getId());
        assertEquals(entities.get(1).getId(), dtos.get(1).getId());
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("throws exception when converting null DTO to entity")
    void throwsExceptionWhenConvertingNullDtoToEntity() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            AssurancePolicyInfraMapper.toEntity(null);
        });

        assertEquals("AssurancePolicyDTO cannot be null", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("converts DTO to entity successfully")
    void convertsDtoToEntitySuccessfully() {
        AssurancePolicyDTO dto = new AssurancePolicyDTO.Builder()
                .id(1)
                .policyName("Policy1")
                .status(AssurancePolicyStatusDTO.valueOf("ACTIVE"))
                .coverageStartDate(new Date(2023, 1, 1))
                .coverageEndDate(new Date(2023, 12, 31))
                .build();

        AssurancePolicyEntity entity = AssurancePolicyInfraMapper.toEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getPolicyName(), entity.getPolicyName());
        assertEquals(dto.getStatus().name(), entity.getStatus().name());
        assertEquals(dto.getCoverageStartDate(), entity.getCoverageStartDate());
        assertEquals(dto.getCoverageEndDate(), entity.getCoverageEndDate());
    }
}