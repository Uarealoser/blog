package com.ual.blog.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

/**
 * @ClassName CustomInterceptor
 * @Description 自定义MyBitis插件, 自动设置createTime和updateTime值
 * @Author ual
 */

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class CustomInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        //获取sql命令
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        //获取参数
        Object parameter = invocation.getArgs()[1];
        if (parameter != null) {
            Field[] declaredFields = parameter.getClass().getDeclaredFields();

            for (Field field : declaredFields) {
                if (field.getAnnotation(CreateTime.class) != null) {
                    if (sqlCommandType.INSERT.equals(sqlCommandType)) {//insert插入语句插入createTime
                        field.setAccessible(true);
                        if (field.get(parameter) == null) {
                            field.set(parameter, new Date());
                        }

                    }
                }

                if (field.getAnnotation(UpdateTime.class) != null) {//insert或update语句插入时updateTime
                    if (sqlCommandType.UPDATE.equals(sqlCommandType) || sqlCommandType.INSERT.equals(sqlCommandType)) {
                        field.setAccessible(true);
                        if (field.get(parameter) == null) {
                            field.set(parameter, new Date());
                        }
                    }
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
