package main.java;

import java.util.Scanner;

public class Test {
    public static void main(String... args) {
		Scanner entrada = new Scanner(System.in);
		Double largura = entrada.nextDouble();
		Double comprimento = entrada.nextDouble();
		Double terrenoLargura = entrada.nextDouble();
		Double terrenoComprimento= entrada.nextDouble();
		Area(largura, comprimento);
		Area(terrenoLargura,terrenoComprimento);
		AreaLivre(largura,comprimento,terrenoComprimento,terrenoLargura);
		System.out.println("Eclim is awesome!");
		entrada.close();
    }
	public static Double Area(Double largura,Double comprimento){
		return largura * comprimento;
	}

	public static Double AreaLivre(Double largura,
								   Double comprimento,
								   Double terrenoComprimento, 
								   Double terrenoLargura){
		return Area(terrenoLargura , terrenoComprimento) - Area(largura,comprimento);
	}
}
