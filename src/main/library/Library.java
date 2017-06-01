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

	public static int[][] VendasFilial1(){
		int[][] vendas = new int[1000][4];
		return vendas;
	} 
	public static int[][] VendasFilial2(){
		int[][] vendas = new int[1000][4];
		return vendas;
	} 

	public static int RandomRange(int min, int max) {
		Random rand = new Random();
		return min + rand.nextInt((max - min) + 1);
	}

	public static int RandomLancheQuantidade() {
		return RandomRange(1, 5); 
	}
	public static int[] Pedido() {
		int[]  pedido = new int [4];
		int max = 5;
		for (int i = 0 ; i < pedido.length; i ++){
			int qtde = RandomLancheQuantidade();
			if ( max > 0  && max - qtde > 0){
				pedido[i] = qtde;
				max = max - qtde;
			} else {
				pedido[i]=  0;
			}
		}
		return pedido;  
	}
	public static int LanchesSobrando(int unidade , int[] pedido){
		int cont = 0;
		for(int i = 0; i < pedido.length ; i ++){
			cont = cont + pedido[i];
		}
		switch (unidade){
		case 1:
			return 200 - cont;
		case 2:
			return 100 -cont;
		case 3:
			return 50 -cont;
		}
		return 0;
	}
	public static int FilialRandom() {
		return RandomRange(1, 3); 
	}
}
