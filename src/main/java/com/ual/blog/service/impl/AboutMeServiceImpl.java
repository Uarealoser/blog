package com.ual.blog.service.impl;

import com.ual.blog.Mapper.AboutMeMapper;
import com.ual.blog.Mapper.BaseMapper;
import com.ual.blog.model.AboutMe;
import com.ual.blog.service.AboutMeService;
import com.ual.blog.web.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName AboutMeServiceImpl
 * @Description TODO
 * @Author ual
 */
public class AboutMeServiceImpl extends BaseServiceImpl<AboutMe> implements AboutMeService {

    @Autowired
    private AboutMeMapper aboutMeMapper;

    @Override
    public AboutMe getAboutMe(Integer status) throws GlobalException {
        return this.aboutMeMapper.getByStatus(status);
    }

    @Override
    public BaseMapper getBaseMapper() {
        return this.aboutMeMapper;
    }
}
