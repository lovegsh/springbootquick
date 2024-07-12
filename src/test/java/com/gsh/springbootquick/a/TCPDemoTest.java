package com.gsh.springbootquick.a;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Create By GSH on .
 */
class TCPDemoTest {

    @Test
    void client() {
        Socket socket = null;
        OutputStream os = null;
        ByteArrayOutputStream bao = null;
        InputStream inputStream = null;
        try {
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet, 8899);

            os = socket.getOutputStream();
            os.write("你好！！我是客户端".getBytes());

            socket.shutdownOutput();


            inputStream = socket.getInputStream();
            bao = new ByteArrayOutputStream();
            byte[] bytes = new byte[20];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                bao.write(bytes, 0, len);
            }
            System.out.println(bao.toString());



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bao.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Test
    void server() throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream bao = null;
        OutputStream outputStream = null;
        try {
            serverSocket = new ServerSocket(8899);
            socket = serverSocket.accept();

            is = socket.getInputStream();

            //乱码不推荐
            //        byte[] bytes = new byte[20];
            //        int len;
            //        while ((len = is.read(bytes)) != -1) {
            //            String s = new String(bytes, 0, len);
            //            System.out.println(s);
            //        }

            bao = new ByteArrayOutputStream();
            byte[] bytes = new byte[20];
            int len;
            while ((len = is.read(bytes)) != -1) {
                bao.write(bytes, 0, len);
            }
            System.out.println(bao.toString());

            System.out.println("收到了来自于："+socket.getInetAddress().getHostName()+"的数据");

            outputStream = socket.getOutputStream();
            outputStream.write("收到拉,很棒！".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.close();
            bao.close();
            is.close();
            socket.close();
            serverSocket.close();
        }

    }
}