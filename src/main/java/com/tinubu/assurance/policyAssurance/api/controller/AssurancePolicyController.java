package com.tinubu.assurance.policyAssurance.api.controller;

import com.tinubu.assurance.policyAssurance.api.mapper.AssurancePolicyApiMapper;
import com.tinubu.assurance.policyAssurance.api.request.CreatePolicyRequest;
import com.tinubu.assurance.policyAssurance.api.response.PolicyResponse;
import com.tinubu.assurance.policyAssurance.domain.assurance.service.AssurancePolicyService;
import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policies")
public class AssurancePolicyController {

    private final AssurancePolicyService assurancePolicyService;

    public AssurancePolicyController(AssurancePolicyService assurancePolicyService) {
        this.assurancePolicyService = assurancePolicyService;
    }

    @PostMapping
    public ResponseEntity<String> createPolicy(@Valid @RequestBody CreatePolicyRequest command) {
        try {
            AssurancePolicyDTO assurancePolicyCreated = assurancePolicyService.createAssurancePolicy(AssurancePolicyApiMapper.toDto(command));
            return ResponseEntity.status(HttpStatus.CREATED).body(assurancePolicyCreated.getId().toString());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid status value: " + command.getStatus());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PolicyResponse>> getAllPolicies() {
        return ResponseEntity.ok(AssurancePolicyApiMapper.toResponseList(assurancePolicyService.getAllAssurancePolicies()));
    }

    @GetMapping("/{policyId}")
    public ResponseEntity<PolicyResponse> getPolicy(@PathVariable Integer policyId) {
        return assurancePolicyService.getAssurancePolicy(policyId)
                .map(policy -> ResponseEntity.ok(AssurancePolicyApiMapper.toResponse(policy)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
