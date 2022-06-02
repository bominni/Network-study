package kr.hs.dgsw.network.class21.server;

import java.util.Scanner;

public class ServerMain {
	
	public static void login() {
		Scanner i = new Scanner(System.in);
		Scanner p = new Scanner(System.in);
		
		String id;
		int pw;
		
		while(true) {
			System.out.printf("ID : ");
			id = i.nextLine();
			System.out.printf("PASS : ");
			pw = p.nextInt();
			if(id.equals("admin") && pw == 1234) {
				System.out.println("** FTP 서버에 접속하였습니다. **");
				break;
			}
			else {
				System.out.println("** ID 또는 PASS가 틀렸습니다.! **");
			}
		}
		
	}
	
	public static void main(String[] args) {
		ServerMain sm = new ServerMain();
		ServerMain.login();
	}

}
