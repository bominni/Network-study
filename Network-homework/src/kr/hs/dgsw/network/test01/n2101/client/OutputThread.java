package kr.hs.dgsw.network.test01.n2101.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class OutputThread extends Thread{
    Socket socket;
    OutputStream outputStream = null;
    InputStream inputStream = null;
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    Scanner scanner = new Scanner(System.in);
    OutputThread(Socket sc) {
        this.socket = sc;
        try { this.outputStream = this.socket.getOutputStream();}
        catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        this.printWriter = new PrintWriter(outputStream, true);	
        try {
            this.inputStream = this.socket.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
        System.out.println(this.getMessage());

        while (true) {
            String id;
            String pass;
            System.out.print("ID: ");
            id = scanner.nextLine();
            System.out.print("PASS: ");
            pass = scanner.nextLine();
            this.Message("login%" + id + '%' + pass);
            String result = this.getMessage();
            System.out.println(result);
            if(result.equals("** FTP 서버에 접속하였습니다. **")) {
                this.start();
                break;
            }
        }
    }

    public void sendFile(String pathName, String fileName) throws IOException {
        BufferedOutputStream bor = new BufferedOutputStream(this.outputStream);
        DataOutputStream dos = new DataOutputStream(bor);
        File fl = new File(pathName);
        String retVal = this.getMessage();
        if(retVal.equals("파일이 이미 있습니다. 덮어쓰기 하실건가요??(Yes: 덮어쓰기 / No: 업로드 취소):")) {
            System.out.println(retVal);
            retVal = scanner.nextLine();
            this.Message(retVal);
            if(retVal.equals("No")) {
                this.Message("NULL");
                System.out.println("** 파일 업로드에 실패하였습니다. **");
                return;
            }
        }

        System.out.println("** 파일을 업로드 하였습니다. **");
        FileInputStream fis = new FileInputStream(fl);
        dos.writeUTF(fileName);
        dos.writeLong(fl.length());
        int readSize = 0;
        byte[] bytes = new byte[8192];
        while (true) {
            readSize = fis.read(bytes);
            if(readSize == -1) {
                dos.flush();
                break;
            }
            dos.write(bytes, 0, readSize);
        }
        fis.close();
    }
    
    public int receiveFile(String dir) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(this.inputStream);
        DataInputStream dis = new DataInputStream(bufferedInputStream);
        try {
            String fileName = dis.readUTF();
            long receiveFileSize = dis.readLong();
            FileOutputStream fos = new FileOutputStream(dir + '/' + fileName);
            int readSize = 0;
            int fileSize = 0;
            byte[] bytes = new byte[8192];
            while (true) {
                readSize = dis.read(bytes);
                fos.write(bytes, 0, readSize);
                fileSize += readSize;
                if (fileSize == receiveFileSize) {
                    break;
                }
            }
            return fileSize;
        } catch (IOException exception) {
            return -1;
        }
    }
    
    public void run() {
        while (true) {
            String command = scanner.nextLine();
            int slashIdx = command.indexOf("/");
            if(slashIdx == -1) {
                System.out.println("** 다시입력해주세요. **");
            }
            else {
                command = command.substring(slashIdx + 1);
                String[] strings = command.split(" ");
                String ret = "";
                for (String i : strings) {
                    ret += i + '%';
                }
                if (strings[0].equals("upload")) {
                    if (new File(strings[1]).exists()) {
                        this.Message(ret);
                        try {
                            this.sendFile(strings[1],
                                    strings.length == 2 ?
                                            strings[1].split("/")[strings[1].split("/").length - 1]
                                            : strings[strings.length - 1]);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("** 존재하지 않는 파일입니다. 다시입력해주세요. **");
                        this.Message("NULL");
                    }
                } else if (strings[0].equals("download")) {
                    this.Message(ret);
                    int size = this.receiveFile("C:\\Users\\DGSW\\Downloads");
                    if (size == -1) {
                        System.out.println("** 파일 다운로드에 실패하였습니다. **");
                    }
                    System.out.println("** 다운로드 완료했습니다. **");
                } else if (strings[0].equals("exit")) {
                    this.Message(ret);
                    try {
                        this.socket.close();
                        break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    this.Message(ret);
                }

                String str = this.getMessage();
                if(!str.equals("NULL")) {
                    System.out.println(str);
                }
            }
        }
    }
    
    public String getMessage() {
        try {
            String str = bufferedReader.readLine();
            str = str.replace("%n", "\n");
            return str;
        } catch (IOException e) {
            System.out.println("** 오류입니다. **");
        }
        return "ERROR";
    }
    
    public void Message(String msg) {
        printWriter.println(msg);
    }
}