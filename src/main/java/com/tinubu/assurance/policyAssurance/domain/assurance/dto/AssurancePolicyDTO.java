package com.tinubu.assurance.policyAssurance.domain.assurance.dto;
import java.util.Date;


public class AssurancePolicyDTO {
    private Integer id;
    private String policyName;
    private AssurancePolicyStatusDTO status;
    private Date coverageStartDate;
    private Date coverageEndDate;
    private Date createdDate;
    private Date updatedDate;

    private AssurancePolicyDTO(Builder builder) {
        this.id = builder.id;
        this.policyName = builder.policyName;
        this.status = builder.status;
        this.coverageStartDate = builder.coverageStartDate;
        this.coverageEndDate = builder.coverageEndDate;
        this.createdDate = builder.createdDate;
        this.updatedDate = builder.updatedDate;
    }

    public Integer getId() {
        return id;
    }

    public String getPolicyName() {
        return policyName;
    }

    public AssurancePolicyStatusDTO getStatus() {
        return status;
    }

    public Date getCoverageStartDate() {
        return coverageStartDate;
    }

    public Date getCoverageEndDate() {
        return coverageEndDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public static class Builder {
        private Integer id;
        private String policyName;
        private AssurancePolicyStatusDTO status;
        private Date coverageStartDate;
        private Date coverageEndDate;
        private Date createdDate;
        private Date updatedDate;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder policyName(String policyName) {
            this.policyName = policyName;
            return this;
        }

        public Builder status(AssurancePolicyStatusDTO status) {
            this.status = status;
            return this;
        }

        public Builder coverageStartDate(Date coverageStartDate) {
            this.coverageStartDate = coverageStartDate;
            return this;
        }

        public Builder coverageEndDate(Date coverageEndDate) {
            this.coverageEndDate = coverageEndDate;
            return this;
        }

        public Builder createdAt(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder updatedAt(Date updatedDate) {
            this.updatedDate = updatedDate;
            return this;
        }

        public AssurancePolicyDTO build() {
            return new AssurancePolicyDTO(this);
        }
    }
}

