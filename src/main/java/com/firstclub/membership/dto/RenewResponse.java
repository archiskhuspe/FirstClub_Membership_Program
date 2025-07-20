package com.firstclub.membership.dto;

import lombok.Data;

@Data
public class RenewResponse {
    private Long membershipId;
    private String status;
    private String newExpiryDate;
    private String paymentInfo;
} 