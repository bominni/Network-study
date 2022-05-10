package kr.hs.dgsw.nework.classs21;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {
	
	public static void main(String[] args) {
		try {
			Socket sc = new Socket("10.80.161.125", 5000);
			
			OutputStream os = sc.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			
			pw.println("김민지 바보!");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
