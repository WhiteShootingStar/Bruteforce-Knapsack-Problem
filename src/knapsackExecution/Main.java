package knapsackExecution;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		Bruteforce SMOrc = new Bruteforce();
		SMOrc.classify("7");
//		long start = System.currentTimeMillis();
//		System.out.println(Arrays.toString(getCH(100000)));
//		long finish = System.currentTimeMillis();
//		System.out.println(finish-start + " milseconds for bool");
//		
//		 start = System.currentTimeMillis();
//		System.out.println(Arrays.toString(getCharVector(100000)));
//		finish = System.currentTimeMillis();
//		System.out.println(finish-start + " milseconds for charVector");
//		
//		 start = System.currentTimeMillis();
//		System.out.println(Arrays.toString(getCharVectorNEW(100000)));
//		 finish = System.currentTimeMillis();
//		System.out.println(finish-start + " milseconds for charVectorNew");
//		
//	}
//	static int arSize=25;
//	private static boolean[] getCH(int number) {
//		boolean[] bits = new boolean[number];
//		for (int i = number-1; i >= 0; i--) {
//			bits[i] = (number & (1 << i)) != 0;
//		}
//		return bits;
//	}
//	private static int[] getCharVector(int number) {
//		int[] vector = new int[arSize];
//
//		for (int j = vector.length - 1; j >= 0; j--) {
//			vector[j] = number % 2;
//			number = number / 2;
//		}
//		return vector;
//	}
//
//	private static  int[] getCharVectorNEW(int number) {
//		int[] vector = new int[arSize];
//
//		String val = Integer.toBinaryString(number);
//		for (int i = val.length() - 1; i >= 0; i--) {
//			vector[i] = Character.getNumericValue(val.charAt(i));
//
//		}
//		return vector;
	}
}
