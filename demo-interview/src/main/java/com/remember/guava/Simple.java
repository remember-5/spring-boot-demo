package com.remember.guava;

import com.google.common.cache.*;

import java.util.concurrent.ConcurrentMap;

/**
 * @author wangjiahao
 * @date 2020/4/27
 */
public class Simple {

    public static void main(String[] args) {
        Simple simple = new Simple();
//        simple.cache1();
//        simple.cache2();
//        simple.cache3();
        simple.cache4();
    }


    private void cache1() {
        Cache<String, Object> cache = CacheBuilder.newBuilder().build();
        cache.put("hello", "world");
        // key值是大小写敏感的，所以使用cache.getIfPresent("HELLO")将返回null值。
        System.err.println(cache.getIfPresent("hello"));
        System.err.println(cache.getIfPresent("HELLO"));
    }


    /**
     * 当值不存在时，会通过CacheLoader计算出值，然后存到缓存中。
     */
    private void cache2() {
        CacheLoader<String, String> cacheLoader = new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                return sayHello(s);
            }
        };

        LoadingCache<String, String> build = CacheBuilder.newBuilder().build(cacheLoader);
        //方法getUnchecked作用为：当值不存在时，会通过CacheLoader计算出值，然后存到缓存中。
        build.getUnchecked("wangjiahao");
        System.err.println(build.getIfPresent("wangjiahao"));
        ConcurrentMap<String, String> stringStringConcurrentMap = build.asMap();
        stringStringConcurrentMap.forEach((k, v) -> {
            System.err.println("key=" + k + " value = " + v);
        });

    }


    private String sayHello(String s) {
        return String.format("hello %s", s);
    }


    /**
     * 驱逐机制
     */
    private void cache3() {
        Cache<String, String> build = CacheBuilder.newBuilder().maximumSize(3).build();
        build.put("k1", "v1");
        build.put("k2", "v2");
        build.put("k3", "v3");
        build.put("k4", "v4");
        build.put("k5", "v5");
        build.put("k6", "v6");

        // 打印size
        System.err.println(build.size());
        // 我们限制最多只能存储3个值，所以k4的存入把最早的k1给驱逐出去了，类似于FIFO。
        System.err.println(build.getIfPresent("k1"));
        System.err.println(build.asMap());


    }


    /**
     * 限制缓存大小
     */
    private void cache4() {

        Weigher<String, String> weigher = (k, v) -> v.length();

        Cache<String, String> build = CacheBuilder.newBuilder().maximumWeight(15).weigher(weigher).build();

        build.put("k1", "11111");
        build.put("k2", "22222");
        build.put("k3", "33333");
        build.put("k4", "4444");
        build.put("k5", "5555");
        System.err.println(build.size());
        System.err.println(build.getIfPresent("k2"));
        System.err.println(build.asMap());
    }


}
