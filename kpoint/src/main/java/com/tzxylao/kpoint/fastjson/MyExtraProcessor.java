package com.tzxylao.kpoint.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 转换json中数值类型
 *
 * @author laoliangliang
 * @date 2019/5/5 15:17
 */
@Slf4j
public class MyExtraProcessor implements ExtraProcessor, ExtraTypeProvider {
    @Override
    public void processExtra(Object object, String key, Object value) {
        VO vo = (VO) object;
        vo.getAttributes().put(key, value);
    }

    @Override
    public Type getExtraType(Object object, String key) {
        if ("value".equals(key)) {
            return int.class;
        }
        return null;
    }

    static class VO {
        private int id;
        private Map<String, Object> attributes = new HashMap<String, Object>();

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Map<String, Object> getAttributes() {
            return attributes;
        }
    }

    public static void main(String[] args) {
        ExtraProcessor processor = new MyExtraProcessor();

        VO vo = JSON.parseObject("{\"id\":123,\"value\":\"123456\"}", VO.class, processor);
        Assert.assertEquals(123, vo.getId());
        // value本应该是字符串类型的，通过getExtraType的处理变成Integer类型了。
        Assert.assertEquals(123456, vo.getAttributes().get("value"));
    }
}


