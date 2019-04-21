package com.tzxylao.java8.stream;

import org.apache.commons.collections4.MapUtils;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author laoliangliang
 * @since 19/4/18 下午10:04
 */
public class CollectDemo {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("小米", 10, Student.Gender.FEMALE, Student.Grade.FOUR),
                new Student("小比", 10, Student.Gender.FEMALE, Student.Grade.FOUR),
                new Student("小就", 14, Student.Gender.MALE, Student.Grade.ONE),
                new Student("小个", 10, Student.Gender.FEMALE, Student.Grade.FOUR),
                new Student("小爱", 10, Student.Gender.FEMALE, Student.Grade.THREE),
                new Student("小嘎", 5, Student.Gender.FEMALE, Student.Grade.FOUR),
                new Student("小好", 18, Student.Gender.FEMALE, Student.Grade.TWO),
                new Student("小额", 10, Student.Gender.FEMALE, Student.Grade.FOUR),
                new Student("小去", 17, Student.Gender.FEMALE, Student.Grade.FOUR)
                );
        //得到所有学生的年龄列表
        List<Integer> ages = students.stream().map(s -> s.age).collect(Collectors.toList());
        System.out.println("所有学生的年龄："+ages);

        //统计汇总信息
        IntSummaryStatistics collect = students.stream().collect(Collectors.summarizingInt(s -> s.age));
        System.out.println("学生年龄汇总信息："+collect.toString());

        //分块
        Map<Boolean, List<Student>> genders = students.stream().collect(Collectors.partitioningBy(s -> s.gender == Student.Gender.MALE));
        MapUtils.verbosePrint(System.out,"男女学生列表：",genders);

        //分组
        Map<Student.Grade, List<Student>> grades = students.stream().collect(Collectors.groupingBy(s -> s.grade));
        MapUtils.verbosePrint(System.out, "学生班级列表", grades);

        //得到所有班级学生的个数
        Map<Student.Grade, Long> gradesCount = students.stream().collect(Collectors.groupingBy(s -> s.grade, Collectors.counting()));
        MapUtils.verbosePrint(System.out, "班级学生人数列表", gradesCount);
    }

    private static class Student {
        enum Gender{
            MALE,FEMALE
        }
        enum  Grade{
            ONE,TWO,THREE,FOUR
        }
        private String name;
        private int age;
        private Gender gender;
        private Grade grade;

        public Student(String name, int age, Gender gender, Grade grade) {
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", gender=" + gender +
                    ", grade=" + grade +
                    '}';
        }
    }
}
