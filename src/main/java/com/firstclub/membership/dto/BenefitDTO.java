package com.firstclub.membership.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenefitDTO {
    private Long id;
    private String name;
    private String description;
    private String type;
    private String benefitValue;
    private String applicableTo;
} 