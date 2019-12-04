package com.ual.blog.Mapper;

import com.ual.blog.model.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ParamMapper extends BaseMapper<Param> {
    /**
     *参数列表
     * @param type 1：全局 2：个人
     * @return
     */
    List<Param> getParamList(Integer type);

    /**
     *通过参数名修改参数值
     * @Param paramList
     */
    void updateByParamName(@org.apache.ibatis.annotations.Param("paramList")List<Param> paramList);
}
