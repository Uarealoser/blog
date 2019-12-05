package com.ual.blog.common.utils;

import com.ual.blog.web.context.SpringContext;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * @ClassName CacheUtils
 * @Description TODO
 * @Author ual
 */
public class CacheUtils {

//    通过名字清理缓存
    public static void deleteByName(String cacheName){
        CacheManager cacheManager = SpringContext.getBeanByType(CacheManager.class);
        Cache cache = cacheManager.getCache(cacheName);
        cache.removeAll();
        cache.flush();
    }

//    清理所有缓存
    public static void deleteAll(){
        CacheManager cacheManager = SpringContext.getBeanByType(CacheManager.class);
        String[] cacheNames=cacheManager.getCacheNames();
        for(String cacheName:cacheNames){
            Cache cache = cacheManager.getCache(cacheName);
            cache.removeAll();
            cache.flush();
        }
    }
}
