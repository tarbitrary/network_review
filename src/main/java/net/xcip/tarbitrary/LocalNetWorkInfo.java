package net.xcip.tarbitrary;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * <p></p>
 *
 * @author tarbitrary
 * @version V1.0
 * @created_time 2019-12-06 09:31
 * @module 现货延期模块
 **/
public class LocalNetWorkInfo {
    public static void main(String[] args) throws SocketException {
        getLocalNetWorkInfo();
    }

    public static void  getLocalNetWorkInfo() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        if (null == networkInterfaces) {
            System.out.println("nothing");
        }

        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            if (null == inetAddresses) {
                System.out.printf("int address is null");
            }

            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                System.out.println("type " + (inetAddress instanceof Inet6Address ? "v6" : "v4") + "address"  + inetAddress.getHostAddress() + "hostname" + inetAddress.getHostName() + "address" + inetAddress.getAddress());
            }
        }



    }
}
