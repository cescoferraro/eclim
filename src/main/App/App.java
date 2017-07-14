package main.App;

import java.awt.print.Printable;
import java.sql.*;
import java.util.Random;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;

import java.util.Scanner;

public class App {
	private static Connection ConnectOracle() throws SQLException {
		try {

			Connection conexao = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "system",
					"oracle");
			return conexao;
		} finally {
			System.out.println("Java Just Connected to Database");
		}
	}

	private static void CreatePersonTable(Connection connection) throws SQLException {
		try {
			String sql = "CREATE TABLE PESSOAS (cpf VARCHAR(20) NOT NULL, nome VARCHAR(150) NOT NULL, idade NUMBER(3) NULL, endereco VARCHAR(150))";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			return;
		} finally {
			System.out.println("Just created the PERSON Table");
		}
	}

	private static void InsertReserva(Connection connection) throws SQLException {

		Scanner entrada = new Scanner(System.in);
		System.out.println("Insira a matricula do funcionario");
		int matricula = entrada.nextInt();
		System.out.println("Insira o identificador do equipamento");
		int identificador = entrada.nextInt();
		try {
			String sql = "insert into RESERVAS (PROTOCOLO,MATRICULA,IDENTIFICADOR,DATA_INICIO,DATA_TERMINO) values ( ?,?,?,?,? )";
			PreparedStatement stmt = connection.prepareStatement(sql);
			Random sorte = new Random();
			Fairy fairy = Fairy.create();
			Person person = fairy.person();
			stmt.setInt(1, sorte.nextInt(10000));
			stmt.setInt(2, matricula);
			stmt.setInt(3, identificador);
			stmt.setDate(4, new java.sql.Date(System.currentTimeMillis()));

			stmt.setDate(5, new java.sql.Date(System.currentTimeMillis() + 4 * 60 * 60 * 24 * 1000));
			stmt.execute();
			return;
		} finally {
			System.out.printf("Reserva realizado para o equipamento %s e funcionario %s \n", identificador, matricula);
		}
	}

	private static void InsertEquipamento(Connection connection) throws SQLException {
		Random sorte = new Random();
		int identificador = sorte.nextInt(10000);
		try {
			String sql = " insert into EQUIPAMENTO (IDENTIFICADOR,DATA_AQUISICAO,DESCRICAO,CUSTO_DIARIA) VALUES (?,?,?,?) ";
			PreparedStatement stmt = connection.prepareStatement(sql);
			Fairy fairy = Fairy.create();
			Person person = fairy.person();
			stmt.setInt(1, identificador);
			stmt.setDate(2, new java.sql.Date(System.currentTimeMillis() - 72 * 60 * 60 * 24 * 1000));
			stmt.setString(3, person.getFirstName());
			stmt.setInt(4, sorte.nextInt(1000));
			stmt.execute();
			return;
		} finally {
			System.out.printf("Equipamento inserido com identificador %s\n", identificador);
		}
	}

	private static void InsertFuncionario(Connection connection) throws SQLException {

		Random sorte = new Random();
		int matricula = sorte.nextInt(1000);
		try {
			String sql = "insert into FUNCIONARIO (MATRICULA,SENHA,NASCIMENTO,DATA_ADMISSAO,SEXO,NOME,ENDERECO,SALARIO) VALUES ( ?, ?, ?, ?, ?,?, ?, ? )";
			PreparedStatement stmt = connection.prepareStatement(sql);
			Fairy fairy = Fairy.create();
			Person person = fairy.person();
			stmt.setInt(1, matricula);
			stmt.setString(2, person.getPassportNumber());
			stmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));

			stmt.setDate(4, new java.sql.Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000));
			String[] sex = { "M", "F" };
			stmt.setString(5, sex[new Random().nextInt(sex.length)]);

			stmt.setString(6, person.getFirstName());
			stmt.setString(7, person.getAddress().getStreet());
			stmt.setInt(8, sorte.nextInt(10000));
			stmt.execute();
			return;
		} finally {
			System.out.printf("Funcionario inserido com matricula %s\n", matricula);
		}
	}

	private static void GetFuncionario(int matricula, Connection connection) throws SQLException {

		System.out.println("*** GetFuncionario");
		try {
			String sql = "SELECT * FROM FUNCIONARIO WHERE MATRICULA = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, matricula);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("==============");
				String NOME = rs.getString("NOME");
				System.out.println("NOME:" + NOME);
				String cpf = rs.getString("MATRICULA");
				System.out.println("Matricula:" + cpf);
				String senha = rs.getString("SENHA");
				System.out.println("SENHA:" + senha);
				String SEXO = rs.getString("SEXO");
				System.out.println("SEXO:" + SEXO);
				String ADMISSAO = rs.getString("DATA_ADMISSAO");
				System.out.println("ADMISSAO:" + ADMISSAO);
				String NASCIMENTO = rs.getString("NASCIMENTO");
				System.out.println("NASCIMENTO:" + NASCIMENTO);
				String ENDERECO = rs.getString("ENDERECO");
				System.out.println("ENDERECO:" + ENDERECO);
				String SALARIO = rs.getString("SALARIO");
				System.out.println("SALARIO:" + SALARIO);
			}
			rs.close();
			stmt.close();
			return;
		} finally {
			System.out.println("Just Printed todas pessoas");
		}
	}

	private static void printFuncionarios(Connection connection) throws SQLException {
		try {
			String sql = "SELECT *  FROM FUNCIONARIO ";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("==============");
				String NOME = rs.getString("NOME");
				System.out.println("NOME:" + NOME);
				String cpf = rs.getString("MATRICULA");
				System.out.println("Matricula:" + cpf);
				String senha = rs.getString("SENHA");
				System.out.println("SENHA:" + senha);
				String SEXO = rs.getString("SEXO");
				System.out.println("SEXO:" + SEXO);
				String ADMISSAO = rs.getString("DATA_ADMISSAO");
				System.out.println("ADMISSAO:" + ADMISSAO);
				String NASCIMENTO = rs.getString("NASCIMENTO");
				System.out.println("NASCIMENTO:" + NASCIMENTO);
				String ENDERECO = rs.getString("ENDERECO");
				System.out.println("ENDERECO:" + ENDERECO);
				String SALARIO = rs.getString("SALARIO");
				System.out.println("SALARIO:" + SALARIO);
			}
			rs.close();
			stmt.close();
			return;
		} finally {
			System.out.println("Just Printed todas pessoas");
		}
	}

	private static void printEquipamentos(Connection connection) throws SQLException {
		try {
			String sql = "SELECT *  FROM EQUIPAMENTO";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("==============");
				String IDENTIFICADOR = rs.getString("IDENTIFICADOR");
				System.out.println("IDENTIFICADOR:" + IDENTIFICADOR);
				String DATA_AQUISICAO = rs.getString("DATA_AQUISICAO");
				System.out.println("DATA_AQUISICAO:" + DATA_AQUISICAO);
				String DESCRICAO = rs.getString("DESCRICAO");
				System.out.println("DESCRICAO:" + DESCRICAO);
				String CUSTO_DIARIA = rs.getString("CUSTO_DIARIA");
				System.out.println("CUSTO_DIARIA:" + CUSTO_DIARIA);

			}
			rs.close();
			stmt.close();
			return;
		} finally {
			System.out.println("==============");
			System.out.println("Just Printed todas equipamentos");
		}
	}

	private static void custoFuncionarios(Connection connection) throws SQLException {
		try {
			String sql = " SELECT distinct FUNCIONARIO.MATRICULA TTTT , COUNT(FUNCIONARIO.MATRICULA) TTT, SUM(EQUIPAMENTO.CUSTO_DIARIA *( RESERVAS.DATA_TERMINO - RESERVAS.DATA_INICIO)) TT FROM FUNCIONARIO INNER JOIN RESERVAS ON FUNCIONARIO.MATRICULA = RESERVAS.MATRICULA INNER JOIN EQUIPAMENTO ON RESERVAS.IDENTIFICADOR = EQUIPAMENTO.IDENTIFICADOR GROUP BY FUNCIONARIO.MATRICULA ORDER BY TT DESC";

			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("==============");
				String IDENTIFICADOR = rs.getString("TTTT");
				System.out.println("MATRICULA:" + IDENTIFICADOR);
				String RESERVAS = rs.getString("TTT");
				System.out.println("RESERVAS:" + RESERVAS);
				String SOMA = rs.getString("TT");
				System.out.println("CUSTO TOTAL:" + SOMA);
			}
			rs.close();
			stmt.close();
			return;
		} finally {
			System.out.println("==============");
			System.out.println("Relatório Custo Reservas Concluído");
			System.out.println("==============");
		}
	}

	private static void reservaMaisComprida(Connection connection) throws SQLException {
		try {
			String sql = " SELECT IDENTIFICADOR FROM RESERVAS WHERE ( RESERVAS.DATA_TERMINO - RESERVAS.DATA_INICIO) = (SELECT MAX ( RESERVAS.DATA_TERMINO - RESERVAS.DATA_INICIO) FROM RESERVAS) ";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("==============");
				String IDENTIFICADOR = rs.getString("IDENTIFICADOR");
				System.out.println("IDENTIFICADOR:" + IDENTIFICADOR);
			}
			rs.close();
			stmt.close();
			return;
		} finally {
			System.out.println("==============");
			System.out.println("Relatório Custo Reservas Concluído");
			System.out.println("==============");
		}
	}

	private static void custoResevas(Connection connection) throws SQLException {
		try {
			String sql = " SELECT RESERVAS.IDENTIFICADOR , Count(*) TTTT, SUM(( RESERVAS.DATA_TERMINO - RESERVAS.DATA_INICIO)) TTT, SUM(EQUIPAMENTO.CUSTO_DIARIA *( RESERVAS.DATA_TERMINO - RESERVAS.DATA_INICIO)) TT FROM RESERVAS INNER JOIN EQUIPAMENTO ON RESERVAS.IDENTIFICADOR = EQUIPAMENTO.IDENTIFICADOR group by RESERVAS.IDENTIFICADOR, EQUIPAMENTO.CUSTO_DIARIA";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("==============");
				String IDENTIFICADOR = rs.getString("IDENTIFICADOR");
				System.out.println("IDENTIFICADOR:" + IDENTIFICADOR);
				String RESERVAS = rs.getString("TTTT");
				System.out.println("RESERVAS: " + RESERVAS);
				String DIARIAS = rs.getString("TTT");
				System.out.println("DIARIAS: " + DIARIAS);
				String SOMA = rs.getString("TT");
				System.out.println("CUSTO TOTAL:" + SOMA);
			}
			rs.close();
			stmt.close();
			return;
		} finally {
			System.out.println("==============");
			System.out.println("Relatório Custo Reservas Concluído");
			System.out.println("==============");
		}
	}

	private static void printRelatorio(Connection connection) throws SQLException {
		try {
			String sql = "SELECT * FROM RESERVAS INNER JOIN FUNCIONARIO ON RESERVAS.MATRICULA = FUNCIONARIO.MATRICULA INNER JOIN EQUIPAMENTO ON EQUIPAMENTO.IDENTIFICADOR = RESERVAS.IDENTIFICADOR Where DATA_INICIO >= sysdate -1";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("==============");
				String MATRICULA = rs.getString("MATRICULA");
				System.out.println("MATRICULA:" + MATRICULA);
				String IDENTIFICADOR = rs.getString("IDENTIFICADOR");
				System.out.println("IDENTIFICADOR:" + IDENTIFICADOR);
				String DATA_INICIO = rs.getString("DATA_INICIO");
				System.out.println("DATA_INICIO:" + DATA_INICIO);
				String DATA_TERMINO = rs.getString("DATA_TERMINO");
				System.out.println("DATA_TERMINO:" + DATA_TERMINO);
			}
			rs.close();
			stmt.close();
			return;
		} finally {
			System.out.println("==============");
			System.out.println("Relatório Concluído");
			System.out.println("==============");
		}
	}

	private static void handleCommand(int command, Connection connection) throws SQLException {
		System.out.println(command);
		switch (command) {
		case 1:
			printFuncionarios(connection);
			break;
		case 2:
			InsertFuncionario(connection);
			break;
		case 3:
			printEquipamentos(connection);
			break;
		case 4:
			InsertEquipamento(connection);
			break;
		case 5:
			InsertReserva(connection);
			break;
		case 6:
			printRelatorio(connection);
			break;
		case 7:
			custoResevas(connection);
			break;
		case 8:
			custoFuncionarios(connection);
			break;
		case 9:
			reservaMaisComprida(connection);
			break;
		}
	}

	private static void menu() {
		System.out.println("=================");
		System.out.println("Escolha a opção desejada:");
		System.out.println("1 ) Lista funcionarios");
		System.out.println("2 ) Insert random funcionarios");
		System.out.println("3 ) Lista equipamentos");
		System.out.println("4 ) Insert random equipamentos");
		System.out.println("5 ) Insert reservas");
		System.out.println("6 ) Imprimir relatorio reservas");
		System.out.println("7 ) Imprimir relatorio reservas custo por equipamento");
		System.out.println("8 ) Imprimir relatorio reservas custo por funcionario");
		System.out.println("9 ) Reserva mais comprida");
		System.out.println("0 ) Sair");
		System.out.println("=================");
	}

	public static void main(String... args) throws SQLException {
		Connection conexao = ConnectOracle();
		// CreatePersonTable(conexao);
		// InsertPerson(conexao);
		// conexao.close();
		Scanner entrada = new Scanner(System.in);
		menu();
		int command = entrada.nextInt();
		while (command != 0) {
			handleCommand(command, conexao);
			menu();
			command = entrada.nextInt();
		}

		// Random sorte = new Random();
		// int matricula = sorte.nextInt(10000);
		// InsertFuncionario(conexao);
		// printFuncionarios(conexao);
		// GetFuncionario(7420, conexao);
	}

}
