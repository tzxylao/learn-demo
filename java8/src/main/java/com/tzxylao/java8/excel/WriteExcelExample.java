package com.tzxylao.java8.excel;

import com.alibaba.excel.EasyExcel;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * easyexcel写excel
 * @author laoliangliang
 * @date 2019/10/23 18:01
 */
public class WriteExcelExample {

    public static void main(String[] args) {
        String fileName = "E:\\test\\1.xlsx";
        EasyExcel.write(fileName,DemoData.class).sheet("模板").doWrite(getList());
    }

    private static List getList() {
        ArrayList<Object> arrayList = Lists.newArrayList();
        arrayList.add(new DemoData("1", "tom1"));
        arrayList.add(new DemoData("2", "tom2"));
        arrayList.add(new DemoData("3", "tom3"));
        return arrayList;
    }
}
