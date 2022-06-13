package kr.hs.dgsw.network.test01.n2101.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

public class FileCheck {
	public File currentdirectory;
    public FileCheck(String dir) {
    	currentdirectory = new File(dir);
    }

    public ArrayList<String> fileList() {
        ArrayList<String> files = new ArrayList<>();

        File[] listFiles = this.currentdirectory.listFiles();
        if(listFiles != null && listFiles.length != 0) {
            for(File file : listFiles) {
                String name = file.getName();
                if(!name.substring(0, 1).equals(".")) {
                    files.add(String.format("%-15s %8s", file.getName(), file.isFile() ? this.Byte((int) file.length()): "<DIR>"));
                }

            }
        }
        return files;
    }

    public int FileDownload(InputStream inputStream) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        DataInputStream dis = new DataInputStream(bufferedInputStream);
        try {
            Random random = new Random();
            String oldName = this.currentdirectory.getAbsolutePath() + '\\' + random.nextInt() + ".downloading";
            String currentName = this.currentdirectory.getAbsolutePath() + '\\' + dis.readUTF();
            
            long FileSize = dis.readLong();
            FileOutputStream os = new FileOutputStream(oldName);
            int readSize = 0;
            int fileSize = 0;
            byte[] bytes = new byte[8192];
            while (true) {
                readSize = dis.read(bytes);
                os.write(bytes, 0 , readSize);
                fileSize += readSize;
                if(fileSize == FileSize) {
                    break;
                }
            }
            
            os.flush();
            os.close();
            
            File file = new File(currentName);
            if(file.exists()) {
                file.delete();
            }
            new File(oldName).renameTo(new File(currentName));
            

            
            return fileSize;
        }
        catch (IOException exception) {
            System.out.println(exception.getMessage());
            return -1;
        }
    }

    public int uploadFile(OutputStream outputStream, String fileName) {
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        DataOutputStream dos = new DataOutputStream(bos);
        File fl = new File(this.currentdirectory.getAbsoluteFile() + "/" + fileName);
        try {
            FileInputStream fis = new FileInputStream(fl);
            dos.writeUTF(fl.getName());
            dos.writeLong(fl.length());
            int readSize = 0;
            int fileSize = 0;
            byte[] bytes = new byte[8192];
            while (true) {
                readSize = fis.read(bytes);
                if(readSize == -1) {
                    dos.flush();
                    break;
                }
                dos.write(bytes, 0, readSize);
                fileSize += readSize;
            }
            fis.close();
            return fileSize;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    
    public boolean wheterFile(String fileName) { 
        return new File(this.currentdirectory.getAbsolutePath() + '/' + fileName).exists();
    }
    
    public String Byte(int length) {
        int sub;
        String unit;
        if(length < 1 << 10) {
            sub = 1;
            unit = " B";
        }
        else if(length < 1 << 20) {
            sub = 1 << 10;
            unit = "KB";

        }
        else if(length < 1 << 30) {
            sub = 1 << 20;
            unit = "MB";
        }
        else {
            sub = 1 << 30;
            unit = "GB";
        }
        return String.format("%.1f%s", (float)length / sub, unit);
    }
}
