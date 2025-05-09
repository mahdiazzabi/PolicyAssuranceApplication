package com.tinubu.assurance.policyAssurance.api.controller;

import com.tinubu.assurance.policyAssurance.api.mapper.AssurancePolicyApiMapper;
import com.tinubu.assurance.policyAssurance.api.request.CreatePolicyRequest;
import com.tinubu.assurance.policyAssurance.api.request.UpdatePolicyRequest;
import com.tinubu.assurance.policyAssurance.api.response.PolicyResponse;
import com.tinubu.assurance.policyAssurance.application.command.CreateAssurancePolicyCommand;
import com.tinubu.assurance.policyAssurance.application.command.UpdateAssurancePolicyCommand;
import com.tinubu.assurance.policyAssurance.application.command.handler.AssurancePolicyCommandHandler;
import com.tinubu.assurance.policyAssurance.application.query.GetAllAssurancePoliciesQuery;
import com.tinubu.assurance.policyAssurance.application.query.GetAssurancePolicyByIdQuery;
import com.tinubu.assurance.policyAssurance.application.query.handler.AssurancePolicyQueryHandler;
import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyDTO;
import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyStatusDTO;
import com.tinubu.assurance.policyAssurance.domain.assurance.service.AssurancePolicyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssurancePolicyControllerTest {

    @Mock
    private AssurancePolicyCommandHandler assurancePolicyCommandHandler;


    @Mock
    private AssurancePolicyQueryHandler assurancePolicyQueryHandler;

    @Mock
    AssurancePolicyApiMapper mapper;

    @InjectMocks
    private AssurancePolicyController assurancePolicyController;


    @Nested
    @DisplayName("createPolicy")
    class CreatePolicy {

        @Test
        void shouldCreatePolicySuccessfully() {
            // Given
            CreatePolicyRequest request = new CreatePolicyRequest();
            request.setStatus("ACTIVE");
            AssurancePolicyDTO dto = new AssurancePolicyDTO.Builder().id(1).policyName("Policy1").build();
            when(assurancePolicyCommandHandler.handle(any(CreateAssurancePolicyCommand.class)))
                    .thenReturn(dto.getId());

            ResponseEntity<String> response = assurancePolicyController.createPolicy(request);
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
        }

        @Test
        @DisplayName("returns BAD REQUEST status when invalid status is provided")
        void returnsBadRequestStatusWhenInvalidStatusIsProvided() {
            CreatePolicyRequest request = new CreatePolicyRequest("Policy1", "INVALID", null, null);
            ResponseEntity<String> response = null;
            try {
                response =  assurancePolicyController.createPolicy(request);
            } catch (IllegalArgumentException e) {
                // Expected exception for invalid status
                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
                assertEquals("Invalid status value: INVALID", response.getBody());

            }
        }
    }

    @Nested
    @DisplayName("getAllPolicies")
    class GetAllPolicies {

        @Test
        @DisplayName("returns list of policies when policies exist")
        void returnsListOfPoliciesWhenPoliciesExist() {
            List<AssurancePolicyDTO> dtos = List.of(new AssurancePolicyDTO.Builder().id(1).policyName("Policy1").build());
            List<PolicyResponse> responses = List.of(new PolicyResponse("Policy1", null, null, null));
            when(assurancePolicyQueryHandler.handle(any(GetAllAssurancePoliciesQuery.class))).thenReturn(dtos);

            ResponseEntity<List<PolicyResponse>> response = assurancePolicyController.getAllPolicies();

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(responses, response.getBody());
        }

        @Test
        @DisplayName("returns empty list when no policies exist")
        void returnsEmptyListWhenNoPoliciesExist() {
            when(assurancePolicyQueryHandler.handle(any(GetAllAssurancePoliciesQuery.class))).thenReturn(List.of());

            ResponseEntity<List<PolicyResponse>> response = assurancePolicyController.getAllPolicies();

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(0, response.getBody().size());
        }
    }

    @Nested
    @DisplayName("getPolicy")
    class GetPolicy {

        @Test
        @DisplayName("returns policy when policy with given ID exists")
        void returnsPolicyWhenPolicyWithGivenIdExists() {
            Integer policyId = 1;
            AssurancePolicyDTO dto = new AssurancePolicyDTO.Builder().id(policyId).policyName("Policy1").build();
            PolicyResponse responseDto = new PolicyResponse("Policy1", null, null, null);
            when(assurancePolicyQueryHandler.handle(any(GetAssurancePolicyByIdQuery.class))).thenReturn(dto);

            ResponseEntity<PolicyResponse> response = assurancePolicyController.getPolicy(policyId);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(responseDto, response.getBody());
        }

        @Test
        @DisplayName("returns NOT FOUND status when policy with given ID does not exist")
        void returnsNotFoundStatusWhenPolicyWithGivenIdDoesNotExist() {
            int policyId = 1;
            when(assurancePolicyQueryHandler.handle(any(GetAssurancePolicyByIdQuery.class))).thenThrow(new RuntimeException("Assurance policy not found"));

            ResponseEntity<PolicyResponse> response = assurancePolicyController.getPolicy(policyId);

            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }
    }

    @Nested
    @DisplayName("updatePolicy")
    class UpdatePolicy {

        @Test
        @DisplayName("updates policy successfully")
        void updatesPolicySuccessfully() {
            UpdatePolicyRequest request = new UpdatePolicyRequest();
            request.setPolicyName("UpdatedPolicy");
            request.setStatus("ACTIVE");
            when(assurancePolicyCommandHandler.handle(any(UpdateAssurancePolicyCommand.class))).thenReturn(1);

            ResponseEntity<String> response = assurancePolicyController.updatePolicy(request);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("Assurance Policy updated with id : 1", response.getBody());
        }
    }
}