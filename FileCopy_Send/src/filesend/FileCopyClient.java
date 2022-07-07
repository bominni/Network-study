package filesend;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class FileCopyClient {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Socket sc = new Socket("127.0.0.1",5000);
		OutputStream os = sc.getOutputStream();
		BufferedOutputStream bor = new BufferedOutputStream(os);
		DataOutputStream dos = new DataOutputStream(bor);
		
		
		
		File fl = new File("D:\\testimage.png");
		FileInputStream fis = new FileInputStream(fl);
		
		byte[] bytes = new byte[1024];
		int readbit = 0;
		
		dos.writeUTF(fl.getName());
		
		while((readbit = fis.read(bytes)) != -1)
			dos.write(bytes,0,readbit);
			//fos.write(bytes,0,readbit);
			// bytes 에 저장된 데이터 전송
		
	}

}