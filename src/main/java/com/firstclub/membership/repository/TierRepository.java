package com.firstclub.membership.repository;

import com.firstclub.membership.model.Tier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TierRepository extends JpaRepository<Tier, Long> {
} 