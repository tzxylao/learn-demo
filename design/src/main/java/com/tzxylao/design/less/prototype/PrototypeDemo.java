package com.tzxylao.design.less.prototype;

import lombok.extern.slf4j.Slf4j;

/**
 * @author laoliangliang
 * @date 2019/4/2 9:43
 */
@Slf4j
public class PrototypeDemo {

    public static void main(String[] args) {
        ProtoObject protoObject = new ProtoObject("tom", 12);
        protoObject.setProtoTarget(new ProtoTarget("uuuuuu"));
        ProtoObject cloneObj = null;
        try {
            cloneObj = (ProtoObject) protoObject.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        log.info("protoObject:{}",protoObject);
        log.info("protoObject name hashcode:{}",protoObject.name.hashCode());
        log.info("protoObject age hashcode:{}",protoObject.age.hashCode());
        log.info("protoObject protoTarget hashcode:{}",protoObject.getProtoTarget().hashCode());


        log.info("cloneObj:{}",cloneObj);
        log.info("cloneObj name :{}",cloneObj.getProtoTarget().getName());
        log.info("cloneObj name hashcode:{}",cloneObj.name.hashCode());
        log.info("cloneObj age hashcode:{}",cloneObj.age.hashCode());
        log.info("cloneObj protoTarget hashcode:{}",cloneObj.getProtoTarget().hashCode());

        protoObject.name="jim";
        protoObject.age=20;
        protoObject.getProtoTarget().setName("oooooooooo");
        log.info(cloneObj.name);
        log.info(cloneObj.age+"");
        log.info(protoObject.getProtoTarget().hashCode()+"");
        log.info(cloneObj.getProtoTarget().hashCode()+"");
        log.info(cloneObj.getProtoTarget().getName());
    }
}
