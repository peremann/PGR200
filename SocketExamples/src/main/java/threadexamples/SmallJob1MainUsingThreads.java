package threadexamples;

public class SmallJob1MainUsingThreads {
	public static void main(String[] args) {
		// Testing SmallJob1AsThread
		System.out.println("SmallJob1Test: main() is starting .....");
		SmallJob1AsThread job1 = new SmallJob1AsThread("Job 1");
		SmallJob1AsThread job2 = new SmallJob1AsThread("Job 2");
		SmallJob1AsThread job3 = new SmallJob1AsThread("Job 3");
		new Thread(job1).start();
		new Thread(job2).start();
		new Thread(job3).start();
		System.out.println("SmallJob1Test: main() is done .....");
	}
}
