package com.firstclub.membership.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Silver, Gold, Platinum

    @Column(nullable = false)
    private int level; // For ordering

    @Lob
    private String criteriaJson; // JSON for business rules

    @Column(nullable = false)
    private boolean isActive;

    private String description;
} 