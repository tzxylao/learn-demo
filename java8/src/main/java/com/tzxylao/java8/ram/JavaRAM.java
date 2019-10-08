package com.tzxylao.java8.ram;

import com.alibaba.fastjson.JSON;
import org.apache.lucene.util.RamUsageEstimator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * java对象占用内存计算
 * @author laoliangliang
 * @date 2019/8/2 9:49
 */
public class JavaRAM {


    public static void main(String[] args) throws UnsupportedEncodingException {
//        test1();
        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int j = 0; j < 3; j++) {
            list.add(j + "");
        }
        for (int j = 0; j < 3; j++) {
            list.add(j + "");
        }
        list.forEach(key -> map.merge(key,Integer.valueOf(key),Integer::sum));
        System.out.println(JSON.toJSONString(map));

//        Map<String, BigDecimal> map = new HashMap<>();
//        for (int i = 0; i < 3; i++) {
//            map.put("RJ2019011470498051242", new BigDecimal("10000"));
//        }
//        System.out.println(RamUsageEstimator.shallowSizeOf(map));
//        System.out.println(RamUsageEstimator.humanSizeOf(map));
    }

    private static void test1() throws UnsupportedEncodingException {
        List<Integer> i = new ArrayList<>(5);
        i.add(1);
        i.add(2);
        i.add(3);

        System.out.println(RamUsageEstimator.shallowSizeOf(i));
        System.out.println(RamUsageEstimator.humanSizeOf(i));
        String strA = "abc";
        System.out.println(RamUsageEstimator.humanSizeOf(strA));
        List<Double> i2 = new ArrayList<>(5);
        i2.add(1.0);
        i2.add(2.0);
        i2.add(3.0);
        System.out.println(RamUsageEstimator.humanSizeOf(i2));

        System.out.println("-----------");
        int a = 1;
        double b = 1.0;
        byte c = 1;
        boolean d = false;
        System.out.println(RamUsageEstimator.humanSizeOf(a));
        System.out.println(RamUsageEstimator.humanSizeOf(b));
        System.out.println(RamUsageEstimator.humanSizeOf(c));
        System.out.println(RamUsageEstimator.humanSizeOf(d));

        String strB = "啊个啊爱范德萨啊啊发aaa";
        String encode = URLEncoder.encode(strB, "utf-8");
        System.out.println(RamUsageEstimator.humanSizeOf(strB));
        System.out.println(RamUsageEstimator.humanSizeOf(encode));

        System.out.println("----------------");

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 300; j++) {
            sb.append("啊");
        }
        System.out.println(RamUsageEstimator.humanSizeOf(sb.toString()));
        System.out.println(RamUsageEstimator.shallowSizeOf(sb.toString()));
        System.out.println(RamUsageEstimator.sizeOf(sb.toString()));
        String s = URLEncoder.encode(sb.toString(), "utf-8");
        System.out.println(RamUsageEstimator.humanSizeOf(s));
        System.out.println(RamUsageEstimator.shallowSizeOf(s));
        System.out.println(RamUsageEstimator.sizeOf(s));
    }
}
