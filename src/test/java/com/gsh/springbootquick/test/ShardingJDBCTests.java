//package com.gsh.springbootquick.test;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.gsh.springbootquick.system.entities.Course;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @author GSH
// * @create 2022/7/9 16:03
// */
//@SpringBootTest
//public class ShardingJDBCTests {
//
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//
//    @Autowired
//    CourseMapper courseMapper;
//
//    @Test
//    public void insert() {
//        Course course = new Course(1L, "aa", 100L, "1", new Date());
//        courseMapper.insert(course);
//        for (int i = 1; i <= 6; i++) {
//            Course course2 = new Course();
//            course2.setCname("java");
//            course2.setUserId(101L);
//            course2.setCstatus("Normal");
//            courseMapper.insert(course2);
//        }
//    }
//
//    @Test
//    public void select() {
//        Course course = courseMapper.selectOne(new QueryWrapper<Course>().eq("user_id", 225L));
//        System.out.println(course.toString());
//    }
//
//    @Test
//    public void substring() {
//        for (int i = 1; i <= 3; i++) {
//            Course c = new Course();
//            c.setCname("java"+i);
//            c.setUserId((long) i);
//            c.setCstatus("Normal");
////            c.setTime(sdf.format(new Date()));
//            courseMapper.insert(c);
//        }
//    }
//}
