package com.tinubu.assurance.policyAssurance.application.query;

public class GetAssurancePolicyByIdQuery {

    private final Integer id;

    public GetAssurancePolicyByIdQuery(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
