package kr.hs.dgsw.network.class21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	
	 public static void main(String[] args) {
	        try {
	            ServerSocket ss = new ServerSocket(5000);

	            Socket sc = ss.accept();

	            Thread it = new InputThread(sc);
	            Thread ot = new OutputThread(sc);

	            it.start();
	            ot.start();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }

}
