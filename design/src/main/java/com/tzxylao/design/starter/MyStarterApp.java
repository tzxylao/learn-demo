package com.tzxylao.design.starter;

import com.shouhuda.websocket.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author laoliangliang
 * @date 2019/4/3 14:21
 */
@Component
public class MyStarterApp {
    @Autowired
    private PersonService personService;

    public void sayHello() {
        personService.sayHello();
    }
}
