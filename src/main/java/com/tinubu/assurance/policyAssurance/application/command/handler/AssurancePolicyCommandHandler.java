package com.tinubu.assurance.policyAssurance.application.command.handler;

import com.tinubu.assurance.policyAssurance.application.command.CreateAssurancePolicyCommand;
import com.tinubu.assurance.policyAssurance.application.command.UpdateAssurancePolicyCommand;
import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyDTO;
import com.tinubu.assurance.policyAssurance.domain.assurance.service.AssurancePolicyService;
import org.springframework.stereotype.Component;

@Component
public class AssurancePolicyCommandHandler {

    private final AssurancePolicyService assurancePolicyService;

    public AssurancePolicyCommandHandler(AssurancePolicyService assurancePolicyService) {
        this.assurancePolicyService = assurancePolicyService;
    }

    /**
     * Handles the creation of a new assurance policy.
     *
     * @param command the command containing the details of the policy to create
     * @return the ID of the created assurance policy
     */

    //@CommandHandler
    public Integer handle(CreateAssurancePolicyCommand command) {
        AssurancePolicyDTO policy = new AssurancePolicyDTO.Builder()
                .policyName(command.getName())
                .status(command.getStatus())
                .coverageStartDate(command.getStartDate())
                .coverageEndDate(command.getEndDate())
                .build();

        AssurancePolicyDTO assurancePolicyCreated = assurancePolicyService.createAssurancePolicy(policy);
        // On successful creation, notify event
        // EventPublisher.publish(new PolicyCreatedEvent(assurancePolicyCreated.getId(), command.getName()));
        return assurancePolicyCreated.getId();
    }

    //@CommandHandler
    public Integer handle(UpdateAssurancePolicyCommand command) {
        AssurancePolicyDTO policy = new AssurancePolicyDTO.Builder()
                .id(command.getId())
                .policyName(command.getName())
                .status(command.getStatus())
                .coverageStartDate(command.getStartDate())
                .coverageEndDate(command.getEndDate())
                .build();
        AssurancePolicyDTO assurancePolicyUpdated = assurancePolicyService.updateAssurancePolicy(policy);
        // On successful update, notify event
        // EventPublisher.publish(new PolicyUpdatedEvent(assurancePolicyUpdated.getId(), command.getName()));
        return assurancePolicyUpdated.getId();
    }
}
