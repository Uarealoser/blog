package com.ual.blog.service;

import com.ual.blog.model.User;
import com.ual.blog.web.exception.GlobalException;

public interface UserService extends BaseService<User>{
    User findByUsername(String username) throws GlobalException;

    /**
     * 修改密码
     * @param username 用户名
     * @param oldpwd   旧密码
     * @param newpwd   新密码
     */
    void updatePwd(String username, String oldpwd, String newpwd) throws GlobalException;
}
