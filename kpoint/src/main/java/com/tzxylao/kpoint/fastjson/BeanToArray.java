package com.tzxylao.kpoint.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tzxylao.kpoint.bean.Model;
import lombok.extern.slf4j.Slf4j;

/**
 * fastjson的一种映射模式
 * 普通模式下，JavaBean映射成json object，BeanToArray模式映射为json array。
 *
 * @author laoliangliang
 * @date 2019/5/5 14:35
 */
@Slf4j
public class BeanToArray {
    public static void main(String[] args) {
        Model model = new Model();
        model.setId(1001);
        model.setName("niubi");

        String text = JSON.toJSONString(model, SerializerFeature.BeanToArray);
        log.info(text);

        Model jsonObject = JSON.parseObject(text,Model.class, Feature.SupportArrayToBean);
        log.info(jsonObject.toString());
    }
}
