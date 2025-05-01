package com.tinubu.assurance.policyAssurance.application.command;

import com.tinubu.assurance.policyAssurance.domain.assurance.dto.AssurancePolicyStatusDTO;
import java.util.Date;

public class CreateAssurancePolicyCommand {
    private final String name;
    private final AssurancePolicyStatusDTO status;
    private final Date startDate;
    private final Date endDate;

    public CreateAssurancePolicyCommand(String name, AssurancePolicyStatusDTO status, Date startDate, Date endDate) {
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }
    public AssurancePolicyStatusDTO getStatus() {
        return status;
    }
    public Date getStartDate() {
        return startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
}
