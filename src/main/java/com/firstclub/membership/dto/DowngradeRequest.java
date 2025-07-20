package com.firstclub.membership.dto;

import lombok.Data;

@Data
public class DowngradeRequest {
    private Long userId;
    private Long targetPlanId;
} 