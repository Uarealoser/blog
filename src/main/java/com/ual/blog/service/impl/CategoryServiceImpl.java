package com.ual.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.ual.blog.Mapper.BaseMapper;
import com.ual.blog.Mapper.CategoryMapper;
import com.ual.blog.common.utils.CacheUtils;
import com.ual.blog.model.Category;
import com.ual.blog.service.CategoryService;
import com.ual.blog.web.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Author ual
 */
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

    //颜色数组
    private static final String [] COLORS={"text-default","text-primary","text-success","text-info","text-warning",
            "text-danger","text-purple","text-pink"};

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public BaseMapper<Category> getBaseMapper() {
        return this.categoryMapper;
    }

    @Override
    public Category getCategoryByName(String categoryName) throws GlobalException {
        return this.categoryMapper.getByName(categoryName);
    }

    @Override
    public int getCategoryCount() throws GlobalException {
        return this.categoryMapper.selectCount(null);
    }

    @Cacheable(key="'categoryCache'")
    @Override
    public List<Map<String, Object>> getCategoryList() {
        return this.categoryMapper.getCategoryList();
    }

    @Transactional
    @Override
    public void save(Category category)throws GlobalException{
        if(StringUtils.isEmpty(category.getImgUrl())){
            category.setImgUrl("/portal/images/category_default.jpg");
        }
        if(StringUtils.isEmpty(category.getColor())){
            category.setColor(COLORS[new Random().nextInt(COLORS.length)]);
        }
        if(StringUtils.isEmpty(category.getSort())){
            category.setSort(99);
        }

        this.categoryMapper.insert(category);
        CacheUtils.deleteByName("categoryCache");
    }

    @Transactional
    @Override
    public void update(Category category)throws GlobalException{
        if (StringUtils.isEmpty(category.getImgUrl())) {
            category.setImgUrl("/portal/images/category_default.jpg");
        }

        if (StringUtils.isEmpty(category.getColor())) {
            category.setColor(COLORS[new Random().nextInt(COLORS.length)]);
        }

        if (StringUtils.isEmpty(category.getSort())) {
            category.setSort(99);
        }

        this.categoryMapper.updateByPrimaryKeySelective(category);
        CacheUtils.deleteByName("categoryCache");
    }

    @Transactional
    @Override
    public void delete(Integer id)throws GlobalException{
        this.categoryMapper.deleteByPrimaryKey(id);
        CacheUtils.deleteByName("categoryCache");
    }

    @Override
    public List<Category> getByPage(Integer pageNum,Integer pageSize)throws GlobalException{
        Example example=new Example(Category.class);
        example.orderBy("sort").asc();

        PageHelper.startPage(pageNum,pageSize);
        return this.categoryMapper.selectByExample(example);
    }
}
