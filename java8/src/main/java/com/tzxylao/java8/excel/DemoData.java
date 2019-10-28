package com.tzxylao.java8.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author laoliangliang
 * @date 2019/10/23 18:04
 */
@Data
@AllArgsConstructor
public class DemoData {

    @ExcelProperty(index = 0)
    private String id;

    @ExcelProperty(index = 1)
    private String name;
}
