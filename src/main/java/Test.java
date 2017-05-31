package main.java;

import java.util.Arrays;
import main.library.Library;

public class Test {
	public static void main(String... args) {
		Library lib = new Library();
		// Math Numbers tests
		System.out.println("// Math Numbers tests");
		int perfectCount = 0;
		int abundantCount = 0;
		int deficientCount = 0;
		for (int i = 1; i <= 100; i++) {
			if (lib.isPerfect(i))
				perfectCount++;
			if (lib.isAbundant(i))
				abundantCount++;
			if (lib.isDeficient(i))
				deficientCount++;
		}
		System.out.println(perfectCount);
		System.out.println(abundantCount);
		System.out.println(deficientCount);
		System.out.println((deficientCount + abundantCount + perfectCount) == 100);
		// Multiple tests
		System.out.println("// Multiple tests");
		System.out.println(lib.isMultiple(2, 4));
		System.out.println(lib.isMultiple(2, 3));
		// Heads or tails tests
		System.out.println("// Heads or tails tests");
		System.out.println(lib.isHeads(3));
		// Size tests
		System.out.println("// Size tests");
		System.out.println(lib.Smallest(2, 32, 9));
		System.out.println(lib.Biggest(2, 32, 9));
		// Sorting tests
		System.out.println("// Sorting tests");
		System.out.println(Arrays.toString(lib.SortUP(2, 32, 9)));
		System.out.println(Arrays.toString(lib.SortDOWN(2, 32, 9)));
	}
}
