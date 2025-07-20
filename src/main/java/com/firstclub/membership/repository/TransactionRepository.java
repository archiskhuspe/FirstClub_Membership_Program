package com.firstclub.membership.repository;

import com.firstclub.membership.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
} 