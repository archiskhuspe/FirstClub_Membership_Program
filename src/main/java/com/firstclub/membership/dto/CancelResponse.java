package com.firstclub.membership.dto;

import lombok.Data;

@Data
public class CancelResponse {
    private Long membershipId;
    private String status;
    private String gracePeriodInfo;
} 