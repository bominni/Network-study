package kr.hs.dgsw.network.test01.n2101.server;

import java.net.ServerSocket;

public class ServerMain {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true) {
                new InputThread(serverSocket.accept());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}