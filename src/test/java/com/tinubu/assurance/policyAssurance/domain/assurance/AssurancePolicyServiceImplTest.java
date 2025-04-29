package com.tinubu.assurance.policyAssurance.domain.assurance;

import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyDTO;
import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyStatusDTO;
import com.tinubu.assurance.policyAssurance.infra.mapper.AssurancePolicyInfraMapper;
import com.tinubu.assurance.policyAssurance.infra.repository.AssurancePolicyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AssurancePolicyServiceImplTest {

    @Mock
    AssurancePolicyRepository assurancePolicyRepository;

    @InjectMocks
    AssurancePolicyServiceImpl assurancePolicyService;

    @Nested
    @DisplayName("createAssurancePolicy")
    class CreateAssurancePolicy {

        @Test
        @DisplayName("returns created policy when valid input is provided")
        void returnsCreatedPolicyWhenValidInputIsProvided() {
            AssurancePolicyDTO inputDto = new AssurancePolicyDTO.Builder()
                    .policyName("Policy1")
                    .status(AssurancePolicyStatusDTO.valueOf("ACTIVE"))
                    .build();
            AssurancePolicyDTO savedDto = new AssurancePolicyDTO.Builder()
                    .id(1)
                    .policyName("Policy1")
                    .status(AssurancePolicyStatusDTO.valueOf("ACTIVE"))
                    .build();
            when(assurancePolicyRepository.save(any())).thenReturn(AssurancePolicyInfraMapper.toEntity(savedDto));

            AssurancePolicyDTO result = assurancePolicyService.createAssurancePolicy(inputDto);

            assertEquals(savedDto.getId(), result.getId());
            assertEquals(savedDto.getPolicyName(), result.getPolicyName());
            assertEquals(savedDto.getStatus(), result.getStatus());
        }

        @Test
        @DisplayName("throws exception when input policy is null")
        void throwsExceptionWhenInputPolicyIsNull() {
            assertThrows(IllegalArgumentException.class, () -> assurancePolicyService.createAssurancePolicy(null));
        }
    }

    @Nested
    @DisplayName("getAssurancePolicy")
    class GetAssurancePolicy {

        @Test
        @DisplayName("returns policy when policy with given ID exists")
        void returnsPolicyWhenPolicyWithGivenIdExists() {
            int policyId = 1;
            AssurancePolicyDTO dto = new AssurancePolicyDTO.Builder()
                    .id(policyId)
                    .policyName("Policy1")
                    .status(AssurancePolicyStatusDTO.valueOf("ACTIVE"))
                    .build();
            when(assurancePolicyRepository.findById(policyId)).thenReturn(Optional.of(AssurancePolicyInfraMapper.toEntity(dto)));

            Optional<AssurancePolicyDTO> result = assurancePolicyService.getAssurancePolicy(policyId);

            assertTrue(result.isPresent());
            assertEquals(dto.getId(), result.get().getId());
            assertEquals(dto.getPolicyName(), result.get().getPolicyName());
        }

        @Test
        @DisplayName("returns empty optional when policy with given ID does not exist")
        void returnsEmptyOptionalWhenPolicyWithGivenIdDoesNotExist() {
            int policyId = 1;
            when(assurancePolicyRepository.findById(policyId)).thenReturn(Optional.empty());

            Optional<AssurancePolicyDTO> result = assurancePolicyService.getAssurancePolicy(policyId);

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("getAllAssurancePolicies")
    class GetAllAssurancePolicies {

        @Test
        @DisplayName("returns list of policies when policies exist")
        void returnsListOfPoliciesWhenPoliciesExist() {
            List<AssurancePolicyDTO> dtos = List.of(
                    new AssurancePolicyDTO.Builder().id(1).policyName("Policy1").status(AssurancePolicyStatusDTO.valueOf("ACTIVE")).build(),
                    new AssurancePolicyDTO.Builder().id(2).policyName("Policy2").status(AssurancePolicyStatusDTO.valueOf("INACTIVE")).build()
            );
            when(assurancePolicyRepository.findAll()).thenReturn(dtos.stream().map(AssurancePolicyInfraMapper::toEntity).toList());

            List<AssurancePolicyDTO> result = assurancePolicyService.getAllAssurancePolicies();

            assertEquals(dtos.size(), result.size());
            assertEquals(dtos.get(0).getId(), result.get(0).getId());
            assertEquals(dtos.get(1).getId(), result.get(1).getId());
        }

        @Test
        @DisplayName("returns empty list when no policies exist")
        void returnsEmptyListWhenNoPoliciesExist() {
            when(assurancePolicyRepository.findAll()).thenReturn(List.of());

            List<AssurancePolicyDTO> result = assurancePolicyService.getAllAssurancePolicies();

            assertTrue(result.isEmpty());
        }
    }
}