package main.java;

import java.util.Scanner;
import java.sql.*; 

public class Test {
    public static void main(String... args) throws SQLException {
		Connection conexao = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:49161/xe","system","oracle"); 
		System.out.println(conexao);
		conexao.close(); 
		System.out.println("SQL is awesome!");
    }

    public static String AskString(){
		Scanner entrada = new Scanner(System.in);
		String result = entrada.next();
		entrada.close();
		return result; 
    }
}
