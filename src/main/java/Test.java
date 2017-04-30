package main.java;

import java.util.Scanner;

public class Test {
    public static void main(String... args) {
	System.out.println("Eclim is awesome!");
    }

    public static String AskString(){
	Scanner entrada = new Scanner(System.in);
	String result = entrada.next();
	entrada.close();
	return result; 
    }
}
