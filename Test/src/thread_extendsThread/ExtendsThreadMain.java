package thread_extendsThread;

public class ExtendsThreadMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread t1 = new MyThread();
		MyThread2 t2 = new MyThread2();
		
		// t1.setPriority(Thread.MAX_PRIORITY);
		// t1.setPriority(Thread.MIN_PRIORITY);
		// t1.setPriority(Thread.NORM_PRIORITY);

		t1.start();
		t2.start();
		
		for(int i=0;i<500;i++) {
			System.out.printf("main Thread: %d \n",i);
		}
	}

}
