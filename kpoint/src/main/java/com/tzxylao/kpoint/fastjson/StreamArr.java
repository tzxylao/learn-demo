package com.tzxylao.kpoint.fastjson;

import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import com.tzxylao.kpoint.bean.VO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 超大JSON数组序列化
 *
 * @author laoliangliang
 * @date 2019/5/5 15:08
 */
public class StreamArr {
    public static void main(String[] args) throws IOException {
        JSONWriter writer = new JSONWriter(new FileWriter("/tmp/huge.json"));
        writer.startArray();
        for (int i = 0; i < 1000 * 1000; ++i) {
            writer.writeValue(new VO());
        }
        writer.endArray();
        writer.close();


        JSONReader reader = new JSONReader(new FileReader("/tmp/huge.json"));
        reader.startArray();
        while(reader.hasNext()) {
            VO vo = reader.readObject(VO.class);
            // handle vo ...
        }
        reader.endArray();
        reader.close();
    }
}

