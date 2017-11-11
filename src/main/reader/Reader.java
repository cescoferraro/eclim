package main.reader;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import main.tree.Tree;
import main.queue.Queue;
import java.util.*;

public class Reader {
	public BufferedReader reader;

	public Reader(){
        try {
			reader = createReaderFromFile("arquivo.txt");
			String currentLine;
			//itera sobre cada uma das expressoes (linhas do file)
			do {
				currentLine = reader.readLine();
				if(currentLine == null)
					continue;
                
				System.out.println("================================================" );
				System.out.println("Expressao: \n " + currentLine);
				int countOpenParens = currentLine.split("\\(", -1).length;
				int countCloseParens = currentLine.split("\\)", -1).length;
				if(countOpenParens != countCloseParens) {
					System.out.println("Syntax Error");
					continue;
				}
				Tree tree = new Tree();
				tree.addRoot("");
                
                String[] termos = currentLine.split(" ");
				ArrayList<Integer> numbers = new ArrayList<Integer>();
                //ignora o primeiro e o ultimo parenteses
                for(int i = 1; i < termos.length - 1; i++) {
                    switch (termos[i]) {
					case "(":
						tree.addAndMoveCursorToLowerLevel("");
						break;
					case ")":
						tree.returnCursorToUpperLevel();
						break;
					case "+":
					case "-":
					case "*":
					case "/":
					case "^":
						tree.setValueOnCursor(termos[i]);
					break;
					default:
						numbers.add(Integer.parseInt(termos[i]));
						tree.addOperando(termos[i]);
						break;
                    }
                }               
                if(!tree.verificaSeEstaNaRaiz())
                    throw new Exception("Error Running arround the Tree!");
                
                System.out.println("central: \n " + tree.positionsCentral().toString());
                System.out.println("width: \n " + tree.positionsWidth().toString());
                System.out.println("pós: \n " + tree.positionsPos().toString());
                System.out.println("pré: \n " + tree.positionsPre().toString());
                System.out.println("Height:  " + tree.height());
                System.out.println("Result: " + tree.calc());
				System.out.println("Media: " + media(numbers));

                // for(int i = 0; i < numbers.size() ; i++) {
					// System.out.println();
					// System.out.println("sdfjkn");
				// }               

			} while(currentLine != null);
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		catch (IOException e) {
			System.out.println("Error reading the file");
		} catch (Exception ex) {
			System.out.println("Error:" + ex.getMessage());
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public Double media(ArrayList<Integer> m){
		int i;
		Double sum = 0.0;
		for(i = 0; i < m.size(); i++)
			sum += m.get(i);
		return sum / m.size();
	}

	public BufferedReader createReaderFromFile(String nomeArquivo) throws FileNotFoundException {
		File arquivo = new File(nomeArquivo);
		if(arquivo.exists())
			return new BufferedReader(new FileReader(arquivo));
		else
			throw new FileNotFoundException();
	}
}
