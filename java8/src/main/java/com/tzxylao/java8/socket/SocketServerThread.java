package com.tzxylao.java8.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Slf4j
class SocketServerThread implements Runnable {

    private Socket socket;

    public SocketServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
            Integer sourcePort = socket.getPort();
            int maxLen = 2048;
            byte[] contextBytes = new byte[maxLen];
            int realLen;
            StringBuffer message = new StringBuffer();
            BIORead:
            while (true) {
                try {
                    while ((realLen = in.read(contextBytes, 0, maxLen)) != -1) {
                        message.append(new String(contextBytes, 0, realLen));
                        /*
                         * 我们假设读取到“over”关键字，
                         * 表示客户端的所有信息在经过若干次传送后，完成
                         * */
                        if (message.indexOf("over") != -1) {
                            break BIORead;
                        }
                    }

                    //下面打印信息
                    log.info("服务器(收到来自于端口：" + sourcePort + "的信息：" + message);
                    //下面开始发送信息
                    out.write("回发响应信息！".getBytes());
                    //关闭
                    out.close();
                    in.close();
                    this.socket.close();
                } catch (Exception e) {
                    log.error("Socket read failed. Exception:{}", e.getMessage());
                }
            }
        }catch (Exception e) {
            log.error("Socket read failed. Exception:{}", e.getMessage());
        }
    }
}