package com.me.common.core.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

/**
 * spring cache 工具类
 *
 * me.system
 **/
@SuppressWarnings(value = { "unchecked", "rawtypes" })
@Component
public class MeCache
{

    static Map<String, Object> cache = new ConcurrentHashMap();

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value)
    {
        cache.put(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     */
//    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit)
//    {
//        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
//    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public Object getCacheObject(final String key)
    {
        return (Object)cache.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public boolean deleteObject(final String key)
    {
        ;
        return  cache.remove(key)!=null?true:false;
    }

    /**
     * 删除集合对象
     *
     * @param keys  模糊匹配关键字
     * @return
     */
    public long deleteObjects(final String keys)
    {
        cache = cache.entrySet().stream().filter(e->(!e.getKey().contains(keys)))
                .collect(Collectors.toMap(
                    (e) -> (String) e.getKey(),
                    (e) -> e.getValue()
        ));
        return 1;
    }


    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Map<String, Object> keys(final String pattern)
    {
        return cache.entrySet().stream().filter(e->(e.getKey().contains(pattern)))
                .collect(Collectors.toMap(
                        (e) -> (String) e.getKey(),
                        (e) -> e.getValue()
                ));
    }
}
