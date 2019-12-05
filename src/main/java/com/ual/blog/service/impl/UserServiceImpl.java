package com.ual.blog.service.impl;

import com.ual.blog.Mapper.BaseMapper;
import com.ual.blog.Mapper.UserMapper;
import com.ual.blog.model.User;
import com.ual.blog.service.UserService;
import com.ual.blog.web.exception.GlobalException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author ual
 */
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) throws GlobalException {
        return this.userMapper.getByUsername(username);
    }

    @Override
    public void updatePwd(String username, String oldpwd, String newpwd) throws GlobalException {
        User user=this.findByUsername(username);
        if(user==null){
            throw new GlobalException(403,"用户不存在");
        }
        if(!user.getPassword().equals(DigestUtils.md5Hex(oldpwd))){
            throw new GlobalException(403,"未输入正确的旧密码");
        }
        User tmp=new User();
        tmp.setId(user.getId());
        tmp.setPassword(DigestUtils.md5Hex(newpwd));

        this.userMapper.updateByPrimaryKeySelective(tmp);
    }

    @Override
    public BaseMapper<User> getBaseMapper() {
        return this.userMapper;
    }
}
