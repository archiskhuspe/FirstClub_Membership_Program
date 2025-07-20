package com.firstclub.membership.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String currency;
    private int durationMonths;
    private boolean isActive;
    private String description;
    private Long defaultTierId;
    private String defaultTierName;
} 