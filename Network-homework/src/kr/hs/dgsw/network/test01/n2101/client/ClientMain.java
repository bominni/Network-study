package kr.hs.dgsw.network.test01.n2101.client;

import java.io.IOException;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9999);

        Thread outputThread = new OutputThread(socket);

        try {
            outputThread.join();
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
