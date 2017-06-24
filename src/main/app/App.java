package main.app;

import main.uno.Uno;
import main.lista.Lista;
import main.carta.Carta;

public class App {
	public static void main(String[] args) {
		Uno newGame = new Uno();
		newGame.inicializacaoGeral();	
		newGame.setMesa(new Lista());
		Lista baralhoAtual = newGame.getBaralho();
		Carta primeira = baralhoAtual.remove();
		newGame.setBaralho(baralhoAtual);
		Lista mesa = newGame.getMesa();
		mesa.add(primeira);
		newGame.setMesa(mesa);
		newGame.limpaTela();
		newGame.Run();
	}
}
