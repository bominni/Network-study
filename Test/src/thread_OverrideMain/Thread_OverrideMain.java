package thread_OverrideMain;

public class Thread_OverrideMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Override 방식으로 쓰레드 생성
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0;i<500;i++) {
					System.out.printf("t1 Thread: %d \n",i);
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0;i<500;i++) {
					System.out.printf("t2 Thread: %d \n",i);
				}
			}
		});
		
		t1.start();
		t2.start();
		
		for(int i=0;i<500;i++) {
			System.out.printf("main Thread: %d \n",i);
		}
	}

}