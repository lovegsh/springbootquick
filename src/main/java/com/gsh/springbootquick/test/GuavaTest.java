package com.gsh.springbootquick.test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 主要包括两种构建缓存的方式，包括LoadingCache和CallableCache 。LoadingCache是指在构建缓存时就设置好加载方法，CallableCache则是在每次获取缓存时设置加载方法。
 * 这里将 Guava Cache 中的方法分为4类进行介绍，如下所示：
 *
 * 构建缓存的方法，包括LoadingCache和CallableCache；
 * 缓存刷新策略（refreshAfterWrite）和缓存过期策略（expireAfterWrite和expireAfterAccess）
 * 缓存过期方法，包括主动使缓存过期（invalidate和invalidateAll）和基于引用的回收策略（weakKeys、weakValues和softValues）
 * 其他方法，例如recordStats（记录缓存命中状态）、maximumSize（设定缓存的最大个数）
 */
public class GuavaTest {
    private static final int THREE = 3;
    private static Map<Integer, String> dbData = new HashMap<>(3);
    static {
        dbData.put(1, "shanghai");
        dbData.put(2, "beijing");
        dbData.put(3, "shenzhen");
    }

    @SneakyThrows
    private static void sleep(long millis) {
        Thread.sleep(millis);
    }

    private static String getCityFromDb(Integer code) {
        sleep(1000);
        return dbData.get(code) + " " + System.currentTimeMillis();
    }

    private static ListeningExecutorService poolExecutor1 = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(20));


    public static void main(String[] args) {
//        createDemo();


    }


    //LoadingCache是指在构建缓存时就设置好加载方法，CallableCache则是在每次获取缓存时设置加载方法
    @SneakyThrows
    private static void createDemo() {
        LoadingCache<Integer, String> guavaLoadingCache = CacheBuilder
                .newBuilder()
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer code) {
                        return getCityFromDb(code);
                    }
                });

        Cache<Integer, String> guavaCallableCache = CacheBuilder
                .newBuilder()
                .build();
        System.out.println(guavaLoadingCache.get(1));
        System.out.println(guavaCallableCache.get(1, () -> getCityFromDb(1)));
    }

    /**
     * 缓存过期有两种策略，一种是在写之后一段时候过期（expireAfterWrite），
     *                 另一种是在读或者写之后一段时间过期（expireAfterAccess），
     * 二者的区别在于一个是写，一个是读或者写。缓存刷新则是只有在写之后一段时候刷新（refreshAfterWrite）的策略，
     * 这里也介绍下removalListener方法，这个方法可以在缓存过期时执行一些操作
     */
    @SneakyThrows
    private static void expireAndRefreshDemo() {
        LoadingCache<Integer, String> expireCache = CacheBuilder.newBuilder()
                .expireAfterAccess(1000, TimeUnit.MILLISECONDS)
                .removalListener(removalNotification ->
                        System.out.println("the key " + removalNotification.getKey() + " is removed"))
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer integer) {
                        return getCityFromDb(integer);
                    }
                });
        expireCache.put(1, "init value");
        System.out.println(expireCache.get(1));
        Thread.sleep(1500);
        System.out.println(expireCache.get(1));
        System.out.println("————————————————");

        LoadingCache<Integer, String> refreshCache = CacheBuilder.newBuilder()
                .refreshAfterWrite(1000, TimeUnit.MILLISECONDS)
                .removalListener(removalNotification ->
                        System.out.println("the key " + removalNotification.getKey() + " is removed"))
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer integer) {
                        return getCityFromDb(integer);
                    }
                });
        System.out.println(refreshCache.get(1));
        Thread.sleep(1500);
        System.out.println(refreshCache.get(1));
    }

    @SneakyThrows
    private static void expireDemo() {
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(1800, TimeUnit.MILLISECONDS)
                .expireAfterAccess(1300, TimeUnit.MILLISECONDS)
                .removalListener(removalNotification ->
                        System.out.println("the key " + removalNotification.getKey() + " is removed"))
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer integer) {
                        return getCityFromDb(integer);
                    }
                });
        cache.put(1, "init value");
        System.out.println(cache.get(1));
        Thread.sleep(1000);
        System.out.println(cache.get(1));
        Thread.sleep(1000);
        System.out.println(cache.get(1));
        Thread.sleep(1500);
        System.out.println(cache.get(1));
    }


    //主动使缓存过期的方法包括invalidate和invalidateAll，invalidate是使单个缓存过期，invalidateAll是使全部缓存过期
    @SneakyThrows
    private static void invalidateDemo() {
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .removalListener(removalNotification ->
                        System.out.println("the key " + removalNotification.getKey() + " is removed"))
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer integer) {
                        return getCityFromDb(integer);
                    }
                });
        cache.put(1, "init value");
        System.out.println(cache.get(1));
        cache.invalidate(1);
        System.out.println(cache.get(1));
        cache.put(2, "init value");
        cache.invalidateAll();
    }

    //基于引用过期，包括weakKeys、weakValues和softValues。weakKeys的意思是清除key为，这几个引用需要慎用
    @SneakyThrows
    private static void invalidateDemo2() {
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .weakKeys()
                .weakValues()
                .removalListener(removalNotification ->
                        System.out.println("the key " + removalNotification.getKey() + " is removed"))
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer integer) {
                        return getCityFromDb(integer);
                    }
                });
        cache.put(3, new String("init key"));
        System.gc();
        System.out.println(cache.get(1));
    }


    /**
     * recordStats，该方法用于记录缓存命中的状态，便于我们更好的使用缓存
     * 可以看到这里缓存命中了一次（key=1），两次没有命中（key=2、3），加载成功两次（key=2、3）
     * 2、maximumSize，该方法用于设置缓存的最大容量，注意这里是个数，并不是内存大小。当缓存个数大于设置的最大容量时，则会使用LRU算法（通过concurrentHashMap和双向链表实现）来淘汰缓存。
     */
    @SneakyThrows
    private static void recordStatsDemo() {
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .recordStats()
                .removalListener(removalNotification ->
                        System.out.println("the key " + removalNotification.getKey() + " is removed"))
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer integer) {
                        return getCityFromDb(integer);
                    }
                });
        cache.put(1, "init value");
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.stats());
    }

    /**
     * Caffeine 是一款基于Java8开发的，高性能的的本地缓存库，出自于Benjamin Manes大神之手，
     * 大家熟悉的Spring4.3+和SpringBoot1.4+缓存的实现便是基于Caffeine。
     * Caffeine 的缓存淘汰算法是一种对LRU和LFU进行了组合优化的算法，
     * caffeine的用法与guava基本相同，有一些不同的方法。
     * 除了这两种初始化方式外，caffeine cache还提供了第三种初始化方式，异步加载方式
     */
}
