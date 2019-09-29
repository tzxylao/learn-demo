package com.tzxylao.java8.file;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

/**
 * @author laoliangliang
 * @date 2019/9/11 15:54
 */
public class DirectByteBufferLearn {

    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10);
        FileChannel readChannel = FileChannel.open(new File("E:\\备份\\temp\\1.txt").toPath(), StandardOpenOption.READ);
        FileChannel writeChannel = FileChannel.open(new File("E:\\备份\\temp\\2.txt").toPath(),
                StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
        int read;
        while ((read = readChannel.read(byteBuffer)) > 0) {
            //buffer从读切换到写
            byteBuffer.flip();
//            System.out.println(read + "--" + StandardCharsets.ISO_8859_1.decode(byteBuffer));
            writeChannel.write(byteBuffer);
            // 写完之后清空缓冲区,否则read=0一直死循环
            byteBuffer.clear();
        }
        writeChannel.close();
        readChannel.close();

//        Path copy_from = Paths.get("E:\\备份\\temp", "1.txt");
//        Path copy_to = Paths.get("E:\\备份\\temp\\2.txt");
//        int bufferSizeKB = 4;
//        int bufferSize = bufferSizeKB * 1024;
//        System.out.println("使用 FileChannel 和 ByteBuffer 类复制文件 ...");
//        try (FileChannel fileChannel_from = (FileChannel.open(copy_from,
//                EnumSet.of(StandardOpenOption.READ)));
//             FileChannel fileChannel_to = (FileChannel
//                     .open(copy_to, EnumSet.of(StandardOpenOption.CREATE_NEW,
//                             StandardOpenOption.WRITE)))) {
//            ByteBuffer bytebuffer = ByteBuffer.allocateDirect(bufferSize);
//            int bytesCount;
//            while ((bytesCount = fileChannel_from.read(bytebuffer)) > 0) {
//                bytebuffer.flip();
//                fileChannel_to.write(bytebuffer);
//                bytebuffer.clear();
//            }
//            System.out.println("使用 FileChannel 和 ByteBuffer 类复制文件成功");
//        } catch (IOException ex) {
//            System.err.println(ex);
//        }
    }
}
