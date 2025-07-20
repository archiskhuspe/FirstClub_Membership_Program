package com.firstclub.membership.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatalogEntryDTO {
    private PlanDTO plan;
    private TierDTO tier;
    private List<BenefitDTO> benefits;
} 