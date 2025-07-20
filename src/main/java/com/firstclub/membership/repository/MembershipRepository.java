package com.firstclub.membership.repository;

import com.firstclub.membership.model.Membership;
import com.firstclub.membership.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    Optional<Membership> findByUserAndStatus(User user, String status);
} 