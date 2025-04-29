package com.tinubu.assurance.policyAssurance.api.response;

import java.util.Date;

public record PolicyResponse(
        String policyName,
        PolicyStatusResponse status,
        Date coverageStartDate,
        Date coverageEndDate
) {
}
