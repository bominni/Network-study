package threadImplementsRunnable;

public class ImplementsRunnableMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyRun_implements mi1 = new MyRun_implements();
		Thread t1=new Thread(mi1);
		
		MyRun_implements2 mi2 = new MyRun_implements2();
		Thread t2=new Thread(mi2);
		
		t1.start();
		t2.start();
		
		for(int i=0;i<500;i++) {
			System.out.printf("main Thread: %d \n",i);
		}
	}

}

