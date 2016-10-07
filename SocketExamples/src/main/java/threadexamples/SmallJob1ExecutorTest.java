package threadexamples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmallJob1ExecutorTest {
	public static void main(String[] args) {
		// Testing SmallJob1AsThread with Executor
		System.out.println("SmallJob1ExecutorTest: main() starter .....");
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(new SmallJob1AsThread("Job 1"));
		executor.execute(new SmallJob1AsThread("Job 2"));
		executor.execute(new SmallJob1AsThread("Job 3"));
		
		executor.shutdown();
		
		System.out.println("SmallJob1ExecutorTest: main() avslutter .....");
	}
}
