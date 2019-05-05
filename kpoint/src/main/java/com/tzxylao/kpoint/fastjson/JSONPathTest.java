package com.tzxylao.kpoint.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import java.util.*;

/**
 * 可用来获得json中的
 * 某个属性是否存在，
 * 有几个属性，
 * 某个属性的值集合，
 * 返回指定下标的集合，
 * 返回范围的集合
 * 通过条件过滤，返回集合
 * 判断是否返回对象修改对象，数组属性添加元素
 * 获取对象中集合属性的值集合
 * 使用keySet抽取对象的属性名，null值属性的名字并不包含在keySet结果中
 *
 * @author laoliangliang
 * @date 2019/5/5 15:28
 */
@Slf4j
public class JSONPathTest {
    public static void main(String[] args) {
        Entity entity = new Entity(123, new Object());

        Assert.assertSame(entity.getValue(), JSONPath.eval(entity, "$.value"));
        Assert.assertTrue(JSONPath.contains(entity, "$.value"));
        Assert.assertTrue(JSONPath.containsValue(entity, "$.id", 123));
        Assert.assertTrue(JSONPath.containsValue(entity, "$.value", entity.getValue()));
        Assert.assertEquals(2, JSONPath.size(entity, "$"));
        Assert.assertEquals(0, JSONPath.size(new Object(), "$"));

        log.info("--------------------------------------------");

        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity("wenshao"));
        entities.add(new Entity("ljw2083"));

        List<String> names = (List<String>) JSONPath.eval(entities, "$.name"); // 返回enties的所有名称
        Assert.assertSame(entities.get(0).getName(), names.get(0));
        Assert.assertSame(entities.get(1).getName(), names.get(1));
        log.info(JSON.toJSONString(names));

        log.info("--------------------------------------------");


        entities = new ArrayList<Entity>();
        entities.add(new Entity("wenshao"));
        entities.add(new Entity("ljw2083"));
        entities.add(new Entity("Yako"));

        List<Entity> result = (List<Entity>) JSONPath.eval(entities, "[1,2]"); // 返回下标为1和2的元素
        Assert.assertEquals(2, result.size());
        Assert.assertSame(entities.get(1), result.get(0));
        Assert.assertSame(entities.get(2), result.get(1));
        log.info(JSON.toJSONString(result));

        log.info("--------------------------------------------");
        entities = new ArrayList<Entity>();
        entities.add(new Entity("wenshao"));
        entities.add(new Entity("ljw2083"));
        entities.add(new Entity("Yako"));

        result = (List<Entity>) JSONPath.eval(entities, "[0:2]"); // 返回下标从0到2的元素
        Assert.assertEquals(3, result.size());
        Assert.assertSame(entities.get(0), result.get(0));
        Assert.assertSame(entities.get(1), result.get(1));
        Assert.assertSame(entities.get(2), result.get(2));
        log.info(JSON.toJSONString(result));


        log.info("--------------------------------------------");
        entities = new ArrayList<Entity>();
        entities.add(new Entity(1001, "ljw2083"));
        entities.add(new Entity(1002, "wenshao"));
        entities.add(new Entity(1003, "yakolee"));
        entities.add(new Entity(1004, null));

        List<Object> result2 = (List<Object>) JSONPath.eval(entities, "[id in (1001)]");
        Assert.assertEquals(1, result2.size());
        Assert.assertSame(entities.get(0), result2.get(0));
        log.info(JSON.toJSONString(result2));

        log.info("--------------------------------------------");
        entity = new Entity(1001, "ljw2083");
        Assert.assertSame(entity, JSONPath.eval(entity, "[id = 1001]"));
        Assert.assertNull(JSONPath.eval(entity, "[id = 1002]"));

        JSONPath.set(entity, "id", 123456); //将id字段修改为123456
        Assert.assertEquals(123456, entity.getId().intValue());

        JSONPath.set(entity, "value", new int[0]); //将value字段赋值为长度为0的数组
        JSONPath.arrayAdd(entity, "value", 1, 2, 3); //将value字段的数组添加元素1,2,3
        log.info(JSON.toJSONString(entity));

        log.info("--------------------------------------------");

        Map root = Collections.singletonMap("company", //
                Collections.singletonMap("departs", //
                        Arrays.asList( //
                                Collections.singletonMap("id",
                                        1001), //
                                Collections.singletonMap("id",
                                        1002), //
                                Collections.singletonMap("id", 1003) //
                        ) //
                ));

        List<Object> ids = (List<Object>) JSONPath.eval(root, "$..id");
        Assert.assertEquals(3, ids.size());
        Assert.assertEquals(1001, ids.get(0));
        Assert.assertEquals(1002, ids.get(1));
        Assert.assertEquals(1003, ids.get(2));
        log.info(JSON.toJSONString(ids));


        log.info("--------------------------------------------");

        Entity e1 = new Entity();
        e1.setId(null);
        e1.setName("hello");
        Map<String, Entity> map = Collections.singletonMap("e", e1);
        Collection<String> result3;

        // id is null, excluded by keySet
        result3 = (Collection<String>) JSONPath.eval(map, "$.e.keySet()");
        Assert.assertEquals(1, result3.size());
        Assert.assertTrue(result3.contains("name"));

        e1.setId(1);
        result3 = (Collection<String>) JSONPath.eval(map, "$.e.keySet()");
        Assert.assertEquals(2, result3.size());
        // included
        Assert.assertTrue(result3.contains("id"));
        Assert.assertTrue(result3.contains("name"));

        // Same result3
        Assert.assertEquals(result3, JSONPath.keySet(map, "$.e"));
        Assert.assertEquals(result3, new JSONPath("$.e").keySet(map));
        log.info(JSON.toJSONString(result3));
    }

    public static class Entity {
        private Integer id;
        private String name;
        private Object value;

        public Entity() {
        }

        public Entity(Integer id, Object value) {
            this.id = id;
            this.value = value;
        }

        public Entity(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Entity(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public Object getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
