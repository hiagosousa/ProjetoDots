/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoDots;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author 201911020023
 */
public class Nos {

    public String[][] tabuleiro;
    public ArrayList<Nos> filhos;
    public int maximizar, minimizar;
    public int valorMinMax, nivel;
    public boolean jogador;
    int alpha, beta;
    Random gerador;

    public Nos(int i, boolean[] jogadasPossiveis, boolean estado) {
        this.tabuleiro = new String[5][5];
        this.valorMinMax = 0;
        this.maximizar = 0;
        this.minimizar = 0;
        this.nivel = 9;
        this.alpha = Integer.MIN_VALUE;
        this.beta = Integer.MAX_VALUE;
        this.filhos = new ArrayList();
        this.jogador = estado;
        this.gerador = new Random();

        formataIA();
        preencheJogadas(jogadasPossiveis, estado, tabuleiro);

    }

    public final void preencheJogadas(boolean[] jogadasPossiveis, boolean estado, String tabuleiro[][]) {
        for (int i = 0; i < jogadasPossiveis.length; i++) {
            if (jogadasPossiveis[i] == false) {
                jogadasPossiveis[i] = true;
                int randResult = 0;
                randResult = gerador.nextInt(2, 12);
                Coordenada coordenada = (TabuleiroDots.mapear(randResult));
                boolean erro = jogadaIA(coordenada.linha, coordenada.coluna);
                if (erro == false) {
                    while (erro == false) {
                        randResult = gerador.nextInt(2, 12);
                        coordenada = (TabuleiroDots.mapear(randResult));
                        erro = jogadaIA(coordenada.linha, coordenada.coluna);
                    }
                }
                //System.out.println("Posicao " + randResult);
                filhos.add(new Nos(i, jogadasPossiveis, estado));
                jogadasPossiveis[i] = false;
            }
        }
    }

    public void formataIA() {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    tabuleiro[i][j] = "*";
                } else if (j % 2 != 0) {
                    tabuleiro[i][j] = " ";
                } else {
                    tabuleiro[i][j] = " ";
                }
            }
        }
    }

    public boolean jogadaIA(int jogadaX, int jogadaY) {
        if (jogadaX >= 5 || jogadaX < 0 || jogadaY >= 5 || jogadaY < 0) {
            System.out.println("Local Invalido, tente novamente:");
            return false;

        } else if (tabuleiro[jogadaX][jogadaY].equals("-") || tabuleiro[jogadaX][jogadaY].equals("|")) {
            //System.out.println("Local ja ocupado por um traco! Tente novamente:");
            return false;

        } else if (tabuleiro[jogadaX][jogadaY].equals("*")) {
            System.out.println("Posicao Invalida! Tente novamente:");
            return false;

        } else if (jogadaX % 2 != 0 && jogadaY % 2 == 0) {
            tabuleiro[jogadaX][jogadaY] = "|";
            return true;

        } else if (jogadaX % 2 == 0 && jogadaY % 2 != 0) {
            tabuleiro[jogadaX][jogadaY] = "-";
            return true;

        }
        System.out.println("Local Inexistente no jogo. Digite outro:");
        return false;
    }
}
