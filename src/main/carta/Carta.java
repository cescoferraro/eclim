package main.carta;


public class Carta {
	private String nome, cor;
	private String especial;

	public Carta(String nome, String cor, String especial){
		this.nome = nome;
		this.cor = cor;
		this.especial = especial;
	}

	public String getNome(){
		return nome;
	}

	public String getCor(){
		return cor;
	}

	public String getEfeito(){
		return especial;
	}

	public void setCor(String cor){
		this.cor = cor;
	}
}
