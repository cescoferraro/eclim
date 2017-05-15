package main.java;

import java.util.Scanner;
import java.sql.*; 

public class Test {
	private static Connection ConnectOracle() throws SQLException {
		try {

			Connection conexao = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:49161/xe","system","oracle"); 
			return conexao;
		} finally{
			System.out.println("Java Just Connected to Database");
		}
	}
	private static void CreatePersonTable(Connection connection) throws SQLException{
		try{
			String sql = "CREATE TABLE PESSOAS (cpf VARCHAR(20) NOT NULL, nome VARCHAR(150) NOT NULL, idade NUMBER(3) NULL, endereco VARCHAR(150))";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute(); 
			return;
		} finally {
			System.out.println("Just created the PERSON Table");
		}
	}
	private static void InsertPerson(Connection connection) throws SQLException{
		try{

			String sql = " INSERT INTO PESSOAS (cpf, nome) VALUES ('90345', 'CESCO') ";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute(); 
			return;
		} finally {
			System.out.println("Just Inserted PESSOA into PESSOAS");}
	}
	private static void PrintAll(Connection connection) throws SQLException{
		try{
			String sql = "SELECT *  FROM PESSOAS";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String cpf= rs.getString("cpf");
				System.out.println(cpf);
			}
			rs.close();
			stmt.close(); 
			return;
		} finally {
			System.out.println("Just Printed todas pessoas");
		}
	}

    public static void main(String... args) throws SQLException {
		Connection conexao = ConnectOracle();
		// CreatePersonTable(conexao);
		// InsertPerson(conexao);
		PrintAll(conexao);
		conexao.close(); 
		System.out.println("SQL is awesome!");
    }

}
