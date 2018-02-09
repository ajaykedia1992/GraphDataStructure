package Google;

import java.util.*;

public class AlternativeNumber {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the no. elements in an array = ");
		int n = sc.nextInt();
		int[] a = new int[n];
		System.out.println("Enter numbers : ");
		for(int i = 0; i< a.length; i++) {
			a[i] = sc.nextInt();
		}
		Arrays.sort(a);
		System.out.print(a[0] + "<=" + a[a.length/2]);
		for(int i = 1, j = a.length/2 + 1; i<a.length/2; i++,j++) {
			System.out.print(">=" + a[i] + "<=" + a[j]);
		}
	}
}
