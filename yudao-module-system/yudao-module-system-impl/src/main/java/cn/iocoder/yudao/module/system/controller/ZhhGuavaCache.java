package cn.iocoder.yudao.module.system.controller;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

public class ZhhGuavaCache {

    private static Cache<String,String> cache = CacheBuilder.newBuilder()
            .maximumSize(3000)
            .expireAfterWrite(3, TimeUnit.HOURS)
            .recordStats()
            .build();

    public static void put(String key, String value) {
        cache.put(key, value);
    }

    public static String get(String key) {
        return cache.getIfPresent(key);
    }

}