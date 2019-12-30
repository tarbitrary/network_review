package net.xcip.tarbitrary.bio.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * <p></p>
 *
 * @author tarbitrary
 * @version V1.0
 * @created_time 2019-12-24 13:48
 * @module 现货延期模块
 **/
public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8899);
        while (true) {
            Socket accept = serverSocket.accept();
            InetAddress inetAddress = accept.getInetAddress();
            SocketAddress remoteSocketAddress = accept.getRemoteSocketAddress();
            System.out.println("receive from client " + inetAddress.getHostAddress() + " *** " + remoteSocketAddress.toString());
            InputStream inputStream = accept.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            String result = baos.toString("UTF-8");

            System.out.println("receive result" + result);
            OutputStream outputStream = accept.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write("echo " + result);
            bufferedWriter.flush();
            accept.shutdownOutput();

            accept.close();
        }
        //serverSocket.close();


    }
}
