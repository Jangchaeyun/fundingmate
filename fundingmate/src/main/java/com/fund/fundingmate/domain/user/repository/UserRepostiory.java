package com.fund.fundingmate.domain.user.repository;

import com.fund.fundingmate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepostiory extends JpaRepository<User, Long> {
}
