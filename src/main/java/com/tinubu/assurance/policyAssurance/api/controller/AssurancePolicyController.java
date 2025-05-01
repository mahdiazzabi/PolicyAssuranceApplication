package com.tinubu.assurance.policyAssurance.api.controller;

import com.tinubu.assurance.policyAssurance.api.mapper.AssurancePolicyApiMapper;
import com.tinubu.assurance.policyAssurance.api.request.CreatePolicyRequest;
import com.tinubu.assurance.policyAssurance.api.request.UpdatePolicyRequest;
import com.tinubu.assurance.policyAssurance.api.response.PolicyResponse;
import com.tinubu.assurance.policyAssurance.application.command.handler.AssurancePolicyCommandHandler;
import com.tinubu.assurance.policyAssurance.application.query.GetAllAssurancePoliciesQuery;
import com.tinubu.assurance.policyAssurance.application.query.GetAssurancePolicyByIdQuery;
import com.tinubu.assurance.policyAssurance.application.query.handler.AssurancePolicyQueryHandler;
import com.tinubu.assurance.policyAssurance.domain.assurance.service.AssurancePolicyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policies")
public class AssurancePolicyController {

    private final AssurancePolicyCommandHandler assurancePolicyCommandHandler;
    private final AssurancePolicyQueryHandler assurancePolicyQueryHandler;

    public AssurancePolicyController(AssurancePolicyCommandHandler commandHandler, AssurancePolicyQueryHandler queryHandler){
        this.assurancePolicyCommandHandler = commandHandler;
        this.assurancePolicyQueryHandler = queryHandler;
    }

    @PostMapping
    public ResponseEntity<String> createPolicy(@Valid @RequestBody CreatePolicyRequest request) {
        try {
            Integer idCreated = assurancePolicyCommandHandler.handle(AssurancePolicyApiMapper.toCommand(request));
            return ResponseEntity.status(HttpStatus.CREATED).body("Assurance Policy created with id : " + idCreated.toString());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> updatePolicy(@Valid @RequestBody UpdatePolicyRequest request) {
        try {
            Integer idUpdated = assurancePolicyCommandHandler.handle(AssurancePolicyApiMapper.toCommand(request));
            return ResponseEntity.ok("Assurance Policy updated with id : " + idUpdated.toString());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PolicyResponse>> getAllPolicies() {
        return ResponseEntity.ok(AssurancePolicyApiMapper.toResponseList(assurancePolicyQueryHandler.handle(
                new GetAllAssurancePoliciesQuery()
        )));
    }

    @GetMapping("/{policyId}")
    public ResponseEntity<PolicyResponse> getPolicy(@PathVariable Integer policyId) {
        try {
            return ResponseEntity.ok(
                    AssurancePolicyApiMapper.toResponse(
                            assurancePolicyQueryHandler.handle(new GetAssurancePolicyByIdQuery(policyId))));

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
