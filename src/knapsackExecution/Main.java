package knapsackExecution;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Bruteforce SMOrc = new Bruteforce();
		SMOrc.classify("HALF");
		long finish = System.currentTimeMillis();
		System.out.println("DONE in " + (finish - start) + " miliseconds");
	}
}
