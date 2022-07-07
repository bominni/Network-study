package thread_extendsThread;

public class MyThread2 extends Thread{

	// 작업 및 쓰레드 구현 
	public void run() {
		for(int i=0;i<500;i++) {
			System.out.printf("t2 Thread: %d \n",i);
		}
	}
}
