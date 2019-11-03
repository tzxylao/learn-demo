package com.tzxylao.java8.download;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author laoliangliang
 * @since 2019-08-10 15:46
 */
public class DownPic {

    private static CountDownLatch latch;

    private static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) {

        String num = "643";
        String prefix = "/21695/";
        Integer code = 983004;
        Integer codeEnd = 983043;
        latch = new CountDownLatch(codeEnd - code + 1);
        ExecutorService pool = Executors.newFixedThreadPool(200);
        for (int i = code; i <= codeEnd; i++) {
            String imagePath = "https://tu.yakamh.com/static/upload/book/" + num + prefix + i + ".jpg";
            pool.submit(new Task(imagePath, num + "/" + i));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("over");
        pool.shutdown();
        pool.shutdownNow();

    }

    static class Task implements Runnable {

        private String imagePath;

        private String code;

        public Task(String imagePath, String code) {
            this.imagePath = imagePath;
            this.code = code;
        }

        @Override
        public void run() {
            boolean flag = true;
            int tryCount = 0;
            while (flag) {
                System.out.println("开始下载 " + imagePath);
                String imgPath = "/Users/laoliangliang/book/" + code + ".jpg";
                System.out.println("imgPath:" + imgPath);
                String result = downloadImg(imagePath, imgPath);
                if (result != null) {
                    flag = false;
                    int i = count.incrementAndGet();
                    System.out.println("完成" + i);
                } else {
                    if (tryCount == 4) {
                        flag = false;
                        tryCount = 0;
                    }
                    tryCount++;
                }
            }
            latch.countDown();
        }
    }


    /**
     * 图片下载
     *
     * @param picUrl  文件资源链接
     * @param imgPath 保存路径
     * @return
     */
    public static String downloadImg(String picUrl, String imgPath) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet get = new HttpGet(picUrl);
            HttpResponse response = httpclient.execute(get);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode != 200) {
                System.out.println("不存在此图片：" + picUrl);
                return imgPath;
            }
            HttpEntity entity = response.getEntity();
            InputStream in = entity.getContent();
            try {
                String tempPath = imgPath.substring(0, imgPath.lastIndexOf("/"));
                File file = new File(tempPath);
                boolean exists = file.exists();
                if (!exists) {
                    file.mkdirs();
                }
                FileOutputStream fout = new FileOutputStream(imgPath);
                int l = -1;
                byte[] tmp = new byte[1024];
                while ((l = in.read(tmp)) != -1) {
                    fout.write(tmp, 0, l);
                }
                fout.flush();
                fout.close();
                return imgPath;
            } finally {
                // 关闭低层流。
                in.close();
                httpclient.close();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            System.out.println("下载图片出错" + picUrl);
        }
        return null;
    }
}
