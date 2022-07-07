package threadCake;

public class CakePlate {
	private int breadCount = 0;
	public CakePlate() {}
	
	// ¿ÁªßªÁ∞° ªß¿ª ∏∏µÍ
	public synchronized void makeBread() {
		if(breadCount>=10) {
			try {
				System.out.println("ªß¿Ã ≥≤¥¬¥Ÿ.");
				wait();								// ¥Î±‚
			} catch(InterruptedException ire) {}
		}
		breadCount++;
		System.out.println("ªß¿ª 1∞≥ ¥ı ∏∏µÍ √—: " + breadCount + "∞≥");
		this.notifyAll();							// ¥Î±‚ «ÿ¡¶
	}
	
	// º’¥‘¿Ã ªß¿ª º“∫Ò«‘
	public synchronized void eatBread() {
		if(breadCount<1) {
			try {
				System.out.println("ªß¿Ã ∏¿⁄∂Û ±‚¥Ÿ∏≤");
				wait();
			} catch(InterruptedException ire) {}
		}
		breadCount--;
		System.out.println("ªß¿ª 1∞≥ ∏‘¿Ω √—: " + breadCount + "∞≥");
		this.notifyAll();
	}
}