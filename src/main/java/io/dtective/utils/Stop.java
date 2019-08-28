package io.dtective.utils;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Stop {

    public static void main(String[] args) throws Exception {
        final int port = 9000;

        Socket s = new Socket(InetAddress.getByName("127.0.0.1"), port);
        OutputStream out = s.getOutputStream();
        System.out.println("*** sending jetty stop request");
        out.write(("\r\n").getBytes());
        out.flush();
        s.close();
    }

}
