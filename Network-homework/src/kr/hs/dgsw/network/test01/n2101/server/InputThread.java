package kr.hs.dgsw.network.test01.n2101.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class InputThread extends Thread{
    static ArrayList<InputThread> threads = new ArrayList<>();
    private static String Directory = "C:\\Users\\DGSW\\NetWork\\Test";
    
	private FileCheck fileCheck;
	private Socket socket;
	private InputStream inputStream;
	private BufferedReader bufferedReader;
	private OutputStream outputStream;
	private PrintWriter printWriter;

    public InputThread(Socket sc) throws IOException {
        this.socket = sc;
        this.fileCheck = new FileCheck(Directory);
        try { this.inputStream = this.socket.getInputStream();}
        catch (IOException e) { throw new RuntimeException(e); }
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            outputStream = this.socket.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        printWriter = new PrintWriter(outputStream, true);
        this.Message("** 서버에 접속하였습니다. **");
        this.start();
    }
    
    public void Message (String mes){
        printWriter.println(mes);
    }
    
    public String MessageSend() throws IOException {
        try {
            String read = bufferedReader.readLine();
            if(read == null) {
                throw new IOException();
            }
            return read;
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public Boolean loginProc() {
        while(true) {
            String[] messages = new String[0];
            try {
                messages = this.MessageSend().split("%");
            } catch (IOException e) {
                return false;
            }
            if(messages.length != 3) {
                this.Message("입력이 잘못되었습니다.");
                continue;
            }
            String command = messages[0];
            if(command.equals("login")) {
                if(messages[1].equals("admin") && messages[2].equals("1234")) {
                    this.Message("** FTP 서버에 접속하였습니다. **");
                    return true;
                }
                else {
                    this.Message("** ID 또는 PASS가 틀렸습니다.! **");
                }
            }
        }
    }
    
    public void run() {
        if(this.loginProc()) {
            threads.add(this);
            while (true) {
                String cmd;
                try {
                    cmd = this.MessageSend();
                } catch (IOException e) {
                    threads.remove(this);
                    break;
                }
                if(cmd.equals("NULL")) {
                    this.Message("NULL");
                }
                else {
                    String[] get = cmd.split("%");
                    String command = get[0];
                    if(command.equals("ls")) {
                        ArrayList<String> files = this.fileCheck.fileList();
                        String Buffer = String.format("파일이 %d개 있습니다.", files.size());
                        for (String file : files) {
                        	Buffer += "%n" + file;
                        }
                        this.Message(Buffer);
                    }
                    else if(command.equals("upload")) {
                        if(this.fileCheck.wheterFile(get[get.length - 1].split("/")[get[get.length - 1].split("/").length - 1])) {
                            this.Message("파일이 이미 있습니다. 덮어쓰기 하실건가요??(Yes: 덮어쓰기 / No: 업로드 취소):");
                            try {
                                if(this.MessageSend().equals("Yes")) {
                              
                                    int sendByte = this.fileCheck.FileDownload(inputStream);
                                    this.Message(fileCheck.Byte(sendByte) + " send");
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else {
                        	this.Message("SKIP");
                        	int sendByte = this.fileCheck.FileDownload(inputStream);
                            this.Message(fileCheck.Byte(sendByte) + " send");
                        }
                       
                    }
                    else if (command.equals("download")) {
                        int size = this.fileCheck.uploadFile(this.outputStream, get[1]);
                        this.Message(fileCheck.Byte(size) + " download");
                    }
                    else if(command.equals("exit")) {
                        try {
                            System.out.println("EXIT");
                            this.socket.close();
                            break;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else {
                        this.Message("Unknown Command");
                    }
                }

            }
        }
    }
}
