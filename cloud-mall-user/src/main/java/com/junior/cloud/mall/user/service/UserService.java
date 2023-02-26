package com.junior.cloud.mall.user.service;

import com.junior.cloud.mall.common.exception.UserException;
import com.junior.cloud.mall.user.model.pojo.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    @Transactional(rollbackFor = Exception.class)
    void register(String username, String password, String email) throws UserException;

    public User login(String username, String password) throws UserException;

    public void update(User user, String signature) throws UserException;

    void checkEmail(String email) throws UserException;
}
