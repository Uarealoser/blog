package com.ual.blog.service;

import com.ual.blog.model.AboutMe;
import com.ual.blog.web.exception.GlobalException;

/**
 * @ClassName AboutMeService
 * @Description TODO
 * @Author ual
 */
public interface AboutMeService extends BaseService<AboutMe>{
    AboutMe getAboutMe(Integer status)throws GlobalException;
}
