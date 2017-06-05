package main.java;

import java.util.Arrays;

import main.library.Library;

public class Test {
	public static void main(String... args) {
		Library lib = new Library();
		lib.populate(100);
		int[] matrizResultados = lib.totlaDeVendas(1);
		int[] filial1Resultados = lib.totlaDeVendas(2);
		int[] filial2Resultados = lib.totlaDeVendas(3);
		System.out.println("===========================");
		System.out.println("Relatório");
		System.out.printf("matriz: %s \n", Arrays.toString(matrizResultados));
		System.out.printf("filial1: %s \n", Arrays.toString(filial1Resultados));
		System.out.printf("filial2: %s \n", Arrays.toString(filial2Resultados));
		System.out.println("===========================");
		// Primeira
		//
		System.out.println("Total de vendas de todas as lojas");
		System.out.printf("Total: %s \n", lib.TOTALMAX());
		// Segunda
		System.out.println("Lanche mais vendido");
		System.out.printf("Nro: %s \n", lib.LancheMaisVendido());
		// Terceira
		System.out.println("Lanche menos vendido");
		System.out.printf("Nro: %s \n", lib.LancheMenosVendido());
		// Quarta
		System.out.println("Diferença da arrecadação do lanche mais e menos vendido");
		int ganahdor = lib.arecadadoPorLanche(lib.LancheMaisVendido());
		int perdedor = lib.arecadadoPorLanche(lib.LancheMenosVendido());
		System.out.printf("diferença: %s \n", ganahdor - perdedor);
		// Quinta
		System.out.println("A venda onde mais lanches foram vendidos para cada loja");
		int[] maiorPedidoMatriz = lib.MaiorPedido(lib.matrix);
		int[] maiorPedidoFilial1 = lib.MaiorPedido(lib.filial1);
		int[] maiorPedidoFilial2 = lib.MaiorPedido(lib.filial2);
		System.out.printf("Matriz: %s \n", Arrays.toString(maiorPedidoMatriz));
		System.out.printf("Filial1: %s \n", Arrays.toString(maiorPedidoFilial1));
		System.out.printf("Filial2: %s \n", Arrays.toString(maiorPedidoFilial2));
		// Quinta 2
		System.out.println("Valor da venda mais cara por filial");
		int maiorPedidoMatrizV = lib.MaiorPedidoValor(lib.matrix);
		int maiorPedidoFilial1V = lib.MaiorPedidoValor(lib.filial1);
		int maiorPedidoFilial2V = lib.MaiorPedidoValor(lib.filial2);
		System.out.printf("Matriz: %s \n", String.valueOf(maiorPedidoMatrizV));
		System.out.printf("Filial1: %s \n", String.valueOf(maiorPedidoFilial1V));
		System.out.printf("Filial2: %s \n", String.valueOf(maiorPedidoFilial2V));
		// Sexta
		// Varios pedidos chegaram no limite máximo de lanches por pedido.
		// fica impossível de dizer qual a venda com mais lanches.
		// Não podemos dizer que o pedido de 5 lanche1 é maior que o pedido de 5 lanches 2
		// Estamos calculando o custo do lanche mais caro de todas a filiais e o pedido
		System.out.println("A venda onde mais lanches foram vendidos dentre todas as lojas.");
		System.out.printf("Custou: %s \n", lib.ValorMaiorPedidoValorTodas());
		System.out.printf("vendendo: %s \n", Arrays.toString(lib.MaiorPedidoValorTodas()));
	}
}
