package com.dange.tanmay.repository;

import com.dange.tanmay.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountEntityRepository extends JpaRepository<AccountEntity,Long> {
}
