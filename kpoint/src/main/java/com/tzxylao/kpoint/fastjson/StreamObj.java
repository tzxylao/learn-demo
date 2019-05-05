package com.tzxylao.kpoint.fastjson;

import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import com.tzxylao.kpoint.bean.VO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 超大JSON对象序列化
 *
 * @author laoliangliang
 * @date 2019/5/5 15:11
 */
public class StreamObj {
    public static void main(String[] args) throws IOException {
        JSONWriter writer = new JSONWriter(new FileWriter("/tmp/huge.json"));
        writer.startObject();
        for (int i = 0; i < 1000 * 1000; ++i) {
            writer.writeKey("x" + i);
            writer.writeValue(new VO());
        }
        writer.endObject();
        writer.close();




        JSONReader reader = new JSONReader(new FileReader("/tmp/huge.json"));
        reader.startObject();
        while(reader.hasNext()) {
            String key = reader.readString();
            VO vo = reader.readObject(VO.class);
            // handle vo ...
        }
        reader.endObject();
        reader.close();
    }
}
