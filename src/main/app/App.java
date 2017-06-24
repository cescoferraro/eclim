package main.app;

import main.uno.Uno;

public class App {
	public static void main(String[] args) {
		Uno newGame = new Uno();
		newGame.inicializacaoGeral();	
		newGame.Run();
	}
}
