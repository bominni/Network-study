package kr.hs.dgsw.network.test01.n2101.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	
	public static void main(String[] args) {
        try {
			ServerSocket ss = new ServerSocket(5000);
			while(true) {
	            Socket sc = ss.accept();

	            Thread it = new InputThread(sc);

	            it.start();
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
