package com.ual.blog.Mapper;

import com.ual.blog.model.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostMapper extends BaseMapper<Post>{
    /**
     * 通过分类ID获取文章列表
     * @param categoryId
     * @return
     */
    List<Post> queryPostByCategoryId(@Param("categoryId")Integer categoryId,@Param("status")Integer status,@Param(
            "title")String title);

    /**
     * 获取文章列表
     * @param status 状态 1:发布 2：操作 null：全部
     * @return
     */
    List<Post> getList(@Param("status") Integer status);

    /**
     * 归档列表
     * @return
     */
    List<Post> getArchiveList();

    /**
     * 通过文章 URL 获取文章内容
     * @param postUrl
     * @return
     */
    Post getByPostUrl(String postUrl);

}
