package threadexamples;

public class SmallJob1Main {
	public static void main(String[] args) {
		// Testing SmallJob1
		System.out.println("SmallJob1Test: main() is starting .....");
		SmallJob1 job1 = new SmallJob1("Job 1");
		SmallJob1 job2 = new SmallJob1("Job 2");
		SmallJob1 job3 = new SmallJob1("Job 3");
		job1.start();
		job2.start();
		job3.start();
		System.out.println("SmallJob1Test: main() is done .....");
	}
}
