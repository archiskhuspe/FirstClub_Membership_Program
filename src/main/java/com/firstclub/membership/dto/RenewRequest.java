package com.firstclub.membership.dto;

import lombok.Data;

@Data
public class RenewRequest {
    private Long userId;
    private Long membershipId;
} 