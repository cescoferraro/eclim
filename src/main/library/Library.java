package main.library;

import java.util.Arrays;
import java.util.Comparator;

public class Library {
	public static void main(String... args) {
		System.out.println("Library is instanciated!");
	}

	// Math Number types
	private int commonAlgorhythm(int number) {
		int temp = 0;
		for (int i = 1; i <= number / 2; i++) {
			if (number % i == 0) {
				temp += i;
			}
		}
		return temp;
	}

	public boolean isPerfect(int number) {
		return commonAlgorhythm(number) == number;
	}

	public boolean isDeficient(int number) {
		return commonAlgorhythm(number) < number;
	}

	public boolean isAbundant(int number) {
		return commonAlgorhythm(number) > number;
	}

	// Extra methods
	public boolean isHeads(int number) {
		return number % 2 == 0;
	}

	public boolean isMultiple(int a, int b) {
		return b % a == 0;
	}

	// Areas
	// (quadrado, retângulo, circulo, triângulo)
	public Double SquareArea(Double side) {
		return Math.pow(side, 2);
	}

	public Double ParallelogramArea(Double sideA, Double sideB) {
		return (sideA * sideB);
	}

	public Double TriangleArea(Double base, Double height) {
		return (base * height) / 3;
	}

	public Double CircleArea(Double radius) {
		return Math.pow(radius, 2) * Math.PI;
	}

	// Superfície Piramyd Missing
	// paralelepípedo, pirâmide, cilindro, esfera
	public Double ParallelepipedSurfaceArea(Double sideA, Double sideB, Double height) {
		return (2 * (sideA * sideB) + 2 * (sideA * height) + 2 * (sideB + height));
	}

	public Double CilinderSurfaceArea(Double radius, Double height) {
		return ((2 * radius * Math.PI) * height) + (2 * Math.PI + Math.pow(radius, 2));
	}

	public Double EsphereSurfaceArea(Double radius) {
		return (4) * Math.pow(radius, 2) * Math.PI;
	}

	// Volumes DONE!
	// : paralelepípedo, pirâmide, cilindro, esfera
	public Double ParallelepipedVolume(Double sideA, Double sideB, Double sideC) {
		return sideA * sideB * sideC;
	}

	public Double PyramidVolume(Double sideA, Double sideB, Double height) {
		return (sideA * sideB) * height;
	}

	public Double CilinderVolume(Double radius, Double height) {
		return (height) * Math.pow(radius, 2) * Math.PI;
	}

	public Double EsphereVolume(Double radius) {
		return (4 / 3) * Math.pow(radius, 3) * Math.PI;
	}

	public int Smallest(int a, int b, int c) {
		int[] tab = { a, b, c };
		return Arrays.stream(tab).min().getAsInt();
	}

	public int Biggest(int a, int b, int c) {
		int[] tab = { a, b, c };
		return Arrays.stream(tab).max().getAsInt();
	}

	public int[] SortUP(int a, int b, int c) {
		int[] tab = { a, b, c };
		Arrays.sort(tab);
		return tab;
	}

	Comparator<Integer> comparator = new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	};

	public Integer[] SortDOWN(int a, int b, int c) {
		Integer[] tab = { a, b, c };
		Arrays.sort(tab, comparator);
		return tab;
	   }
}
