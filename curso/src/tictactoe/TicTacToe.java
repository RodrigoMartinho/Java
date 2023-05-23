package tictactoe;
import java.util.Scanner;

public class TicTacToe {
    public static char[][] tabuleiro = new char[3][3];
    public static Scanner scanner = new Scanner(System.in);
    public static char letra;

    public static void main(String[] args) {
        iniciarJogo();

        mostrarTabuleiro(tabuleiro);

        solicitarJogada();
    }

    public static void iniciarJogo() {

        for (int posicao = 0; posicao < 9; posicao++) {
            tabuleiro[posicao / 3][posicao % 3] = ' ';
        }
        letra = 'X';
    }

    public static void mostrarTabuleiro(char[][] texto) {
        System.out.println("-".repeat(9));

        for(int j = 0; j < texto.length; j++) {
            System.out.print("| ");
            for(int i = 0; i < texto[j].length; i++) {
                System.out.print(texto[j][i] + " ");
            }
            System.out.println("|");
        }

        System.out.println("-".repeat(9));
    }

    public static void solicitarJogada() {

        //Validar se digitou número, e não letras
        int linha = scanner.nextInt();
        int coluna = scanner.nextInt();

        //Validar número de linha/coluna
        if ((linha < 1 || linha > 3) || (coluna < 1 || coluna > 3)) {
            System.out.println("Coordinates should be from 1 to 3!");

            //Se ainda tiver jogadas disponíveis, solicitar a localizacao
            if (contarJogadas(' ') > 0) solicitarJogada();
        } else {
            if (tabuleiro[linha - 1][coluna - 1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                solicitarJogada();
            } else { //preenche com X a localização passada
                //tabuleiro[linha - 1][coluna - 1] = 'X';
                tabuleiro[linha - 1][coluna - 1] = letra;

                letra = (letra == 'X') ? 'O' : 'X';

                mostrarTabuleiro(tabuleiro);

                String resultado = analisar(tabuleiro);

                if (resultado.equals("")) {
                    if (contarJogadas(' ') > 0) solicitarJogada();
                } else {
                    System.out.println(resultado);
                }

            }
        }
    }

    public static Integer contarJogadas(char letra) {
        int qtd = 0;

        for(int j = 0; j < tabuleiro.length; j++) {
            for(int i = 0; i < tabuleiro[j].length; i++) {
                if (tabuleiro[j][i] == letra) {
                  qtd++;
                }
            }
        }

        return qtd;
    }

    public static String analisar(char[][] texto) {

        int qtdX = 0;
        int qtdO = 0;
        int qtdBrancos = 0;

        for(int j = 0; j < texto.length; j++) {
            for(int i = 0; i < texto[j].length; i++) {
                if (texto[j][i] == 'X') {
                    qtdX++;
                } else if (texto[j][i] == 'O') {
                    qtdO++;
                } else {
                    qtdBrancos++;
                }
            }
        }

        String[] testes = new String[8];

        //Horizontal
        testes[0] = texto[0][0] + String.valueOf(texto[0][1]) + texto[0][2];
        testes[1] = texto[1][0] + String.valueOf(texto[1][1]) + texto[1][2];
        testes[2] = texto[2][0] + String.valueOf(texto[2][1]) + texto[2][2];
        //Vertical
        testes[3] = texto[0][0] + String.valueOf(texto[1][0]) + texto[2][0];
        testes[4] = texto[0][1] + String.valueOf(texto[1][1]) + texto[2][1];
        testes[5] = texto[0][2] + String.valueOf(texto[1][2]) + texto[2][2];
        //Diagonais
        testes[6] = texto[0][0] + String.valueOf(texto[1][1]) + texto[2][2];
        testes[7] = texto[0][2] + String.valueOf(texto[1][1]) + texto[2][0];

        boolean xWin = false, oWin = false;

        for (int x = 0; x < testes.length; x++) {
            if (testes[x].equals("XXX") ) {
                xWin = true;
            }

            if (testes[x].equals("OOO")) {
                oWin = true;
            }
        }

        if (xWin) {
            return "X wins";
        } else if (oWin) {
            return "O wins";
        } else if (qtdBrancos > 0) {
            return "";
        } else {
            return "Draw";
        }
    }
}