package kr.hs.dgsw.network.class21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class InputThread extends Thread {
	private static List<InputThread> ClientList = (List<InputThread>) Collections.synchronizedList(new ArrayList<InputThread>());
	Socket sc = null;
	
	public InputThread(Socket sc) {
		this.sc = sc;
		ClientList.add(this);
	}
	
	public void sandMessage(String msg) {
		OutputStream os = null;
		try{
            os = sc.getOutputStream();
        }catch (IOException e){
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(os, true);

        pw.println(msg);

		
	}
	public void run() {
		InputStream is = null;
		try {
			is = sc.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		//String msg = null;
		try {
			while(true) {
				for(InputThread tmpit:ClientList) {
					tmpit.sandMessage(br.readLine());
					// System.out.println(br.readLine());
				}
//				msg = br.readLine();
//				System.out.println(msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
