package com.tinubu.assurance.policyAssurance.infra.mapper;

import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyStatusDTO;
import com.tinubu.assurance.policyAssurance.infra.model.PolicyStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class PolicyStatusMapperTest {

    @Test
    @DisplayName("converts DTO status to entity status successfully")
    void convertsDtoStatusToEntityStatusSuccessfully() {
        AssurancePolicyStatusDTO dtoStatus = AssurancePolicyStatusDTO.ACTIVE;

        PolicyStatus entityStatus = PolicyStatusMapper.toEntity(dtoStatus);

        assertEquals(PolicyStatus.ACTIVE, entityStatus);
    }

    @Test
    @DisplayName("returns null when converting null DTO status to entity status")
    void returnsNullWhenConvertingNullDtoStatusToEntityStatus() {
        PolicyStatus entityStatus = PolicyStatusMapper.toEntity(null);

        assertNull(entityStatus);
    }

    @Test
    @DisplayName("converts entity status to DTO status successfully")
    void convertsEntityStatusToDtoStatusSuccessfully() {
        PolicyStatus entityStatus = PolicyStatus.ACTIVE;

        AssurancePolicyStatusDTO dtoStatus = PolicyStatusMapper.toDto(entityStatus);

        assertEquals(AssurancePolicyStatusDTO.ACTIVE, dtoStatus);
    }

    @Test
    @DisplayName("returns null when converting null entity status to DTO status")
    void returnsNullWhenConvertingNullEntityStatusToDtoStatus() {
        AssurancePolicyStatusDTO dtoStatus = PolicyStatusMapper.toDto(null);

        assertNull(dtoStatus);
    }
}