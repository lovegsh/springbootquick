package com.gsh.springbootquick.test;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author GSH
 * @create 2022/10/25 20:50
 */
public class test2 {

    public static void main(String[] args) {
        System.out.println(123 % 10);
        Float f = .12f;
        Double d = .12;
        System.out.println(f instanceof Float);
        System.out.println(d instanceof Double);
        System.out.println(12/.75f);
        System.out.println(Arrays.asList(1, 2, 3, 4).stream().filter(i -> i != 3).toList());
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value2");
        map.put("key4", "value4");
        System.out.println("map = "+map);
//        CopyOnWriteArraySet<Map.Entry<String, Object>> entries = new CopyOnWriteArraySet<>(map.entrySet());
//        for (Map.Entry<String, Object> entry : entries ){
//            if(entry.getValue().equals("value2")){
//                map.remove(entry.getKey());
//            }
//        }
//        System.out.println("map = "+map);
        Map<String, Object> map1 = map.entrySet().stream().filter(k -> !k.getValue().equals("value2")).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        System.out.println(map1);
        //        Stream.of(map).filter(m -> m.get("key2").equals("value2")).collect(Collectors.toList()).forEach(System.out:)
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Stream<Integer> boxed = Arrays.stream(nums1).boxed();
        Set<Integer> set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums2).distinct().filter(x->set.contains(x)).toArray();
    }

    @Test
    public void test3(){
        int[] arr = new int[]{1,2,6,3,4,5,6};
        ListNode listNode = new ListNode(0, null);
        System.out.println("listNode===>"+listNode.val+"~~~"+listNode.next);
        for(int a : arr){
            listNode.val = a;
            listNode = listNode.next;
        }
        ListNode listNode1 = removeElements(listNode, 6);
        System.out.println("=====>"+node2array(listNode1));
    }

    public static ListNode removeElements(ListNode head, int val) {
        ListNode node = new ListNode(0, head);
        ListNode tem = node;
//        while(tem.next != null){
//            if(tem.next.val == val){
//                tem.next = tem.next.next;
//            } else {
//                tem = tem.next;
//            }
//        }
        while(node.next != null){
            System.out.println(" next="+node.next.val);
            if(node.next.val == val){
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return node.next;
    }

    public List node2array(ListNode head){
        List<Integer> list = new ArrayList<>();
        while (head.hasNext()){
            list.add(head.val);
        }
        return list;
    }

    @Test
    public void test2() {
        int b = '1'-'0';
        System.out.println(b);
        int c = 1 + '0';
        System.out.println(c);
    }

    @Test
    public void mySqrt() {
        int c = 0;
        int x = 10;
        int l = 0;
        int r = x;
        int mid = 0;
        int res = 0;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (mid <= x / mid){
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
            System.out.println("mid="+mid+", l="+l+", r="+r);
            if (c++ > 20) break;
        }
        System.out.println(res);

    }


}

class ListNode implements Iterator{
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public boolean hasNext() {
        return this.next == null;
    }

    @Override
    public Object next() {
        return this.next;
    }
}
