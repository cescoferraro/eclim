package main.calc;

public class Calc{

    private double first, second;
    private char operador;

    public void setOperator(char valor) {
        switch (valor) {
            case '+':
                operador = '+';
                break;
            case '-':
                operador = '-';
                break;
            case '*':
                operador = '*';
                break;
            case '/':
                operador = '/';
                break;
            case '^':
                operador = '^';
                break;
        }
    }

    public double calc() throws Exception {
        switch (operador) {
            case '+':
                return soma();
            case '-':
                return subtracao();
            case '*':
                return produto();
            case '/':
                return divisao();
            case '^':
                return potencia();
            default:
                throw new Exception("Operando invalido");
        }
    }

    public void setFirst(double valor1) {
        this.first = valor1;
    }

    public void setSecond(double valor2) {
        this.second = valor2;
    }

    public double soma() {
        return (first + second);
    }

    public double subtracao() {
        return (first - second);
    }

    public double produto() {
        return (first * second);
    }

    public double divisao() {
        return (first / second);
    }

    public double potencia() {
        return Math.pow(first, second);
    }
}
