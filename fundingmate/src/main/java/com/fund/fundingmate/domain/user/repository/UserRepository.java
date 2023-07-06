package com.fund.fundingmate.domain.user.repository;

import com.fund.fundingmate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
}
