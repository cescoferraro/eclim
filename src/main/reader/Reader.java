package main.reader;


import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	public BufferedReader reader;

	public Reader(){
        //le expressoes e monta cada expressao em uma arvore
        try {
			File arquivo = new File("arquivo.txt");
			if(arquivo.exists())
				reader = new BufferedReader(new FileReader(arquivo));
			else
				throw new FileNotFoundException();


			String linhaAtual;
			//itera sobre cada uma das expressoes (linhas do arquivo)
			do {
				linhaAtual = reader.readLine();
				if(linhaAtual == null)
					continue;
                
				System.out.println("Expressao: " + linhaAtual);
				int quantidadeAbreParenteses = linhaAtual.split("\\(", -1).length;
				int quantidadeFechaParenteses = linhaAtual.split("\\)", -1).length;
				if(quantidadeAbreParenteses != quantidadeFechaParenteses) {
					System.out.println("Expressao com erro de erro de sintaxe");
					continue;
				}
                
			} while(linhaAtual != null);
		} 
		catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado.");
		}
		catch (IOException e) {
			System.out.println("Erro na leitura do arquivo");
		} catch (Exception ex) {
			System.out.println("Erro:" + ex.getMessage());
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}

}
