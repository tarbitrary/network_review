package net.xcip.tarbitrary.bio.tcp;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * <p></p>
 *
 * @author tarbitrary
 * @version V1.0
 * @created_time 2019-12-24 14:02
 * @module 现货延期模块
 **/
public class TCPClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();

        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8899);
//        socket.bind(socketAddress);

        socket.connect(socketAddress);

        byte[] buffer = new byte[1012];

        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        bufferedWriter.write("你好，tarbitrary");
        bufferedWriter.flush();
        socket.shutdownOutput();
//        outputStream.close();
//        outputStream.close();


        InputStream inputStream = socket.getInputStream();
        int len = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        while ((len = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }

        String s = baos.toString("UTF-8");

        System.out.println("receive from server , " + s);

        socket.close();


    }
}
