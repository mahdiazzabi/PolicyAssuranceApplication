CREATE TABLE IF NOT EXISTS assurance_policy (
                                                id INT PRIMARY KEY AUTO_INCREMENT,
                                                policy_name VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL,
    coverage_start_date DATE NOT NULL,
    coverage_end_date DATE NOT NULL,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP NOT NULL
    );
INSERT INTO assurance_policy (
    policy_name,
    status,
    coverage_start_date,
    coverage_end_date,
    created_date,
    updated_date
) VALUES (
             'PoliceTest',
             'ACTIVE',
             '2024-01-01',
             '2026-01-01',
             CURRENT_TIMESTAMP,
             CURRENT_TIMESTAMP
         );