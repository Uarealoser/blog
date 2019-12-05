package com.ual.blog.Mapper;

import com.ual.blog.model.User;

public interface UserMapper extends BaseMapper<User> {
    User getByUsername(String username);
}
