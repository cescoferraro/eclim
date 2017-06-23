package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import main.carta.Carta;
import main.lista.Lista;

public class Uno {
	private static Lista baralho;
	private static Lista mesa;
	private static Lista[] jogadores;
	private static int qtdJogadores = 0;
	private static int vezDoJogador = 0;
	private static int definidorDeVez = 1;
	private static Boolean passaVez = false;
	private static Boolean terminou = false;
	private static int escolha = -2;
	private static Scanner entrada = new Scanner(System.in);

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
			arq.close();
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

	public static void inicializacaoGeral() {
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

	public static void main(String[] args) {
		inicializacaoGeral();
		System.out.println(baralho.size());
		int resposta = 0;
		mesa = new Lista();
		Carta teste = baralho.remove();
		mesa.add(teste);

		limpaTela();

		Scanner entrada = new Scanner(System.in);

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
				System.out.println("1. Mostrar Mão \n2. Jogar Carta \n3. Comprar Carta \n4. Sair do jogo");
				System.out.println("Opção: ");
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
				default:
					limpaTela();
					passaVez = false;
				}
			}
		}
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

	public static void save() {
		try {
			FileWriter arq;
			PrintWriter writeArq;
			Carta c;
			int size = 0;

			// Salva baralho
			arq = new FileWriter("saveLoadBaralho.txt");
			writeArq = new PrintWriter(arq);
			size = baralho.size();

			for (int i = 0; i < baralho.size(); i++) {
				c = baralho.get(i);
				writeArq.printf(c.getNome() + "," + c.getCor() + "," + c.getEfeito());

				if (baralho.size() - 1 != i)
					writeArq.printf(";");
			}
			arq.close();

			// salva mesa
			arq = new FileWriter("saveLoadMesa.txt");
			writeArq = new PrintWriter(arq);
			size = mesa.size();

			for (int i = 0; i < mesa.size(); i++) {
				c = mesa.get(i);
				writeArq.printf(c.getNome() + "," + c.getCor() + "," + c.getEfeito());

				if (size - 1 != i)
					writeArq.printf(";");
			}
			arq.close();

			// salva jogadores
			arq = new FileWriter("saveLoadJogadores.txt");
			writeArq = new PrintWriter(arq);

			for (int i = 0; i < jogadores.length; i++) {
				Lista lstJog = jogadores[i];

				size = lstJog.size();
				for (int z = 0; z < lstJog.size(); z++) {
					c = lstJog.get(z);
					writeArq.printf(c.getNome() + "," + c.getCor() + "," + c.getEfeito());

					if (size - 1 != z)
						writeArq.printf(";");
				}

				if (jogadores.length - 1 != i)
					writeArq.printf(";;");
			}
			arq.close();

			// salva config
			arq = new FileWriter("saveLoadConfig.txt");
			writeArq = new PrintWriter(arq);

			writeArq.printf(qtdJogadores + ",");
			writeArq.printf(vezDoJogador + ",");
			writeArq.printf(definidorDeVez + ",");
			writeArq.printf(passaVez + ",");
			writeArq.printf(terminou.toString());
			arq.close();

			System.out.println("Salvo com sucesso.");

		} catch (IOException e) {
			System.out.println("Ocorreu um erro no processo. Tente novamente.");
		}
	}

	public static void load() {
		try {

			File arq;
			BufferedReader lerArq;
			String conteudo;

			// carrega baralho
			arq = new File("saveLoadBaralho.txt");
			if (arq.exists()) {
				baralho.clear();
				lerArq = new BufferedReader(new FileReader(arq));
				String[] lstObj = lerArq.readLine().split(";");
				Carta carta;

				for (int i = 0; i < lstObj.length; i++) {
					String atributos[] = lstObj[i].split(",");

					carta = new Carta(atributos[0], atributos[1], atributos[2]);
					baralho.add(i, carta);
				}

				// carrega mesa
				arq = new File("saveLoadMesa.txt");

				mesa.clear();
				lerArq = new BufferedReader(new FileReader(arq));
				lstObj = lerArq.readLine().split(";");

				for (int i = 0; i < lstObj.length; i++) {
					String atributos[] = lstObj[i].split(",");

					carta = new Carta(atributos[0], atributos[1], atributos[2]);
					mesa.add(i, carta);
				}

				// carrega jogadores
				arq = new File("saveLoadJogadores.txt");
				for (int x = 0; x < jogadores.length; x++)
					jogadores[x].clear();

				lerArq = new BufferedReader(new FileReader(arq));
				lstObj = lerArq.readLine().split(";;");

				for (int i = 0; i < lstObj.length; i++) {
					Lista jog = new Lista();

					String obj[] = lstObj[i].split(";");
					for (int z = 0; z < obj.length; z++) {
						String atributos[] = obj[z].split(",");

						carta = new Carta(atributos[0], atributos[1], atributos[2]);

						jog.add(carta);
					}

					jogadores[i] = jog;
				}

				// carrega config
				arq = new File("saveLoadConfig.txt");
				lerArq = new BufferedReader(new FileReader(arq));
				String atributos[] = lerArq.readLine().split(",");

				qtdJogadores = Integer.parseInt(atributos[0]);
				vezDoJogador = Integer.parseInt(atributos[1]);
				definidorDeVez = Integer.parseInt(atributos[2]);
				passaVez = Boolean.parseBoolean(atributos[3]);
				terminou = Boolean.parseBoolean(atributos[3]);

			} else {
				System.out.println("Não existe jogo salvo ou os arquivos foram corrompidos.");
			}

		} catch (IOException e) {
			System.out.println("Ocorreu um erro no processo. Tente novamente.");
	     }
 	}
}
