package com.gsh.springbootquick.common.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.gsh.springbootquick.system.entity.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author GSH
 * @create 2023/2/21 9:38
 */
public class ExcelReportUtils {

    public static void excelReport(String file, List<String> colomns, List list){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        //        writer.addHeaderAlias("name", "姓名");
        //        writer.addHeaderAlias("age", "年龄");
        //        writer.addHeaderAlias("score", "分数");
        //合并单元格后的标题行，使用默认标题样式
        writer.merge(2, "content");
        //一次性写出内容，强制输出标题
        writer.write(list, true);
        //输出到文件
        writer.flush(fileOutputStream, true);
        //关闭writer，释放内存
        writer.close();
    }

    public static void main(String[] args) {
        User user = new User();
        user.setId(1L);
        user.setUsername("zhangsan");
        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("lisi");
        List<User> rows = CollUtil.newArrayList(user, user2);
        //通过工具类创建writer
        String file = "D:\\Java\\workspace\\springboot-quick\\src\\main\\java\\com\\gsh\\springbootquick\\util\\excel\\导出.xlsx";
        excelReport(file, null, rows);
        System.out.println("导出成功!");
    }
}
