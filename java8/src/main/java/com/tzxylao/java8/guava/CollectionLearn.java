package com.tzxylao.java8.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.*;

import java.util.List;
import java.util.Map;

/**
 * @author laoliangliang
 * @date 2019/9/5 14:50
 */
public class CollectionLearn {

    public static void main(String[] args) {
        //普通
        List<String> list = Lists.newArrayList("1", "2", "3");
        String join = Joiner.on("-").join(list);
        System.out.println(join);

        Map<Object, Object> map = Maps.newHashMap();

        //不变Collection
        ImmutableList<String> iList = ImmutableList.of("1", "2", "3");
        ImmutableMap<String, String> immutableMap = ImmutableMap.of("key1", "value1", "key2", "value2");
        System.out.println(Joiner.on(",").withKeyValueSeparator("=").join(immutableMap));


        //key-value  key可以重复
        ArrayListMultimap<String, Integer> arrayListMultimap = ArrayListMultimap.create();
        arrayListMultimap.put("a", 1);
        arrayListMultimap.put("a", 2);
        System.out.println(arrayListMultimap.get("a"));

        //无序+可重复   count()方法获取单词的次数  增强了可读性+操作简单
        HashMultiset<Object> set = HashMultiset.create();
        set.add("aa");
        set.add("aa");
        set.add("bb");
        System.out.println(set.count("aa"));
        System.out.println(set);

        //双向Map(Bidirectional Map) 键与值都不能重复
        HashBiMap<Object, Object> biMap = HashBiMap.create();
        biMap.put("aa", 1);
        biMap.put("aa", 2);
//        biMap.put("bb", 2);

        System.out.println(biMap.get("aa"));
        System.out.println(biMap.get("bb"));

        //双键的Map Map--> Table-->rowKey+columnKey+value  //和sql中的联合主键有点像
        HashBasedTable<Object, Object, Object> base = HashBasedTable.create();
        base.put("1", "id", "123");
        base.put("2", "id", "123");
        base.put("2", "id2", "124");
        base.put("2", "id2", "125");
        System.out.println(base);
    }
}
