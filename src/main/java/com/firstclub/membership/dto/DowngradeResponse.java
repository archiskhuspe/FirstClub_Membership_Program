package com.firstclub.membership.dto;

import lombok.Data;

@Data
public class DowngradeResponse {
    private Long membershipId;
    private String status;
    private String newTier;
    private String prorationInfo;
} 