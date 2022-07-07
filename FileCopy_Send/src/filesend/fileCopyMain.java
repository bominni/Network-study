package filesend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class fileCopyMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File fl = new File("D:\\testimage.png");
		FileInputStream fis = new FileInputStream(fl);
		
		FileOutputStream fos = new FileOutputStream("D:\\temp\\testimage.png");
		
		byte[] bytes = new byte[1024];
		int readbit = 0;
		while((readbit = fis.read(bytes)) != -1)
			fos.write(bytes,0,readbit);
	}

}
