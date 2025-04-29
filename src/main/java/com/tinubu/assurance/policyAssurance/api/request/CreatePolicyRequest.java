package com.tinubu.assurance.policyAssurance.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class CreatePolicyRequest {

    @NotBlank(message = "Le nom de la police ne doit pas être vide.")
    private String policyName;

    @NotNull(message = "Le statut est obligatoire.")
    private String status;

    @NotNull(message = "La date de début de couverture est obligatoire.")
    private Date coverageStartDate;

    @NotNull(message = "La date de fin de couverture est obligatoire.")
    private Date coverageEndDate;

    public CreatePolicyRequest() {
    }

    public CreatePolicyRequest(String policyName, String status, Date coverageStartDate, Date coverageEndDate) {
        this.policyName = policyName;
        this.status = status;
        this.coverageStartDate = coverageStartDate;
        this.coverageEndDate = coverageEndDate;
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
