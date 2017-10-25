package main.app;

import main.uno.Uno;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.io.IOException;

public class App {
	public static void main(String[] args) {
		Uno uno = new Uno();
		Path path1 = Paths.get("exemplo.txt");
		try(Scanner sc=new Scanner(Files.newBufferedReader(path1,Charset.defaultCharset())))
			{
				sc.useDelimiter("[;\n]"); // separadores: ; e nova linha
				while (sc.hasNext()) {
					String s = sc.next().replaceAll("\n", "");
					s = s.replaceAll("\r", "");
					s = s.replaceAll("\t", "");
					uno.handleCommand(s);
				}
			} catch (IOException x) {
			System.err.format("Erro de E/S: %s%n", x);
		}
		System.out.println(uno.top());
	}
}
