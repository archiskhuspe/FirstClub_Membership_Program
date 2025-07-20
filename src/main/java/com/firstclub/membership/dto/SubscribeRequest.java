package com.firstclub.membership.dto;

import lombok.Data;

@Data
public class SubscribeRequest {
    private Long userId;
    private Long planId;
    private boolean autoRenew;
    private String paymentMethod;
} 