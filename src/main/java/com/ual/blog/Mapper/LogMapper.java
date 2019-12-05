package com.ual.blog.Mapper;

import com.ual.blog.model.Log;

public interface LogMapper extends BaseMapper<Log> {
    void deleteAll();
}
