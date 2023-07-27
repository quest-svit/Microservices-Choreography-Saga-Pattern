package com.dange.tanmay.repository;

import com.dange.tanmay.entity.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountBalance, Long> {
}
