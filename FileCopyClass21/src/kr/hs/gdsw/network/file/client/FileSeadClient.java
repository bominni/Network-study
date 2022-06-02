package kr.hs.gdsw.network.file.client;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class FileSeadClient {
	
	public static void main(String[] args) throws IOException {
		Socket sc = new Socket("10.80.162.181", 5000);

        OutputStream os = sc.getOutputStream();
        BufferedOutputStream bor = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bor);

        File f1 = new File("D:\\2103¹Ú¼Ò¿µ\\Network\\testimage.png");
        FileInputStream fis = new FileInputStream(f1);

        dos.writeUTF(f1.getName());

        int readsize = 0;
        byte[] bytes = new byte[1024];

        while((readsize = fis.read(bytes)) != -1){
            dos.write(bytes, 0, readsize);
        }
	}

}
