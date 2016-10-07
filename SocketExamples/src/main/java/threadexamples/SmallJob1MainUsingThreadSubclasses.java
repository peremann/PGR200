package threadexamples;

public class SmallJob1MainUsingThreadSubclasses {
	public static void main(String[] args) {

		// Testing SmallJob1AsThreadSubclass
		System.out.println("SmallJob1Test: main() is starting .....");
		SmallJob1AsThreadSubclass job1 = new SmallJob1AsThreadSubclass("Job 1");
		SmallJob1AsThreadSubclass job2 = new SmallJob1AsThreadSubclass("Job 2");
		SmallJob1AsThreadSubclass job3 = new SmallJob1AsThreadSubclass("Job 3");
		job1.start();
		job2.start();
		job3.start();
		System.out.println("SmallJob1Test: main() is done .....");

	}
}
