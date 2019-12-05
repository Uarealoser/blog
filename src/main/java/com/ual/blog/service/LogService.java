package com.ual.blog.service;

import com.ual.blog.model.Log;

public interface LogService extends BaseService<Log> {
    void deleteAll();
}
