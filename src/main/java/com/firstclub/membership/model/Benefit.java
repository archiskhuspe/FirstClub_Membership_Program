package com.firstclub.membership.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Benefit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Column(nullable = false)
    private String type; // delivery, discount, coupon, support, etc.

    @Column(name = "benefit_value")
    private String benefitValue; // e.g., 10% for discount, or free for delivery

    private String applicableTo; // categories, items, etc.
} 