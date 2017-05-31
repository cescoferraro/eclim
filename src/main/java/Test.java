package main.java;


import java.util.Arrays;

import main.library.Library;

public class Test {
	public static void main(String... args) {
		Library lib = new Library();
		// Math Numbers tests

		for (int clientes = 0; clientes < 1000; clientes++) {
			System.out.println(lib.FilialRandom());
			// System.out.println(lib.RandomLancheQuantidade());
			System.out.println(Arrays.toString(lib.Pedido()));
		}
	}
}
