package com.tzxylao.java8.array;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author laoliangliang
 * @date 2019/10/17 11:15
 */
public class ArrayLearn {

    private static SoftReference<Student> soft = new SoftReference<>(new Student(1,"tom"));;

    public static void main(String[] args) {
//        int[] arr = new int['z'];
//        arr['a']++;
//        arr['A']++;
//        arr['A']++;
//        System.out.println(arr['a']);
//        System.out.println(arr['A']);
//        System.out.println("-------------");
//        int a = 1,b,c;
//        a=b=a++;
//        c=a+++b;
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(c);
//        System.out.println("------------");
        new Thread(()->{
            while (true) {
                if(soft.get()==null){
                    System.out.println("==="+soft.get()+"===");
                    break;
                }else {
                    System.out.println("==="+soft.get()+"===");
                }
            }
        }).start();

        new Thread(()->{
            List<String> list=new ArrayList<>();
            int index=1;
            while(true){
                list.add((index++)+"");
            }
        }).start();


    }

    public static class Student
    {  int id;
        String name;
        public Student(Integer id, String name)
        {
            this.id = id; this.name = name;
        }
        @Override public String toString()
        {
            return "[id=" + id + ",name=" + name + "]";
        }
    }
}
