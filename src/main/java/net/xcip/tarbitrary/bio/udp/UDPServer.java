package net.xcip.tarbitrary.bio.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * <p></p>
 *
 * @author tarbitrary
 * @version V1.0
 * @created_time 2019-12-24 17:36
 * @module 现货延期模块
 **/
public class UDPServer {

    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(9999);
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, 1024);
        ds.receive(dp);

        int length = dp.getLength();
        byte[] data = dp.getData();
        String result = new String(data, 0, length, "UTF-8");
        System.out.println("receive result," + result );

        dp.setLength(0);
        String s = "echo udp," + result;
        dp.setData(s.getBytes("UTF-8"));
        ds.send(dp);

    }
}

