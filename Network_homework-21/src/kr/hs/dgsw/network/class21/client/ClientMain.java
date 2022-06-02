package kr.hs.dgsw.network.class21.client;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {
	
	public static void main(String[] args) {
		Socket sc;
		try {
			sc = new Socket("10.80.162.206", 5000);
			OutputStream os = sc.getOutputStream();
			BufferedOutputStream bor = new BufferedOutputStream(os);
			DataOutputStream dos = new DataOutputStream(bor);
			
			File f1 = new File("C:\\Users\\DGSW\\Downloads\\[제출] 유스케이스 명세서.hwp");
			FileInputStream fis = new FileInputStream(f1);
			
			dos.writeUTF(f1.getName());
			
			int readsize;
			byte[] bytes = new byte[1024];
			
			while((readsize = fis.read(bytes)) != -1) {
				dos.write(bytes, 0, readsize);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
