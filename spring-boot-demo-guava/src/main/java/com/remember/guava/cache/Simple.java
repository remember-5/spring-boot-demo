package com.remember.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * @author wangjiahao
 * @date 2020/4/27
 */
public class Simple {

    public static void main(String[] args) {
        Cache<String , Object> cache = CacheBuilder.newBuilder().build();
        cache.put("hello","world");
        System.err.println(cache.getIfPresent("hello"));
    }

}
