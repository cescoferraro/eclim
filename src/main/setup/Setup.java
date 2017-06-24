package main.setup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import main.carta.Carta;
import main.uno.Uno;
import main.lista.Lista;

public class Setup {
	public Setup() {}

	public static void save(Uno uno) {
		try {
			FileWriter arq;
			PrintWriter writeArq;
			Carta c;
			int size = 0;

			// Salva baralho
			arq = new FileWriter("saveLoadBaralho.txt");
			writeArq = new PrintWriter(arq);
			size = uno.baralho.size();

			for (int i = 0; i < uno.baralho.size(); i++) {
				c = uno.baralho.get(i);
				writeArq.printf(c.getNome() + "," + c.getCor() + "," + c.getEfeito());

				if (uno.baralho.size() - 1 != i)
					writeArq.printf(";");
			}
			arq.close();

			// salva mesa
			arq = new FileWriter("saveLoadMesa.txt");
			writeArq = new PrintWriter(arq);
			size = uno.mesa.size();

			for (int i = 0; i < uno.mesa.size(); i++) {
				c = uno.mesa.get(i);
				writeArq.printf(c.getNome() + "," + c.getCor() + "," + c.getEfeito());

				if (size - 1 != i)
					writeArq.printf(";");
			}
			arq.close();

			// salva jogadores
			arq = new FileWriter("saveLoadJogadores.txt");
			writeArq = new PrintWriter(arq);

			for (int i = 0; i < uno.jogadores.length; i++) {
				Lista lstJog = uno.jogadores[i];

				size = lstJog.size();
				for (int z = 0; z < lstJog.size(); z++) {
					c = lstJog.get(z);
					writeArq.printf(c.getNome() + "," + c.getCor() + "," + c.getEfeito());

					if (size - 1 != z)
						writeArq.printf(";");
				}

				if (uno.jogadores.length - 1 != i)
					writeArq.printf(";;");
			}
			arq.close();

			// salva config
			arq = new FileWriter("saveLoadConfig.txt");
			writeArq = new PrintWriter(arq);

			writeArq.printf(uno.qtdJogadores + ",");
			writeArq.printf(uno.vezDoJogador + ",");
			writeArq.printf(uno.definidorDeVez + ",");
			writeArq.printf(uno.passaVez + ",");
			writeArq.printf(uno.terminou.toString());
			arq.close();

			System.out.println("Salvo com sucesso.");

		} catch (IOException e) {
			System.out.println("Ocorreu um erro no processo. Tente novamente.");
		}
	}

	public static void load(Uno uno) {
		try {

			File arq;
			BufferedReader lerArq;
			String conteudo;

			// carrega baralho
			arq = new File("saveLoadBaralho.txt");
			if (arq.exists()) {
				uno.baralho.clear();
				lerArq = new BufferedReader(new FileReader(arq));
				String[] lstObj = lerArq.readLine().split(";");
				Carta carta;

				for (int i = 0; i < lstObj.length; i++) {
					String atributos[] = lstObj[i].split(",");

					carta = new Carta(atributos[0], atributos[1], atributos[2]);
					uno.baralho.add(i, carta);
				}

				// carrega mesa
				arq = new File("saveLoadMesa.txt");

				uno.mesa.clear();
				lerArq = new BufferedReader(new FileReader(arq));
				lstObj = lerArq.readLine().split(";");

				for (int i = 0; i < lstObj.length; i++) {
					String atributos[] = lstObj[i].split(",");

					carta = new Carta(atributos[0], atributos[1], atributos[2]);
					uno.mesa.add(i, carta);
				}

				// carrega jogadores
				arq = new File("saveLoadJogadores.txt");
				for (int x = 0; x < uno.jogadores.length; x++)
					uno.jogadores[x].clear();

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

					uno.jogadores[i] = jog;
				}

				// carrega config
				arq = new File("saveLoadConfig.txt");
				lerArq = new BufferedReader(new FileReader(arq));
				String atributos[] = lerArq.readLine().split(",");

				uno.qtdJogadores = Integer.parseInt(atributos[0]);
				uno.vezDoJogador = Integer.parseInt(atributos[1]);
				uno.definidorDeVez = Integer.parseInt(atributos[2]);
				uno.passaVez = Boolean.parseBoolean(atributos[3]);
				uno.terminou = Boolean.parseBoolean(atributos[3]);

			} else {
				System.out.println("Não existe jogo salvo ou os arquivos foram corrompidos.");
			}

		} catch (IOException e) {
			System.out.println("Ocorreu um erro no processo. Tente novamente.");
		}
	}

}
