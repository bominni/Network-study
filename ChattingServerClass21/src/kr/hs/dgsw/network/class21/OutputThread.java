package kr.hs.dgsw.network.class21;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class OutputThread  extends Thread {
Socket sc = null;
    
    
    public OutputThread(Socket sc){
        this.sc = sc;
    }
    
    public void run(){
        Scanner scan = new Scanner(System.in);
        
        OutputStream os = null;
        try{
            os = sc.getOutputStream();
        }catch (IOException e){
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(os, true);
        
        while(true)
        	pw.println(scan.nextLine());
    }
}
