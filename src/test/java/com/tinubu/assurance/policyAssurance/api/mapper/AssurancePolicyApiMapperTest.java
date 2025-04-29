package com.tinubu.assurance.policyAssurance.api.mapper;

import com.tinubu.assurance.policyAssurance.api.request.CreatePolicyRequest;
import com.tinubu.assurance.policyAssurance.api.response.PolicyResponse;
import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyDTO;
import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyStatusDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AssurancePolicyApiMapperTest {

    @Test
    @DisplayName("converts CreatePolicyRequest to AssurancePolicyDTO successfully")
    void convertsCreatePolicyRequestToAssurancePolicyDtoSuccessfully() {
        CreatePolicyRequest request = new CreatePolicyRequest("Policy1", "ACTIVE", new Date(2023, 1, 1),  new Date(2023, 12, 31));

        AssurancePolicyDTO dto = AssurancePolicyApiMapper.toDto(request);

        assertEquals(request.getPolicyName(), dto.getPolicyName());
        assertEquals("ACTIVE", dto.getStatus().name());
        assertEquals(request.getCoverageStartDate(), dto.getCoverageStartDate());
        assertEquals(request.getCoverageEndDate(), dto.getCoverageEndDate());
    }

    @Test
    @DisplayName("returns null when converting null CreatePolicyRequest to AssurancePolicyDTO")
    void returnsNullWhenConvertingNullCreatePolicyRequestToAssurancePolicyDto() {
        AssurancePolicyDTO dto = AssurancePolicyApiMapper.toDto(null);

        assertNull(dto);
    }

    @Test
    @DisplayName("throws exception when invalid status is provided in CreatePolicyRequest")
    void throwsExceptionWhenInvalidStatusIsProvidedInCreatePolicyRequest() {
        CreatePolicyRequest request = new CreatePolicyRequest("Policy1", "INVALID", null, null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            AssurancePolicyApiMapper.toDto(request);
        });

        assertEquals("Invalid status value: INVALID", exception.getMessage());
    }

    @Test
    @DisplayName("converts AssurancePolicyDTO to PolicyResponse successfully")
    void convertsAssurancePolicyDtoToPolicyResponseSuccessfully() {
        AssurancePolicyDTO dto = new AssurancePolicyDTO.Builder()
                .policyName("Policy1")
                .status(AssurancePolicyStatusDTO.valueOf("ACTIVE"))
                .coverageStartDate(new Date(2023, 1, 1))
                .coverageEndDate(new Date(2023, 12, 31))
                .build();

        PolicyResponse response = AssurancePolicyApiMapper.toResponse(dto);

        assertEquals(dto.getPolicyName(), response.policyName());
        assertEquals("ACTIVE", response.status().name());
        assertEquals(dto.getCoverageStartDate(), response.coverageStartDate());
        assertEquals(dto.getCoverageEndDate(), response.coverageEndDate());
    }

    @Test
    @DisplayName("converts list of AssurancePolicyDTO to list of PolicyResponse successfully")
    void convertsListOfAssurancePolicyDtoToListOfPolicyResponseSuccessfully() {
        AssurancePolicyDTO dto1 = new AssurancePolicyDTO.Builder()
                .policyName("Policy1")
                .status(AssurancePolicyStatusDTO.valueOf("ACTIVE"))
                .build();
        AssurancePolicyDTO dto2 = new AssurancePolicyDTO.Builder()
                .policyName("Policy2")
                .status(AssurancePolicyStatusDTO.valueOf("INACTIVE"))
                .build();

        List<PolicyResponse> responses = AssurancePolicyApiMapper.toResponseList(List.of(dto1, dto2));

        assertEquals(2, responses.size());
        assertEquals(dto1.getPolicyName(), responses.get(0).policyName());
        assertEquals(dto2.getPolicyName(), responses.get(1).policyName());
    }
}