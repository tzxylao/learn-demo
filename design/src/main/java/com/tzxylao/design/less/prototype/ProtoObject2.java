package com.tzxylao.design.less.prototype;

/**
 * @author laoliangliang
 * @date 2019/4/2 9:43
 */
public class ProtoObject2 implements Cloneable {

    public String name;

    public Integer age;

    private ProtoTarget protoTarget;

    public void setProtoTarget(ProtoTarget protoTarget) {
        this.protoTarget = protoTarget;
    }

    public ProtoTarget getProtoTarget() {
        return protoTarget;
    }

    public ProtoObject2(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
