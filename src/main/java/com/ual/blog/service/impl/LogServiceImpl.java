package com.ual.blog.service.impl;

import com.ual.blog.Mapper.BaseMapper;
import com.ual.blog.Mapper.LogMapper;
import com.ual.blog.model.Log;
import com.ual.blog.service.LogService;
import com.ual.blog.web.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName LogServiceImpl
 * @Description TODO
 * @Author ual
 */
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public void deleteAll() {
        this.logMapper.deleteAll();
    }

    @Override
    public BaseMapper<Log> getBaseMapper() {
        return this.logMapper;
    }

    @Override
    public List<Log> getList() throws GlobalException {
        Example example=new Example((Log.class));
        example.orderBy("createTime").desc();
        return this.logMapper.selectByExample(example);
    }
}
