package threadImplementsRunnable;

public class MyRun_implements implements Runnable {
	
	// 작업 구현
	public void run() {
		for(int i=0;i<500;i++) {
			System.out.printf("t1 Thread: %d \n",i);
		}
	}
}