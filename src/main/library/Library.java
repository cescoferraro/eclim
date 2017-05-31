package main.library;

import java.util.Random;

public class Library {
	public static void main(String... args) {
		System.out.println("Library is instanciated!");
	}

	public static int[][] VendasMatriz(){
		int[][] vendas = new int[1000][4];
		return vendas;
	} 


	public static int RandomRange(int min, int max) {
		Random rand = new Random();
		return min + rand.nextInt((max - min) + 1);
	}

	public static int[] Pedido() {
		int[]  pedido = new int [4];
		int max = 5;
		for (int i = 0 ; i < pedido.length; i ++){
			int qtde = RandomRange(1,max);
			if ( max > 0  ){
				pedido[i] = qtde;
				max = max - qtde;
			} else {
				pedido[i]=  0;
			}
		}
		return pedido;  
	}

	public static int FilialRandom() {
		return RandomRange(1, 3); 
	}
}
