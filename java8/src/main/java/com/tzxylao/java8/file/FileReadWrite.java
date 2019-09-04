package com.tzxylao.java8.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author laoliangliang
 * @date 2019/8/26 14:03
 */
public class FileReadWrite {
    private final static String path = "D:\\cygwin64\\home\\workspace3\\learn-demo\\java8\\src\\main\\java\\com" +
            "\\tzxylao" +
            "\\java8\\file" +
            "\\FileReadWrite.java";
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
//        readNIO();
        readAIO();
    }

    public static void readNIO() throws IOException {

        FileChannel channel = new FileInputStream(new File(path)).getChannel();
        int capacity = 100;
        ByteBuffer bf = ByteBuffer.allocate(capacity);
        System.out.println("限制是： " + bf.limit() + "容量是： " + bf.capacity() + "位置是: " + bf.position());
        int length = -1;
        while ((length = channel.read(bf)) != -1) {
            bf.clear();
            byte[] array = bf.array();
            System.out.write(array, 0, length);
            System.out.println();
            System.out.println("限制是： " + bf.limit() + "容量是： " + bf.capacity() + "位置是: " + bf.position());
        }
        channel.close();
    }

    public static void readAIO() throws IOException, ExecutionException, InterruptedException {
        Path path = Paths.get(FileReadWrite.path);
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ,
                StandardOpenOption.WRITE);

        ByteBuffer buffer = ByteBuffer.allocate(100_000);
        Future<Integer> result = channel.read(buffer, 0);

        int count = 0;
        while (!result.isDone()) {
            System.out.println(++count);
        }
        Integer bytesRead = result.get();
        System.out.println("Bytes read [" + bytesRead + "]");
        System.out.write(buffer.array());

        CompletionHandler<Integer, Object> handler = new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println("Attachment: " + attachment + " " + result
                        + " bytes written");
                System.out.println("CompletionHandler Thread ID: "
                        + Thread.currentThread().getId());
            }

            @Override
            public void failed(Throwable e, Object attachment) {
                System.err.println("Attachment: " + attachment + " failed with:");
                e.printStackTrace();
            }
        };
        System.out.println("Main Thread ID：" + Thread.currentThread().getId());
        channel.write(ByteBuffer.wrap("Sample".getBytes()), 0, "FileReadWrite", handler);
//        channel.write(ByteBuffer.wrap("Box".getBytes()), 0, "Second Write", handler);
    }
}
