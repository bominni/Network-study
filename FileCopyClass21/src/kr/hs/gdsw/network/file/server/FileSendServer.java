package kr.hs.gdsw.network.file.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileSendServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(5000);
		Socket sc = ss.accept();
		
		InputStream is = sc.getInputStream();
		BufferedInputStream bir = new BufferedInputStream(is);
		DataInputStream dis = new DataInputStream(bir);
		
		String filename = dis.readUTF();
		FileOutputStream fos = new FileOutputStream("C:\\Users\\DGSW\\" + filename);
		
		int readsize;
		byte[] bytes = new byte[1024];
		
		while((readsize = dis.read(bytes)) != -1) {
			fos.write(bytes, 0, readsize);
		}
	}

}
