package com.gsh.springbootquick;

import com.gsh.springbootquick.bean.Person;
import com.gsh.springbootquick.service.HelloService;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;


@SpringBootTest
class SpringbootQuickApplicationTests {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    public void testHelloService(){
        boolean b = ioc.containsBean("helloService");
        System.out.println(b);
    }

    @Test
    void contextLoads() {
//        System.out.println(person);
        logger.trace(()->"trace.....");
        logger.debug(()->"debug....");
        logger.info(()->"info...");
        logger.warn(()->"warn...");
        logger.error(()->"error...");
        logger.info(()->person.toString());
    }

}
