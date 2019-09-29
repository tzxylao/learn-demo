package com.tzxylao.java8.guava;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * @author laoliangliang
 * @date 2019/9/5 16:14
 */
public class FileLearn {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\cygwin64\\home\\workspace3\\learn-demo\\java8\\src\\main\\java\\com\\tzxylao\\java8" +
                "\\guava\\FileLearn.java");
        List<String> lines = Files.readLines(file, Charsets.UTF_8);
        System.out.println(lines);
//        Files.copy(from,to);
        URL prop = Resources.getResource("application.properties");
        URLConnection urlConnection = prop.openConnection();
//        urlConnection.connect();
        InputStream inputStream = urlConnection.getInputStream();
        System.out.println(readStream(inputStream));
    }

    public static String readStream(InputStream in) throws IOException {
        // 实例化输入流，并获取网页代码
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String s; // 依次循环，至到读的值为空
        StringBuilder sb = new StringBuilder();
        while ((s = reader.readLine()) != null) {
            sb.append(s);
        }
        reader.close();

        return new String(sb.toString().getBytes(),"UTF-8");
    }
}
