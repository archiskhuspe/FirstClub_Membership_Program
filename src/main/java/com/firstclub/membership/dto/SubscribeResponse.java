package com.firstclub.membership.dto;

import lombok.Data;

@Data
public class SubscribeResponse {
    private Long membershipId;
    private String status;
    private String startDate;
    private String expiryDate;
    private String tier;
} 