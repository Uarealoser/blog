package com.ual.blog.Mapper;

import com.ual.blog.model.AboutMe;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AboutMeMapper extends BaseMapper<AboutMe> {
    //通过状态获取
    AboutMe getByStatus(@Param("status") Integer status);
}
