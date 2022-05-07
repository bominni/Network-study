package kr.hs.dgsw.network.class21;

public class ThreadMain {

	public static void main(String[] args) {
		
		/* 4월 26일 */
		Thread1 m1 = new Thread1();
		
		Thread t1 = new Thread(m1, "t1");
		Thread t2 = new Thread(m1, "t2");
		Thread t3 = new Thread(m1, "t3");
		Thread t4 = new Thread(m1, "t4");
		Thread t5 = new Thread(m1, "t5");
		Thread t6 = new Thread(m1, "t6");
		Thread t7 = new Thread(m1, "t7");
		Thread t8 = new Thread(m1, "t8");
		Thread t9 = new Thread(m1, "t9");
		Thread t10 = new Thread(m1, "t10");
		
		// t1.setPriority(Thread.MAX_PRIORITY);
		// t1.setPriority(Thread.MIN_PRIORITY);
		// t1.setPriority(Thread.NORM_PRIORITY); <-기본값
		

		t10.setPriority(10);
		t1.setPriority(1);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		
		for(int i=0; i<100; i++) {
			System.out.printf("Main Thread: %d\n", i);
		}

//        try{
//            for(int i=0; i<500; i++) {
//                System.out.printf("t1 Thread: %d \n", i);
//                Thread.sleep(1);
//            }
//        } catch (InterruptedException e) {
//        }
//        t1.interrupt();
	}
}



/* 4월 21일 */
//		//Thread1 m1 = new Thread1();
//		Thread2 m2 = new Thread2();
//	
//		Thread t1 = new Thread();
//		Thread t2 = new Thread(m2);
//	
//		//Override 방식으로 쓰레드 생성
//		Thread t3 = new Thread(new Runnable () {
//			@Override
//			public void run() {
//				for(int i=0; i<500; i++) {
//					System.out.printf("t3 Thread: %d\n i");
//				}
//			}
//		});
//		
//		t1.start();
//		t2.start();
//		t3.start();
//		
//		//Lambda로 쓰레드 생성
//		new Thread( () -> {
//			for(int i=0; i<500; i++) {
//				System.out.printf("t4 Thread: %d \n", i);
//			}
//		}).start();
//		
//		for(int i=0; i<500; i++) {
//			System.out.println("main Thread : " + i);
//		}
//		
//		try {
//			t1.join();
//			t2.join();
//			t3.join();
//		} catch(InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println("모두 종료");
//	}
//	
//}
