/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoDots;

/**
 *
 * @author 201911020023
 */
public class TabuleiroDots {

    private final String[][] tabuleiro;
    public Nos noRaiz;

    public TabuleiroDots() {
        this.tabuleiro = new String[5][5];

    }   

    public int finalizado() {
        int pontuacao1 = 0, pontuacao2 = 0;
        int contador = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (tabuleiro[i][j].equals(" ")) {
                    contador++;
                }
                if (tabuleiro[i][j].equals("1")) {
                    pontuacao1++;
                }
                if (tabuleiro[i][j].equals("2")) {
                    pontuacao2++;
                }
            }
        }
        if (contador == 0) {
            System.out.println("\nPontuacao FINAL:\nJogador 1: " + pontuacao1 + " ponto(s);\nJogador 2: " + pontuacao2 + " ponto(s);\n");
            if (pontuacao1 > pontuacao2) {
                System.out.println("Vencedor:\nJogador 1!");
            }
            if (pontuacao1 < pontuacao2) {
                System.out.println("Vencedor:\nJogador 2!");
            }
            if (pontuacao1 == pontuacao2) {
                System.out.println("Resultado: Empate!");
            }
        } else {
            System.out.println("\nPontuacao atual:\nJogador 1: " + pontuacao1 + " ponto(s);\nJogador 2: " + pontuacao2 + " ponto(s);\n");
        }
        return contador;
    }
}
