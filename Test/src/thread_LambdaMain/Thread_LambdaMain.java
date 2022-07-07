package thread_LambdaMain;

public class Thread_LambdaMain {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Lambda로 쓰레드 생성
		new Thread(() ->{
			for(int i=0;i<500;i++) {
				System.out.printf("t1 Thread: %d \n",i);
			}
		}).start();
		
		new Thread(() ->{
			for(int i=0;i<500;i++) {
				System.out.printf("t2 Thread: %d \n",i);
			}
		}).start();
		
		for(int i=0;i<500;i++) {
			System.out.printf("main Thread: %d \n",i);
		}
	}
}