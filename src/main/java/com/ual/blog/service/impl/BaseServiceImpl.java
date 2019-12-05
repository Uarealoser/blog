package com.ual.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.ual.blog.Mapper.BaseMapper;
import com.ual.blog.service.BaseService;
import com.ual.blog.web.exception.GlobalException;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName BaseServiceImpl
 * @Description TODO
 * @Author ual
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    public abstract BaseMapper<T> getBaseMapper();

    @Override
    public void save(T model) throws GlobalException{
        this.getBaseMapper().insertSelective(model);
    }

    @Override
    public void update(T model) throws GlobalException {
        this.getBaseMapper().updateByPrimaryKeySelective(model);
    }

    @Override
    public T getById(Integer id) throws GlobalException {
        return this.getBaseMapper().selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id)throws GlobalException{
        this.getBaseMapper().deleteByPrimaryKey(id);
    }

    @Override
    public List<T> getList() throws GlobalException {
        return this.getBaseMapper().selectAll();
    }

    @Override
    public List<T> getByPage(Integer pageNum, Integer pageSize) throws GlobalException {
        PageHelper.startPage(pageNum,pageSize);
        return this.getList();
    }

    @Override
    public List<T> getByExamplePage(Example example, Integer pageNum, Integer pageSize) throws GlobalException {
        PageHelper.startPage(pageNum,pageSize);
        return this.getBaseMapper().selectByExample(example);
    }



}
