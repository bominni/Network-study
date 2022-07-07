package threadWithSemaphore;

import java.util.concurrent.Semaphore;

public class Thread_WithSemaphore implements Runnable{
	private int i=0;
	private Semaphore sem = new Semaphore(1); //Samaphore(접근 가능 갯수)
	
	public void run() {
		while(i<500) {
			try {
				sem.acquire();	// 임계구역 시작
					for(int j=0;j<2000;j++);
					System.out.printf((Thread.currentThread()).getName() + " i = " + i);
					System.out.println();
					i++;
				sem.release();	// 임계구역 종료
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}