package com.firstclub.membership.dto;

import lombok.Data;

@Data
public class MembershipStatusResponse {
    private Long membershipId;
    private String plan;
    private String tier;
    private String startDate;
    private String expiryDate;
    private boolean autoRenew;
    private String status;
} 