package com.gsh.springbootquick.test;


import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author GSH
 * @create 2022/8/30 16:58
 *
 * getResource,classPathResource,getResourceAsStream可以获取到resources根路径
 */
public class NIOTest {

    public static void main(String[] args) throws IOException {
        String filePath = "src/main/resources/static/test.txt";
        System.out.println(NIOTest.class.getResource("").getPath());
        System.out.println(NIOTest.class.getClassLoader().getResource("").getPath());
        System.out.println(ClassLoader.getSystemClassLoader().getResource("").getPath());
        System.out.println(ClassLoader.getSystemResource("").getPath());

        System.out.println("======class.getResource====");
        System.out.println(NIOTest.class.getResource("/static/test.txt").getPath());
//        System.out.println(NIOTest.class.getClassLoader().getResource("/static/test.txt").getPath());
//        System.out.println(ClassLoader.getSystemClassLoader().getResource("/static/test.txt").getPath());
//        System.out.println(ClassLoader.getSystemResource("/static/test.txt").getPath());

        System.out.println("==============classPathResource============= ");
        ClassPathResource classPathResource = new ClassPathResource("/static/test.txt");
        InputStream inputStream = classPathResource.getInputStream();
        getFileContent(inputStream);


        System.out.println("=====getResourceAsStream======");
        InputStream resource = NIOTest.class.getResourceAsStream("/static/test.txt");
        getFileContent(resource);

        //        Files.write(Paths.get(filePath), Content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

        System.out.println("=======readAllBytes=========");
        byte[] data = Files.readAllBytes(Paths.get(filePath));
        System.out.println(new String(data, StandardCharsets.UTF_8));

    }


    public static void getFileContent(Object fileInPath) throws IOException {
        BufferedReader br = null;
        if (fileInPath == null) {
            return;
        }
        if (fileInPath instanceof String) {
            br = new BufferedReader(new FileReader((String) fileInPath));
        } else if (fileInPath instanceof InputStream) {
            br = new BufferedReader(new InputStreamReader((InputStream) fileInPath));
        }
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }
}
