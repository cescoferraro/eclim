package main.java;

import java.util.Arrays;

import main.library.Library;

public class Test {
	public static void main(String... args) {
		Library lib = new Library();
		lib.populate(700);
		int[] matrizResultados = lib.totlaDeVendas(1);
		int[] filial1Resultados = lib.totlaDeVendas(2);
		int[] filial2Resultados = lib.totlaDeVendas(3);
		System.out.println(Arrays.toString(matrizResultados));
		System.out.println(Arrays.toString(filial1Resultados));
		System.out.println(Arrays.toString(filial2Resultados));
		// Primeira
		System.out.println(lib.TOTALMAX());
		//Segunda
		System.out.println(lib.LancheMaisVendido());
		// Terceira
		System.out.println(lib.LancheMenosVendido());
		// Quarta
		int ganahdor = lib.arecadadoPorLanche(lib.LancheMaisVendido());
		int perdedor =lib.arecadadoPorLanche(lib.LancheMenosVendido());
		System.out.println(ganahdor - perdedor);
		// Quinta
		int[] maiorPedidoMatriz =  lib.MaiorPedido(lib.matrix);
		int[] maiorPedidoFilial1 = lib.MaiorPedido(lib.filial1 );
		int [] maiorPedidoFilial2 = lib.MaiorPedido(lib.filial2 );
		System.out.println(Arrays.toString(maiorPedidoMatriz));
		System.out.println(Arrays.toString( maiorPedidoFilial1));
		System.out.println(Arrays.toString( maiorPedidoFilial2));
		//Sexta
		// Varios pedidos chegaram no limite máximo de lanches por pedido.
		// fica impossível de dizer qual a venda com mais lanches.
		// Na quinta questão eu to retornando o último pedido com o número máximo
		// pq acontece a mesma coisa em cada unidade.
		// System.out.println(lib.LancheMaisVendido());
	}
}
