package 정답3;

import java.util.concurrent.Semaphore;

public class Thread_DiningPhilosopher extends Thread{
	private static Semaphore eat = new Semaphore(1);
	int id; // 철학자 id
    Semaphore lstick, rstick; // 왼쪽, 오른쪽 젓가락
    Thread_DiningPhilosopher(int id, Semaphore lstick, Semaphore rstick) {
        this.id = id;
        this.lstick = lstick;
        this.rstick = rstick;
    }
    public void run() {
        try {
            while(true) {
            	eat.acquire();
	                lstick.acquire(); 		// 왼쪽 집어들기
	                rstick.acquire(); 		// 오른쪽 집어들기
	            eat.release();
	                eating(); 				// 식사
	                Thread.sleep(1);		// 무한 반복문에 의한 렉 현상 해결용(문제 해결과 상관 없음)
	                rstick.release(); 		// 오른쪽 내려놓기
	                lstick.release(); 		// 왼쪽 내려놓기
                thinking(); 			// 생각하기
            }
        } catch (InterruptedException e) {}
    }
    
    void eating() {
        System.out.println("[" + id + "] 식사 중");
    }
    
    void thinking() {
        System.out.println("[" + id + "] 생각 중");
    }
}