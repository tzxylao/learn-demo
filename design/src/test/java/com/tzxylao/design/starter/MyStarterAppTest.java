package com.tzxylao.design.starter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author laoliangliang
 * @date 2019/4/3 14:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyStarterAppTest {
    @Autowired
    private MyStarterApp starterApp;

    @Test
    public void sayHello() throws Exception {
        starterApp.sayHello();
    }

}