package com.ual.blog.service.impl;

import com.ual.blog.Mapper.BaseMapper;
import com.ual.blog.Mapper.ParamMapper;
import com.ual.blog.component.CommonMap;
import com.ual.blog.model.Param;
import com.ual.blog.service.ParamService;
import com.ual.blog.web.context.SpringContext;
import com.ual.blog.web.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName ParamServiceImpl
 * @Description TODO
 * @Author ual
 */
public class ParamServiceImpl extends BaseServiceImpl<Param> implements ParamService {

    @Autowired
    private ParamMapper paramMapper;


    @Override
    public List<Param> getParamList(Integer type) throws GlobalException {
        return this.paramMapper.getParamList(type);
    }

    @Override
    public void updateValue(Integer pk, String value) throws GlobalException {
            Param param=new Param();
            param.setId(pk);
            param.setParamValue(value);
            this.paramMapper.updateByPrimaryKeySelective(param);
            //刷新
            this.refreshParam();
    }

    @Override
    public void updatePerParam(Map<String, String> map) throws GlobalException {
        if(map.isEmpty()){
            return;
        }

        //key要与数据库中的paramName对应
        Set<String> names=map.keySet();

        List<Param> paramList=new ArrayList<>(names.size());
        Param param;
        for(String name:names){
            param=new Param();
            param.setParamName(name);
            param.setParamValue(map.get(name));
            paramList.add(param);
        }

        //批量修改
        this.paramMapper.updateByParamName(paramList);
        //刷新
        this.refreshParam();
    }

    @Override
    public BaseMapper<Param> getBaseMapper() {
        return this.paramMapper;
    }
//?
    private void refreshParam(){
        CommonMap commonMap = SpringContext.getBeanByName("commonMap");
        List<Param> paramList=null;
        try {
            paramList=this.paramMapper.selectAll();
        }catch (Exception e){
            e.printStackTrace();
        }

        for(Param param:paramList){
            commonMap.put(param.getParamName(),param.getParamValue());
        }
    }
}
