package com.tzxylao.java8.regex;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author laoliangliang
 * @date 2019/9/27 10:07
 */
public class RegexPractice {
    private static String s2 = "{\"body\":{\"page_index\":\"1\",\"page_size\":10}," +
            "\"header\":{\"request_seq\":\"sn2019091905415046800000000000000010529\"," +
            "\"sign\":\"c76d2b6dca2178f69f003c71dccf72cf\",\"tra_code\":\"000058\"}}";
    public static final String DATE_STRING = "2017-04-25";
    public static final String P_COMM = "(\\d{4})-((\\d{2})-(\\d{2}))";
    private static Pattern p1 = Pattern.compile("(\\d)([a-z]*)");
    private static Pattern p2 = Pattern.compile("\"sign\":\"(?<sign>\\w+)\"");

    public static void main(String[] args) {
//        p1Test();
//        System.out.println("--------------");
//        p2Test();
//        System.out.println("--------------");
//        p2Test2();
//        System.out.println("--------------");
//        p3Test();
        System.out.println("--------------");
        try {
            p4Test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 捕获反向引用
     */
    public static void p1Test() {
        Matcher matcher = p1.matcher("3abc");
        while (matcher.find()) {
            String group = matcher.group();
            System.out.println("group: " + group);
            System.out.println(matcher.replaceAll("$2$2"));
        }
    }

    /**
     * 正则切割
     */
    public static void p2Test() {
        String[] split = p2.split(s2);
        for (String s : split) {
            System.out.println(s);
        }
    }

    public static void p2Test2() {
        Matcher matcher = p2.matcher(s2);
        matcher.find();
        System.out.println(matcher.group(0));
        System.out.println(matcher.group(1));
        System.out.println(matcher.group("sign"));
    }

    public static void p3Test() {
        Pattern pattern = Pattern.compile(P_COMM);
        Matcher matcher = pattern.matcher(DATE_STRING);
        System.out.println(matcher.find());
        System.out.printf("\nmatcher.group(0) value:%s", matcher.group(0));
        System.out.printf("\nmatcher.group(1) value:%s", matcher.group(1));
        System.out.printf("\nmatcher.group(2) value:%s", matcher.group(2));
        System.out.printf("\nmatcher.group(3) value:%s", matcher.group(3));
        System.out.printf("\nmatcher.group(4) value:%s", matcher.group(4));
        System.out.println(matcher.find());
    }


    private static Pattern p4 = Pattern.compile("<img src=\"(?<img>[\\w/._-]+)\"");

    /**
     * 下载网页中所有图片到本地
     */
    public static void p4Test() throws IOException {
        String url = "https://www.ivsky.com/";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            int count = 0;
            String result = response.body().string();
            Matcher matcher = p4.matcher(result);
            while (matcher.find()) {
                String img = matcher.group("img");
                String address = "http:" + img;
                String name = address.substring(address.lastIndexOf("/"));
                downloadPicture(address, "E:\\备份\\pic\\" + name);
                System.out.println(address);
                count++;
            }
            System.out.println("over count:" + count);
        } else {
            System.out.println("失败");
        }
    }


    //链接url下载图片
    private static void downloadPicture(String urlList, String path) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
