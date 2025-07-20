package com.firstclub.membership.dto;

import lombok.Data;

@Data
public class UpgradeRequest {
    private Long userId;
    private Long targetPlanId;
} 