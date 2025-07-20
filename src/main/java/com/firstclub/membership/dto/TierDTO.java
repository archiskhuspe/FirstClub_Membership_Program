package com.firstclub.membership.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TierDTO {
    private Long id;
    private String name;
    private int level;
    private String criteriaJson;
    private boolean isActive;
    private String description;
    private List<BenefitDTO> benefits;
} 