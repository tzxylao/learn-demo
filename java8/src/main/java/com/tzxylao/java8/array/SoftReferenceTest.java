package com.tzxylao.java8.array;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class SoftReferenceTest {
	public static SoftReference<Student> studentSoftRef = new SoftReference<Student>( new Student(1, "张三"));
	public static void main(String[] args) {
		 Runnable r1=()->{
			 while(true){
				 if(studentSoftRef.get()==null){
					 System.out.println("==="+studentSoftRef.get()+"===");
					 break;
				 }else {
					 System.out.println("==="+studentSoftRef.get()+"===");
				}
				 
			 }
		 };
		 Runnable r2=new Runnable() {	 
			@Override
			public void run() {
				 List<String> list=new ArrayList<>();
				 int index=1;
				 while(true){
					list.add((index++)+"");
				 }
			}
		};
		new Thread(r1).start();
		new Thread(r2).start();
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