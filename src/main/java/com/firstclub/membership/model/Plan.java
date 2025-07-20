package com.firstclub.membership.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Monthly, Quarterly, Yearly

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private int durationMonths;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_tier_id")
    private Tier defaultTier;

    @Column(nullable = false)
    private boolean isActive;

    private String description;
} 