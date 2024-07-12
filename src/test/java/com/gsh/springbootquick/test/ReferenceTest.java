package com.gsh.springbootquick.test;

import org.junit.Test;
//import sun.misc.Cleaner;

import java.lang.ref.*;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author GSH
 * @create 2022/12/4 10:49
 */
public class ReferenceTest {
    public static void main(String[] args) {

    }

    @Test
    public void StrongRefenenceDemo(){
        Object o1 = new Object();
        Object o2 = o1;
        o1 = null;
        System.gc();
        System.out.println(o1);  //null
        System.out.println(o2);  //java.lang.Object@2503dbd3
    }

    @Test
    public void softRefMemoryEnough() {
        Object o1 = new Object();
        SoftReference<Object> s1 = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(s1.get());

        o1 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(s1.get());
    }

    /**
     * JVM配置`-Xms5m -Xmx5m` ，然后故意new一个一个大对象，使内存不足产生 OOM，看软引用回收情况
     */
    @Test
    public void softRefMemoryNotEnough() {
        Object o1 = new Object();
        SoftReference<Object> s1 = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(s1.get());

        o1 = null;
        byte[] bytes = new byte[10 * 1024 * 1024];

        System.out.println(o1);
        System.out.println(s1.get());
    }

    @Test
    public void WeakReference(){
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        Object o1 = new Object();
        String s1 = new String("abc");
        String s2 = "def";
        WeakReference<Object> w1 = new WeakReference<>(o1, queue);
        WeakReference<String> w2 = new WeakReference<>(s1, queue);
        WeakReference<String> w3 = new WeakReference<>(s2, queue);

        System.out.println(o1);
        System.out.println(w1.get());
        System.out.println(s1);
        System.out.println(w2.get());
        System.out.println(s2);
        System.out.println(w3.get());
        System.out.println("------gc------");
        o1 = null;
        s1 = null;
        s2 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(w1.get());
        System.out.println(s1);
        System.out.println(w2.get());
        System.out.println(s2);
        System.out.println(w3.get());
        System.out.println("queue = "+ w1);
    }

    @Test
    public void myHashMap() {
        HashMap<String, String> map = new HashMap<>();
        String key = new String("k1");
        String value = "v1";
        map.put(key, value);
        System.out.println(map);

        key = null;
        System.gc();

        System.out.println(map);
    }

    @Test
    public void myWeakHashMap() throws InterruptedException {
        WeakHashMap<String, String> map = new WeakHashMap<>();
        String key2 = "weak2";
        // 刚开始写成了上边的代码
        //思考一下，写成上边那样会怎么样？ 那可不是引用了
        String key = new String("weak");
        String value = "map";
        map.put(key, value);
        map.put(key2, value);
        System.out.println(map);
        //去掉强引用
        key = null;
        key2 = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println(map);
    }

    @Test
    public void PhantomReferenceTest(){
        Object o1 = new Object();
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1, queue);

        System.out.println(o1);
        System.out.println(queue.poll());
        System.out.println(phantomReference.get());

        o1 = null;
        System.gc();
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }


        System.out.println(o1);
        System.out.println(queue.poll()); //引用队列中
        System.out.println(phantomReference.get());
    }

//    @Test
//    public void cleaningTest() throws Exception {
//        try (CleaningExample o = new CleaningExample(11)){
//
//        }
//        CleaningExample o2 = new CleaningExample(22);
//        System.gc();
//        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
//    }

//    static class CleaningExample implements AutoCloseable {
//        private final Cleaner.Cleanable cleanable;
//
//        public CleaningExample(int s) {
//            State state = new State(s);
//            Cleaner cleaner = Cleaner.create();
//            cleanable = cleaner.register(this, state);
//        }
//
//        static class State implements Runnable {
//
//            private final int s;
//
//            public State(int s) {
//                this.s = s;
//            }
//
//            @Override
//            public void run() {
//                System.out.println("State runnable in action.State value = " + s);
//            }
//        }
//
//        @Override
//        public void close() throws Exception {
//            cleanable.clean();
//        }
//    }
}


