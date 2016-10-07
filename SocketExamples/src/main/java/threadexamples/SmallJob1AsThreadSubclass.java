package threadexamples;

public class SmallJob1AsThreadSubclass extends Thread {
	
	public SmallJob1AsThreadSubclass(String name){
		super(name);
	}
	
	public void run() {
		try {
			for (int i = 1; i <= 10; i++) {
				Thread.sleep((int) (Math.random() * 2000) + 1000);
				if (i == 5) {
					System.out.println(getName() + " is halfway done!");
				} else if (i == 7) {
					System.out.println(getName() + " soon 3/4 done!");
				}
			}
		} catch (InterruptedException e) {
			System.out.println(getName() + " - job interrupted");
		} finally {
			System.out.println(getName() + " is done!");
		}
	}
}
