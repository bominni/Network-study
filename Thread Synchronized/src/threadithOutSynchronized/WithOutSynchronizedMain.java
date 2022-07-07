package threadithOutSynchronized;

public class WithOutSynchronizedMain { // Thread 임계 구역 설정 안한 경우(오류 발생)

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyRun_WithOutSynchronized mr1=new MyRun_WithOutSynchronized();
		Thread t1=new Thread(mr1, "t1");
		Thread t2=new Thread(mr1, "t2");
		Thread t3=new Thread(mr1, "t3");
		
		t1.start();
		t2.start();
		t3.start();
	}

}