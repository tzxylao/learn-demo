package com.tzxylao.kpoint.fastjson;

import com.alibaba.fastjson.JSON;
import com.tzxylao.kpoint.bean.Model;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 输出json流，比如到文件
 * @author laoliangliang
 * @date 2019/5/5 14:55
 */
@Slf4j
public class Pojo {
    public static void main(String[] args) throws IOException {
        Model model = new Model();
        model.setName("tom").setId(12341);
        OutputStream outputStream = new FileOutputStream("D:\\logs\\fastjson");
        int i = JSON.writeJSONString(outputStream, model);
        log.info("result:{}", i);
    }
}
