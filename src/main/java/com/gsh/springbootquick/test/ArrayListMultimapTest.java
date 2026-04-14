package com.gsh.springbootquick.test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 使用ArrayListMultimap时，当key重复时,value不会覆盖。相同Key的value都会放入与该key对应的值(value)中
 * 当一个key对应多个value,那么该map就有对应的value数量的key（意思说value有几个那么key就有几个。。。）
 */
public class ArrayListMultimapTest {
    public static void main(String[] args) {
        Aa a1 = new Aa().setId(1).setName("syl").setSex("男");
        Aa a2 = new Aa().setId(1).setName("syl1").setSex("男");
        Aa a3 = new Aa().setId(2).setName("syl2").setSex("女");
        Aa a4 = new Aa().setId(3).setName("syl3").setSex("女");
        Aa a5 = new Aa().setId(3).setName("syl3").setSex("女");
        //使用map
        toMap(a1, a2, a3, a4, a5);
        //使用multimap
        toMultimap(a1, a2, a3, a4, a5);
        List<Integer> list = Stream.of(1, 2, 3).collect(Collectors.toList());
        list.add(4);
        //list1不能进行删增
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        //list1.add(4);
    }

    /**
     *使用multimap,key重复时,value不会被覆盖
     */
    private static void toMultimap(Aa a1, Aa a2, Aa a3, Aa a4,Aa a5) {
        List<Aa> aList = Stream.of(a1, a2, a3, a4,a5).collect(Collectors.toList());
        //创建multimap
        Multimap<Integer, Aa> multimap = ArrayListMultimap.create();
        for (Aa aa : aList) {
            multimap.put(aa.getId(),aa);
        }
        System.out.println(" 000===" + multimap);

        for (Map.Entry<Integer, Collection<Aa>> entry : multimap.asMap().entrySet()) {
            Integer key = entry.getKey();
            Collection<Aa> collection = entry.getValue();
            System.out.println(key+"   ------    "+ collection);
        }
        //对multiMap进行遍历
//        multimap.keys().stream().forEach(k-> System.out.println("key:"+k+"value:"+multimap.get(k)));
        multimap.keys().forEach(k-> System.out.println(k+" ==="));
        //取出multimap的value并且进行遍历
        multimap.values().forEach(a-> System.out.println(a.getName()));
        long count = multimap.get(3).size();
        System.out.println("count = " + count);
    }

    /**
     *使用map,key重复时,value会被覆盖
     */
    private static void toMap(Aa a1, Aa a2, Aa a3, Aa a4, Aa a5) {
        //Aa::getId是map中的key,a -> a是map中value,可以通过(u1,u2)->u1指定重复之后按照那个值输出
        Map<Integer, Aa> map = Stream.of(a1,a2,a3,a4,a5).collect(Collectors.toMap(Aa::getId, a -> a, (u1, u2) -> u2));
        map.forEach((K,V)->{
            System.out.println(K+" "+V);
        });
    }
}

@Data
@Accessors(chain = true)
class Aa{
    private Integer id;
    private String name;
    private String sex;
}
