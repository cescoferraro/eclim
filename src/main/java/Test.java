package main.java;


import java.util.Arrays;

import main.library.Library;

public class Test {
	public static void main(String... args) {
		Library lib = new Library();
		// Math Numbers tests
		int [][] Matrix = lib.VendasMatriz();
		int [][] Filial1 = lib.VendasFilial1();
		int [][] Filia2 = lib.VendasFilial2();

		for (int clientes = 0; clientes < 1000; clientes++) {
			int [] pedidoRandom = lib.Pedido();
			int filialRandom = lib.FilialRandom();
			int sobrando = lib.LanchesSobrando(filialRandom,pedidoRandom);
			System.out.println(Arrays.toString(pedidoRandom));
			System.out.println(filialRandom);
			System.out.println(sobrando);
		}
	}
}
