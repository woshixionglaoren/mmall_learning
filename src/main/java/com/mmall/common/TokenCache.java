package com.mmall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by 华耀 王 on 2020/3/1
 * 说明：把forgetToken放到本地cache中，然后设置它的有效期
 **/

public class TokenCache {
    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);

    public static final String TOKEN_PREFIX = "token_";

    //这是Guava里面的本地缓存；initialCapacity(1000),1000是初始化的容量；maximumSize(10000)是最大缓存容量，当超过这个容量是Guava回调用LRU算法（最少使用算法）；expireAfterAccess(12, TimeUnit.HOURS)意思是有效期，为12个小时
    private static LoadingCache<String,String> localCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000).expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                //默认的数据加载实现，当调用get加载取值的时候，如果key没有对应的值，就调用这个方法进行加载。
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    public static void setKey(String key,String value){
        localCache.put(key, value);
    }

    public static String getKey(String key){
        String value = null;
        try{
            value = localCache.get(key);
            if ("null".equals(value)){
                return null;
            }
            return value;
        }catch (Exception e){
            logger.error("localCache get error",e);
        }
        return null;
    }

}
