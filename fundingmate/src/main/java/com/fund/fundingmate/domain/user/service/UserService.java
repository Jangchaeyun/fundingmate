package com.fund.fundingmate.domain.user.service;

import com.fund.fundingmate.domain.user.entity.User;

public interface UserService {
    void join(User user) throws  Exception;
    void login(User user) throws Exception;

    void modifyPw(Long id, String password);
}
