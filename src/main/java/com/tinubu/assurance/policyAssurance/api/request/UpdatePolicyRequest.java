package com.tinubu.assurance.policyAssurance.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class UpdatePolicyRequest {

    @NotNull(message = "L'identifiant de la police est obligatoire.")
    private Integer policyId;

    private String policyName;

    private String status;

    private Date coverageStartDate;

    private Date coverageEndDate;

    public UpdatePolicyRequest(Integer policyId, String policyName, String status, Date coverageStartDate, Date coverageEndDate) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.status = status;
        this.coverageStartDate = coverageStartDate;
        this.coverageEndDate = coverageEndDate;
    }

    public UpdatePolicyRequest() {
    }
    public Integer getPolicyId() {
        return policyId;
    }
    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCoverageStartDate() {
        return coverageStartDate;
    }

    public void setCoverageStartDate(Date coverageStartDate) {
        this.coverageStartDate = coverageStartDate;
    }

    public Date getCoverageEndDate() {
        return coverageEndDate;
    }

    public void setCoverageEndDate(Date coverageEndDate) {
        this.coverageEndDate = coverageEndDate;
    }
}
