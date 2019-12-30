package net.xcip.tarbitrary.bio.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * <p></p>
 *
 * @author tarbitrary
 * @version V1.0
 * @created_time 2019-12-24 17:45
 * @module 现货延期模块
 **/
public class UDPClient {

    public static void main(String[] args) throws IOException {
        DatagramSocket ds;
        ds = new DatagramSocket();
        String sendInfo = "hello , 涂强";
        byte[] bytes = sendInfo.getBytes("UTF-8");
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        dp.setSocketAddress(new InetSocketAddress("127.0.0.1", 9999));
        ds.send(dp);

        byte[] buf = new byte[1024];
        DatagramPacket receive = new DatagramPacket(buf, 1024);

        ds.receive(receive);

        System.out.println("receive, " + new String(receive.getData(), 0, receive.getLength()));



    }
}
