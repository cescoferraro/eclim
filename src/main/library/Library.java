package main.library;

import java.util.Random;

public class Library {
	public int[][] matrix = new int[1][4];
	public int[][] filial1 = new int[1][4];
	public int[][] filial2 = new int[1][4];
	public static int[] precos = { 15, 28, 30, 25 };

	public static void main(String... args) {
		System.out.println("Library is instanciated!");
	}

	public static int VendaPorLanche(int lanche, int[][] planilhaVendas) {
		int disponivel = 0;
		for (int i = 0; i < planilhaVendas.length; i++) {
			disponivel = disponivel + planilhaVendas[i][lanche];
		}
		return disponivel;
	}

	public int[][] relatorioGeral() {
		int[][] results = new int[3][4];
		results[0] = this.totlaDeVendas(1);
		results[1] = this.totlaDeVendas(2);
		results[2] = this.totlaDeVendas(3);
		return results;
	}

	public int[] relatorioLanchesAcumulado() {
		int[][] relatorio = this.relatorioGeral();
		int[] results = new int[4];
		results[0] = relatorio[0][0] + relatorio[1][0] + relatorio[2][0];
		results[1] = relatorio[0][1] + relatorio[1][1] + relatorio[2][1];
		results[2] = relatorio[0][2] + relatorio[1][2] + relatorio[2][2];
		results[3] = relatorio[0][3] + relatorio[1][3] + relatorio[2][3];
		return results;
	}

	public int lancheMenosVendido() {
		int[] Acumulado = this.relatorioLanchesAcumulado();
		int min = Math.min(Acumulado[0], Math.min(Acumulado[1], Math.min(Acumulado[2], Acumulado[3])));
		if (min == Acumulado[0])
			return 1;
		if (min == Acumulado[1])
			return 2;
		if (min == Acumulado[2])
			return 3;
		if (min == Acumulado[3])
			return 4;
		return 0;
	}

	public static int TamanhoPedido(int[] pedido) {
		int cont = 0;
		for (int i = 0; i < pedido.length; i++) {
			cont = cont + pedido[i];
		}
		return cont;
	}

	public static int MaiorPedidoValor(int[][] filial) {
		int max = 0;
		for (int i = 0; i < filial.length; i++) {
			int cont = 0;
			for (int j = 0; j < filial[i].length; j++) {
				cont = cont + (filial[i][j] * precos[j]);
			}
			if (cont > max)
				max = cont;
		}
		return max;
	}

	public int ValorMaiorPedidoValorTodas() {
		int max = 0;
		for (int i = 0; i < this.matrix.length; i++) {
			int cont = 0;
			for (int j = 0; j < this.matrix[i].length; j++) {
				cont = cont + (this.matrix[i][j] * precos[j]);
			}
			if (cont > max) {
				max = cont;

			}
		}
		for (int i = 0; i < this.filial1.length; i++) {
			int cont = 0;
			for (int j = 0; j < filial1[i].length; j++) {
				cont = cont + (filial1[i][j] * precos[j]);
			}
			if (cont > max) {
				max = cont;
			}
		}
		for (int i = 0; i < this.filial2.length; i++) {
			int cont = 0;
			for (int j = 0; j < this.filial2[i].length; j++) {
				cont = cont + (this.filial2[i][j] * precos[j]);
			}
			if (cont > max) {
				max = cont;
			}
		}
		return max;
	}

	public int[] MaiorPedidoValorTodas() {
		int max = 0;
		int[] maiorPedido = new int[4];
		for (int i = 0; i < this.matrix.length; i++) {
			int cont = 0;
			for (int j = 0; j < matrix[i].length; j++) {
				cont = cont + (matrix[i][j] * precos[j]);
			}
			if (cont > max) {
				maiorPedido = matrix[i];
				max = cont;

			}
		}
		for (int i = 0; i < this.filial1.length; i++) {
			int cont = 0;
			for (int j = 0; j < filial1[i].length; j++) {
				cont = cont + (filial1[i][j] * precos[j]);
			}
			if (cont > max) {
				maiorPedido = filial1[i];
				max = cont;
			}
		}
		for (int i = 0; i < this.filial2.length; i++) {
			int cont = 0;
			for (int j = 0; j < filial2[i].length; j++) {
				cont = cont + (filial2[i][j] * precos[j]);
			}
			if (cont > max) {
				maiorPedido = filial2[i];
				max = cont;
			}
		}
		return maiorPedido;
	}

	public static int[] MaiorPedido(int[][] filial) {
		int max = 0;
		for (int i = 0; i < filial.length; i++) {
			int cont = 0;
			for (int j = 0; j < filial[i].length; j++) {
				cont = cont + filial[i][j];
			}
			if (cont >= max)
				max = cont;
		}
		int[] hey = new int[4];
		for (int i = 0; i < filial.length; i++) {
			int cont = 0;
			for (int j = 0; j < filial[i].length; j++) {
				cont = cont + filial[i][j];
			}
			if (cont == max)
				return filial[i];
		}
		return hey;

	}

	public int lancheMaisVendido() {
		int[] Acumulado = this.relatorioLanchesAcumulado();
		int min = Math.max(Acumulado[0], Math.max(Acumulado[1], Math.max(Acumulado[2], Acumulado[3])));
		if (min == Acumulado[0])
			return 1;
		if (min == Acumulado[1])
			return 2;
		if (min == Acumulado[2])
			return 3;
		if (min == Acumulado[3])
			return 4;
		return 0;
	}

	public int arecadadoPorLanche(int lanche) {
		int[] Acumulado = this.relatorioLanchesAcumulado();
		int min = Math.max(Acumulado[0], Math.max(Acumulado[1], Math.max(Acumulado[2], Acumulado[3])));
		switch (lanche) {
		case 1:
			return Acumulado[0] * precos[0];
		case 2:
			return Acumulado[1] * precos[1];
		case 3:
			return Acumulado[2] * precos[2];
		case 4:
			return Acumulado[3] * precos[3];
		default:
			return 0;
		}
	}

	public int totalDeVendasEmpresa() {
		int cont = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				cont = cont + matrix[i][j];
			}
		}
		for (int i = 0; i < filial1.length; i++) {
			for (int j = 0; j < filial1[i].length; j++) {
				cont = cont + filial1[i][j];
			}
		}
		for (int i = 0; i < filial2.length; i++) {
			for (int j = 0; j < filial2[i].length; j++) {
				cont = cont + filial2[i][j];
			}
		}
		return cont;
	}

	public int[] totlaDeVendas(int unidade) {
		int[] lanches = new int[4];
		switch (unidade) {
		case 1:
			for (int i = 0; i < 4; i++) {
				lanches[i] = VendaPorLanche(i, this.matrix);
			}
			return lanches;
		case 2:
			for (int i = 0; i < 4; i++) {
				lanches[i] = VendaPorLanche(i, filial1);
			}
			return lanches;
		case 3:
			for (int i = 0; i < 4; i++) {
				lanches[i] = VendaPorLanche(i, filial2);
			}
			return lanches;
		default:
			return lanches;
		}
	}

	public void populate(int total) {
		for (int clientes = 1; clientes < total; clientes++) {
			int filial = RandomRange(1, 3);
			int filialDisponivel = disponiveisFilial(filial);
			int m = disponiveisFilial(1);
			int mm = disponiveisFilial(2);
			int mmm = disponiveisFilial(3);
			int[] pedido = randomPedido();
			int tamanhoPedido = TamanhoPedido(pedido);
			// System.out.printf("%s %s %s\r\n", m, mm, mmm);
			if (tamanhoPedido <= filialDisponivel) {
				insertPedido(filial, pedido);
			}
		}

	}

	public static int RandomRange(int min, int max) {
		Random rand = new Random();
		return min + rand.nextInt((max - min) + 1);
	}

	public int[] randomPedido() {
		int[] pedido = new int[4];
		int qtde = RandomRange(1, 5);
		for (int i = 0; i < pedido.length; i++) {
			if (qtde > 0) {
				int randomQtde = RandomRange(1, qtde);
				pedido[i] = randomQtde;
				qtde = qtde - randomQtde;
			} else {
				pedido[i] = 0;
			}
		}
		return pedido;
	}

	public static int VendasAGORA(int[][] planilhaVendas) {
		int disponivel = 0;
		for (int i = 0; i < planilhaVendas.length; i++) {
			for (int j = 0; j < planilhaVendas[i].length; j++) {
				disponivel = disponivel + planilhaVendas[i][j];
			}
		}
		return disponivel;
	}

	public int disponiveisFilial(int unidade) {
		switch (unidade) {
		case 1:
			return 200 - VendasAGORA(matrix);
		case 2:
			return 100 - VendasAGORA(filial1);
		case 3:
			return 50 - VendasAGORA(filial2);
		default:
			return 0;
		}
	}

	private static int[][] push(int[][] array, int[] push) {
		int[][] longer = new int[array.length + 1][];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				longer[i] = array[i];
			}
		}
		longer[array.length] = push;
		return longer;
	}

	public void insertPedido(int unidade, int[] pedido) {
		switch (unidade) {
		case 1:
			matrix = push(matrix, pedido);
			return;
		case 2:
			filial1 = push(filial1, pedido);
			return;
		case 3:
			filial2 = push(filial2, pedido);
			return;
		default:
			return;
		}
	}

}
