package com.tzxylao.kpoint.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 局部使用BeanToArray模式（对象数组变成值的数组）
 *
 * @author laoliangliang
 * @date 2019/5/5 14:35
 */
@Slf4j
public class BeanToArray2 {
    public static void main(String[] args) {
        Company company = new Company();
        company.code = 100;
        company.departments.add(new Department(1001, "Sales"));
        company.departments.add(new Department(1002, "Financial"));

// {"code":10,"departments":[[1001,"Sales"],[1002,"Financial"]]}
        String text = JSON.toJSONString(company);
        log.info(text);
    }
}

class Company {
    public int code;
    public List<Department> departments = new ArrayList<Department>();
}

class Company2 {
    public int code;

    @JSONField(serialzeFeatures=SerializerFeature.BeanToArray, parseFeatures=Feature.SupportArrayToBean)
    public List<Department> departments = new ArrayList<Department>();
}

@JSONType(serialzeFeatures=SerializerFeature.BeanToArray, parseFeatures=Feature.SupportArrayToBean)
class Department {
    public int id;
    public String name;
    public Department() {}
    public Department(int id, String name) {this.id = id; this.name = name;}
}
