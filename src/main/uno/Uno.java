package main.uno;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import main.carta.Carta;
import main.lista.Lista;
import main.setup.Setup;

public class Uno {
	public static Lista baralho;
	public static Lista mesa;
	public static Lista[] jogadores;
	public static int qtdJogadores = 0;
	public static int vezDoJogador = 0;
	public static int definidorDeVez = 1;
	public static Boolean passaVez = false;
	public static Boolean terminou = false;
	public static int escolha = -2;

	public Uno() {
	}

	public static void geraBaralho() {

		baralho = new Lista();

		Scanner ler = new Scanner(System.in);

		System.out.print("Informe o nome do arquivo: ");
		String nome = ler.nextLine() + ".txt";

		try {
			FileReader arq = new FileReader(nome);
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine();
			String[] aux;
			Carta carta;
			while (linha != null) {
				aux = linha.split(" ");
				carta = new Carta(aux[0], aux[1], aux[2]);
				baralho.add(carta);
				linha = lerArq.readLine();
			}
		} catch (IOException e) {

		}
		baralho.embaralhar();
	}

	// Cada mao compra a quantidade inicial de cartas do jogo
	public static void iniciaMaos() {
		Carta teste;
		int qtdCartaInicial = 7;
		for (int i = 0; i < qtdJogadores; i++) {
			for (int j = 0; j < qtdCartaInicial; j++) {
				teste = baralho.remove();
				jogadores[i].add(teste);
			}
		}

	}

	public static void compraCarta() {
		Carta aux = baralho.remove();
		jogadores[vezDoJogador].add(aux);
		System.out.println("---------------------------------");
		System.out.print("Você comprou a carta: ");
		jogadores[vezDoJogador].printaUltima();
		System.out.println("---------------------------------");
		for (int i = 0; i < 7; i++) {
			System.out.println();
		}
	}

	public static void printaMaos() {
		for (int i = 0; i < qtdJogadores; i++) {
			System.out.println("Jogador " + (i + 1) + ": ");
			jogadores[i].printaTexto();
		}
	}

	public static void geraProximoJogador() {
		if (vezDoJogador == qtdJogadores - 1 && definidorDeVez == 1) {
			vezDoJogador = 0;
		} else if (vezDoJogador == 0 && definidorDeVez == -1) {
			vezDoJogador = qtdJogadores - 1;
		} else {
			vezDoJogador += definidorDeVez;
		}
	}

	public void Run() {
		int resposta = 0;

		while (!terminou) {
			while (!passaVez) {

				passaVez = true;
				System.out.println("---------------------------------");
				System.out.println("              Mesa");
				System.out.print("          ");
				mesa.printaUltima();
				System.out.println("---------------------------------");
				System.out.println();
				System.out.println("Vez do Jogador " + (vezDoJogador + 1) + ": ");
				System.out.print("1. Mostrar Mão \n");
				System.out.print("2. Jogar Carta \n");
				System.out.print("3. Comprar Carta \n");
				System.out.print("4. Sair do jogo\n");
				System.out.print("5. Salvar jogo \n");
				System.out.print("6. Carregar jogo \n");
				System.out.println("Opção: ");

				Scanner entrada = new Scanner(System.in);
				resposta = entrada.nextInt();
				switch (resposta) {
				case 1:
					limpaTela();
					mostraMao();
					System.out.println();
					passaVez = false;
					break;
				case 2:
					limpaTela();
					Boolean trocaRodada = false;
					// Mostra a mão
					mostraMao();
					System.out.println("\nQual carta deseja jogar?");
					while ((escolha < 0 || escolha > jogadores[vezDoJogador].size() - 1) && escolha != -1
						   && trocaRodada == false) {
						escolha = entrada.nextInt();
						if (escolha < 0 || escolha > jogadores[vezDoJogador].size() - 1) {
							limpaTela();
							System.out.println("Índice inválido.");
							System.out.println("Caso queira voltar atras, digite -1");
							mostraMao();
							System.out.println("\nQual carta deseja jogar?");
						}
						trocaRodada = verificaJogadaValida();
					}
					limpaTela();
					escolha = -2; // reseta a escolha para padrão
					passaVez = false; // reseta opcao do menu
					break;
				case 3:
					limpaTela();
					compraCarta();
					delay(1500);
					limpaTela();
					geraProximoJogador();
					passaVez = false;
					break;
				case 4:
					passaVez = true;
					terminou = true;
					break;
				case 5:
					limpaTela();
					Setup.save(this);
					System.out.println();
					passaVez = false;
					break;
				case 6:
					limpaTela();
					Setup.load(this);
					System.out.println();
					passaVez = false;
					break;
				default:
					limpaTela();
					passaVez = false;
				}
			}
		}

	}

	public void inicializacaoGeral() {
		geraBaralho();

		Scanner entrada = new Scanner(System.in);

		System.out.print("Quantidade de jogadores: ");

		while (qtdJogadores < 1 || qtdJogadores > 10) {
			qtdJogadores = entrada.nextInt();
		}

		jogadores = new Lista[qtdJogadores];

		// inicializando jogadores
		for (int i = 0; i < qtdJogadores; i++) {
			Lista jogador = new Lista();
			jogadores[i] = jogador;
		}
		iniciaMaos();
		virarCarta();
	}

	public void virarCarta(){
		mesa = new Lista();
		Carta teste = baralho.remove();
		mesa.add(teste);
		limpaTela();

	}
	public static void informaSeValeu(String mensagem) {
		// Informa na tela se valeu ou não
		limpaTela();
		System.out.println("---------------------------------");
		System.out.println(mensagem);
		System.out.println("---------------------------------");
		for (int i = 0; i < 7; i++) {
			System.out.println();
		}
		delay(1000);
		limpaTela();
	}

	public static Boolean verificaJogadaValida() {
		if (escolha > -1 && escolha < jogadores[vezDoJogador].size()) {
			if (((jogadores[vezDoJogador].get(escolha).getNome().equals(mesa.getLast().getNome()))
				 || (jogadores[vezDoJogador].get(escolha).getCor().equals(mesa.getLast().getCor())))
				|| jogadores[vezDoJogador].get(escolha).getNome().equals("Cur")
				|| jogadores[vezDoJogador].get(escolha).getNome().equals("+4")) {
				Carta aux = jogadores[vezDoJogador].remove(escolha);
				mesa.add(aux);
				// Informa na tela se valeu ou não
				informaSeValeu("Carta jogada.");
				// Verifica se o jogador ficou com 0 cartas
				verificaSeGanhou();
				// Executa os efeitos das cartas
				efeitosEspeciais();
				return true;
			} else {
				// Informa na tela se valeu ou não
				informaSeValeu("Carta inválida.");
				return false;
			}
		}
		return false;
	}

	public static void efeitosEspeciais() {
		String aux = mesa.getLast().getEfeito();
		int efeito = Integer.parseInt(aux);
		Scanner entrada = new Scanner(System.in);
		Carta auxCarta;
		int cor = -1;
		switch (efeito) {
		case 0:
			// Simplesmente passa a rodadas
			geraProximoJogador();
			break;
		case 1:
			// Adiciona 2 cartas para o jogador seguinte
			geraProximoJogador();
			auxCarta = baralho.remove();
			jogadores[vezDoJogador].add(auxCarta);
			auxCarta = baralho.remove();
			jogadores[vezDoJogador].add(auxCarta);
			System.out.println("Jogador: " + (vezDoJogador + 1) + " recebeu +2 e perdeu a vez.");
			geraProximoJogador();
			break;
		case 2:
			// Inverte o sentido do jogo
			if (definidorDeVez == 1) {
				definidorDeVez = -1;
			} else {
				definidorDeVez = 1;
			}
			System.out.println("Sentido do jogo foi invertido.");
			geraProximoJogador();
			break;
		case 3:
			// Bloqueia o próximo jogador
			geraProximoJogador();
			System.out.println("Jogador " + (vezDoJogador + 1) + " perdeu a vez");
			geraProximoJogador();
			break;
		case 4:
			// Efeito curinga, escolhe a cor da carta
			while (cor < 0 || cor > 3) {
				System.out.println("---------------------------------");
				System.out.println("Cores para o curinga");
				System.out.println("---------------------------------");
				System.out.println("0. Amarelo \n1. Azul \n2. Verde \n3. Vermelho\n");
				System.out.println("Qual a cor desejada?");
				cor = entrada.nextInt();
				switch (cor) {
				case 0:
					mesa.getLast().setCor("amarelo");
					break;
				case 1:
					mesa.getLast().setCor("azul");
					break;
				case 2:
					mesa.getLast().setCor("verde");
					break;
				case 3:
					mesa.getLast().setCor("vermelho");
					break;
				default:
					limpaTela();
				}
			}
			geraProximoJogador();
			System.out.println("Jogador: " + (vezDoJogador + 1) + " recebeu +4 e perdeu a vez.");
			limpaTela();
			break;
		case 5:
			// Efeito curinga, escolhe a cor da carta
			while (cor < 0 || cor > 3) {
				System.out.println("---------------------------------");
				System.out.println("Cores para o +4");
				System.out.println("---------------------------------");
				System.out.println("0. Amarelo \n1. Azul \n2. Verde \n3. Vermelho\n");
				System.out.println("Qual a cor desejada?");
				cor = entrada.nextInt();
				switch (cor) {
				case 0:
					mesa.getLast().setCor("amarelo");
					break;
				case 1:
					mesa.getLast().setCor("azul");
					break;
				case 2:
					mesa.getLast().setCor("verde");
					break;
				case 3:
					mesa.getLast().setCor("vermelho");
					break;
				default:
					limpaTela();
				}
			}
			limpaTela();
			geraProximoJogador();
			// Adiciona 4 cartas na mao do jogador
			for (int i = 0; i < 4; i++) {
				auxCarta = baralho.remove();
				jogadores[vezDoJogador].add(auxCarta);
			}
			System.out.println("Jogador: " + (vezDoJogador + 1) + " recebeu +4 e perdeu a vez.");
			geraProximoJogador();
			break;
		default:
			;
		}
	}

	public static void verificaSeGanhou() {
		if (jogadores[vezDoJogador].size() == 0) {
			System.out.println("---------------------------------");
			System.out.println("Jogador " + (vezDoJogador + 1) + " ganhou!!!");
			System.out.println("---------------------------------");
			System.out.println("\n\n\n\n\n");
			System.exit(0);
		}
	}

	public static void mostraMao() {
		System.out.println("---------------------------------");
		System.out.println("Mão do Jogador " + (vezDoJogador + 1) + ": ");
		System.out.println("---------------------------------");
		jogadores[vezDoJogador].printaTexto();
	}

	public static void delay(int tempo) {
		try {
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void limpaTela() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	/**
	 * @return the baralho
	 */
	public static Lista getBaralho() {
		return baralho;
	}

	/**
	 * @param baralho
	 *            the baralho to set
	 */
	public static void setBaralho(Lista baralho) {
		Uno.baralho = baralho;
	}

	/**
	 * @return the mesa
	 */
	public static Lista getMesa() {
		return mesa;
	}

	/**
	 * @param mesa
	 *            the mesa to set
	 */
	public static void setMesa(Lista mesa) {
		Uno.mesa = mesa;
	}

	/**
	 * @return the jogadores
	 */
	public static Lista[] getJogadores() {
		return jogadores;
	}

	/**
	 * @param jogadores
	 *            the jogadores to set
	 */
	public static void setJogadores(Lista[] jogadores) {
		Uno.jogadores = jogadores;
	}

	/**
	 * @return the qtdJogadores
	 */
	public static int getQtdJogadores() {
		return qtdJogadores;
	}

	/**
	 * @param qtdJogadores
	 *            the qtdJogadores to set
	 */
	public static void setQtdJogadores(int qtdJogadores) {
		Uno.qtdJogadores = qtdJogadores;
	}

	/**
	 * @return the vezDoJogador
	 */
	public static int getVezDoJogador() {
		return vezDoJogador;
	}

	/**
	 * @param vezDoJogador
	 *            the vezDoJogador to set
	 */
	public static void setVezDoJogador(int vezDoJogador) {
		Uno.vezDoJogador = vezDoJogador;
	}

	/**
	 * @return the definidorDeVez
	 */
	public static int getDefinidorDeVez() {
		return definidorDeVez;
	}

	/**
	 * @param definidorDeVez
	 *            the definidorDeVez to set
	 */
	public static void setDefinidorDeVez(int definidorDeVez) {
		Uno.definidorDeVez = definidorDeVez;
	}

	/**
	 * @return the passaVez
	 */
	public static Boolean getPassaVez() {
		return passaVez;
	}

	/**
	 * @param passaVez
	 *            the passaVez to set
	 */
	public static void setPassaVez(Boolean passaVez) {
		Uno.passaVez = passaVez;
	}

	/**
	 * @return the terminou
	 */
	public static Boolean getTerminou() {
		return terminou;
	}

	/**
	 * @param terminou
	 *            the terminou to set
	 */
	public static void setTerminou(Boolean terminou) {
		Uno.terminou = terminou;
	}

	/**
	 * @return the escolha
	 */
	public static int getEscolha() {
		return escolha;
	}

	/**
	 * @param escolha
	 *            the escolha to set
	 */
	public static void setEscolha(int escolha) {
		Uno.escolha = escolha;
	}

}
