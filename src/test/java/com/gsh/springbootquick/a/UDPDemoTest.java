package com.gsh.springbootquick.a;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Create By GSH on .
 */
public class UDPDemoTest {

    @Test
    void sender() throws IOException {

        DatagramSocket socket = new DatagramSocket();
        InetAddress inet = InetAddress.getLocalHost();
        byte[] bytes = "UDP发送拉阿斯利康的".getBytes();
        DatagramPacket dp = new DatagramPacket(bytes, 0, bytes.length, inet, 8099);

        socket.send(dp);

        socket.close();
    }

    @Test
    void receiver() throws IOException {
        DatagramSocket socket = new DatagramSocket(8099);
        byte[] bytes = new byte[100];
        DatagramPacket dp = new DatagramPacket(bytes, 0, bytes.length);
        socket.receive(dp);

        System.out.println(new String(bytes));

        socket.close();
    }
}
