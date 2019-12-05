package com.ual.blog.service.impl;

import com.ual.blog.Mapper.BaseMapper;
import com.ual.blog.Mapper.CategoryMapper;
import com.ual.blog.Mapper.PostMapper;
import com.ual.blog.common.utils.CacheUtils;
import com.ual.blog.common.utils.DateUtil;
import com.ual.blog.component.LuceneService;
import com.ual.blog.model.Category;
import com.ual.blog.model.Post;
import com.ual.blog.service.CategoryService;
import com.ual.blog.service.PostService;
import com.ual.blog.web.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @ClassName PostServiceImpl
 * @Description TODO
 * @Author ual
 */
public class PostServiceImpl extends BaseServiceImpl<Post> implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LuceneService luceneService;



    @Transactional
    @Override
    public void save(Post post)throws GlobalException{
        Category category=categoryService.getById(post.getCategoryId());
        if(category==null){
            throw new GlobalException(400,"该分类不存在");
        }

        if(StringUtils.isEmpty(post.getImgUrl())){
            post.setImgUrl("material-" + (new Random().nextInt(30) + 1 + ".jpg"));
        }

        Date now =new Date();
        String dateStr= DateUtil.formateToStr(now,"yyyy-MM-dd");
        String[] dates=dateStr.split("-");

        post.setPublishDate(now);
        post.setYear(dates[0]);
        post.setMonth(dates[1]);
        post.setDay(dates[2]);
        post.setCategoryName(category.getName());
        post.setPostUrl(post.getYear()+"/"+post.getMonth()+"/"+post.getDay()+"/"+post.getTitle().replace(" ","-"+"/"));

        this.postMapper.insert(post);

        //加索引
        this.luceneService.add(post);
        //清理缓存
        CacheUtils.deleteAll();
        //把文章url推送给百度todo

    }


    @Override
    public List<Post> getPyCategoryId(Integer categoryId, Integer pageNum, Integer pageSize, String title) throws GlobalException {
        return null;
    }

    @Override
    public List<Post> getListPyPage(Integer status, Integer pageNum, Integer pageSize) throws GlobalException {
        return null;
    }

    @Override
    public List<Post> getArchiveList() throws GlobalException {
        return null;
    }

    @Override
    public List<Post> queryByCategory(String categoryName, Integer pageNum, Integer pageSize) throws GlobalException {
        return null;
    }

    @Override
    public Post getByPostUrl(String postUrl) throws GlobalException {
        return null;
    }

    @Override
    public Post getPreviousInfo(Integer id) throws GlobalException {
        return null;
    }

    @Override
    public Post getNextInfo(Integer id) throws GlobalException {
        return null;
    }

    @Override
    public List<Post> queryByKeyworld(String keyword) throws GlobalException {
        return null;
    }

    @Override
    public int getPostCount(Integer status) throws GlobalException {
        return 0;
    }

    @Override
    public int getTagCount() throws GlobalException {
        return 0;
    }

    @Override
    public void deleteBatch(String ids) throws GlobalException {

    }

    @Override
    public void importFiles(String path) throws GlobalException {

    }

    @Override
    public BaseMapper<Post> getBaseMapper() {
        return this.postMapper;
    }
}
