package com.tzxylao.java8.socket;

import lombok.extern.slf4j.Slf4j;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author laoliangliang
 * @date 2019/10/18 17:50
 */
@Slf4j
public class MyServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(83);
        try {
            while(true) {
                Socket socket = null;
                socket = serverSocket.accept();
                //这边获得socket连接后开启一个线程监听处理数据
                SocketServerThread socketServerThread = new SocketServerThread(socket);
                new Thread(socketServerThread).start();
            }
        } catch(Exception e) {
            log.error("Socket accept failed. Exception:{}", e.getMessage());
        } finally {
            if(serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
