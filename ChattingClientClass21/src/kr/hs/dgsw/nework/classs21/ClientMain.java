package kr.hs.dgsw.nework.classs21;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientMain {
	public static void main(String[] args) {
		BufferedReader ibr = new BufferedReader(new InputStreamReader(System.in));

        {
            try {
                Socket sc;
                sc = new Socket("10.80.161.188", 5000);

                //서버로 데이터 보내기
//                OutputStream os = sc.getOutputStream();
//                PrintStream pw = new PrintStream(os, true);
//
//                String imsg = "";
//                while((imsg=ibr.readLine())!=null){
//                    pw.println(imsg);
//                }
//
//                pw.println("천사");
//
//                //메시지 수신
//                InputStream is = sc.getInputStream();
//                BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//                String msg = br.readLine();     //메시지 읽기
//                System.out.println(msg);        //출력

                Thread it = new InputThread(sc);
                Thread ot = new OutputThread(sc);

                it.start();
                ot.start();

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
