/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoDots;

import java.util.Scanner;

public class Dots {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int jogada = sc.nextInt();
        boolean jogador = false;
        boolean novoPonto, erro;
        boolean[] jogadasPossiveis = new boolean[12];

        Nos noRaiz = new Nos();
        noRaiz.formata();
        noRaiz.jogada(0, 1);
        jogadasPossiveis[0] = true;

        System.out.println("Digite a posicao da jogada (Jogador 1):");

        Coordenada coordenada = Nos.mapear(jogada);

        erro = noRaiz.jogada(coordenada.linha, coordenada.coluna);
        if (erro == false) {
            while (erro == false) {
                jogada = sc.nextInt();
                coordenada = Nos.mapear(jogada);
                erro = noRaiz.jogada(coordenada.linha, coordenada.coluna);
            }
        }
        jogadasPossiveis[jogada - 1] = true;
        noRaiz.imprime();
        preencheJogadas(jogadasPossiveis, jogador, noRaiz);

        ////
        TabuleiroDots tabuleiro = new TabuleiroDots();
        tabuleiro.formata();

        tabuleiro.jogada(0, 1);

        /*System.out.println("Digite a posicao da jogada (Jogador " + jogador + "):");
        int jogada = sc.nextInt();
        Coordenada coordenada = tabuleiro.mapear(jogada);
        erro = tabuleiro.jogada(coordenada.linha, coordenada.coluna);
        if (erro == false) {
            while (erro == false) {
                jogada = sc.nextInt();
                coordenada = tabuleiro.mapear(jogada);
                erro = tabuleiro.jogada(coordenada.linha, coordenada.coluna);
            }
        }
        tabuleiro.imprime();

        Nos no = new Nos(0, jogadasPossiveis, estado);

        minMax(no);

        //jnnhnhnhnhnh
        while (tabuleiro.finalizado() != 0) {
            System.out.println("Digite a posicao da jogada (Jogador " + jogador + "):");
            jogada = sc.nextInt();
            coordenada = tabuleiro.mapear(jogada);

            erro = tabuleiro.jogada(coordenada.linha, coordenada.linha);
            if (erro == false) {
                while (erro == false) {
                    coordenada.linha = sc.nextInt();
                    coordenada.linha = sc.nextInt();
                    erro = tabuleiro.jogada(coordenada.linha, coordenada.linha);
                }
            }
            novoPonto = tabuleiro.condicao(coordenada.linha, coordenada.linha, erro, jogador);
            tabuleiro.imprime();
            if (novoPonto == true) {
                System.out.println("Ponto! Jogador " + jogador + " joga novamente!");
                tabuleiro.imprime();
            } else {
                switch (jogador) {
                    case 1:
                        jogador = 2;

                        break;
                    case 2:
                        jogador = 1;
                        break;
                    default:
                        jogador = 0;
                        break;
                }
            }
        }
        System.out.println("Fim de Jogo!");*/
    }

    public static void preencheJogadas(boolean[] jogadasPossiveis, boolean jogador, Nos no) {
        for (int i = 0; i < jogadasPossiveis.length; i++) {
            if (jogadasPossiveis[i] == false) {
                jogadasPossiveis[i] = true;
                Nos filho = new Nos(no.tabuleiro);
                Coordenada coordenada = Nos.mapear(i + 1);
                boolean erro = filho.jogada(coordenada.linha, coordenada.coluna);
                no.filhos.add(filho);
                if (!jogador) {
                    filho.valorMinMax = Integer.MIN_VALUE;
                } else {
                    filho.valorMinMax = Integer.MAX_VALUE;
                }

                if (filho.condicao(coordenada.linha, coordenada.coluna, erro, jogador)) {
                    preencheJogadas(jogadasPossiveis, jogador, filho);
                } else {

                    preencheJogadas(jogadasPossiveis, !jogador, filho);

                }
                jogadasPossiveis[i] = false;
            }
        }
    }

    public static int max(int a, int b) {
        if (a > b) {
            return a;
        } else if (a < b) {
            return b;
        } else {
            return a;
        }
    }

    public static int min(int a, int b) {
        if (a < b) {
            return a;
        } else if (a > b) {
            return b;
        } else {
            return a;
        }
    }

    /*public static int logicaDeDecisao(Nos no) {
        int pontuacao = 0;
        Coordenada coordenada = TabuleiroDots.mapear(no.valorMinMax);
        if (no.tabuleiro[coordenada.linha][coordenada.coluna].equals("-") || no.tabuleiro[coordenada.linha][coordenada.coluna].equals("|")) {
            //System.out.println("Local ja ocupado por um traco! Tente novamente:");
            pontuacao--;

        }
        if ((coordenada.linha + 2) < 5 && (coordenada.coluna + 1) < 5 && (coordenada.coluna - 1 >= 0)) {
            if (no.tabuleiro[coordenada.linha][coordenada.coluna].equals("|")) {
                if (no.jogador == true) {
                    pontuacao++;
                } else {
                    pontuacao--;
                }

            }
            if (no.tabuleiro[coordenada.linha][coordenada.coluna - 2].equals("|")) {
                if (no.jogador == true) {
                    pontuacao++;
                } else {
                    pontuacao--;
                }
            }
            if (no.tabuleiro[coordenada.linha - 1][coordenada.coluna - 1].equals("-")) {
                if (no.jogador == true) {
                    pontuacao++;
                } else {
                    pontuacao--;
                }
            }
            if (no.tabuleiro[coordenada.linha + 1][coordenada.coluna - 1].equals("-")) {
                if (no.jogador == true) {
                    pontuacao++;
                } else {
                    pontuacao--;
                }

            }
        }
        if ((coordenada.linha + 2) < 5 && (coordenada.coluna + 1) < 5 && (coordenada.coluna - 1 >= 0)) {
            if (no.tabuleiro[coordenada.linha][coordenada.coluna].equals("|")) {
                if (no.jogador == true) {
                    pontuacao++;
                } else {
                    pontuacao--;
                }

            }
            if (no.tabuleiro[coordenada.linha][coordenada.coluna + 2].equals("|")) {
                if (no.jogador == true) {
                    pontuacao++;
                } else {
                    pontuacao--;
                }
            }
            if (no.tabuleiro[coordenada.linha + 1][coordenada.coluna + 1].equals("-")) {
                if (no.jogador == true) {
                    pontuacao++;
                } else {
                    pontuacao--;
                }
            }
            if (no.tabuleiro[coordenada.linha - 1][coordenada.coluna + 1].equals("-")) {
                if (no.jogador == true) {
                    pontuacao++;
                } else {
                    pontuacao--;
                }
            }
        }
        if ((coordenada.linha + 2) < 5 && (coordenada.coluna + 1) < 5 && (coordenada.coluna - 1 >= 0)) {
            if (no.tabuleiro[coordenada.linha][coordenada.coluna].equals("-")) {
                if (no.jogador == true) {
                    pontuacao++;
                } else {
                    pontuacao--;
                }
            }
            if (no.tabuleiro[coordenada.linha + 2][coordenada.coluna].equals("-")) {
                if (no.jogador == true) {
                    pontuacao++;
                } else {
                    pontuacao--;
                }
            }
            if (no.tabuleiro[coordenada.linha + 1][coordenada.coluna + 1].equals("|")) {
                if (no.jogador == true) {
                    pontuacao++;
                } else {
                    pontuacao--;
                }
            }
            if (no.tabuleiro[coordenada.linha + 1][coordenada.coluna - 1].equals("|")) {
                if (no.jogador == true) {
                    pontuacao++;
                } else {
                    pontuacao--;
                }
            }
        }

        return pontuacao;
    }*/

    /*public static int minMax(Nos no) {

        if (no.filhos.isEmpty() || no.nivel == 0) {
            System.out.println("Fim do no");
            no.valorMinMax = logicaDeDecisao(no);
            return no.valorMinMax;
        }
        if (no.jogador == true) {
            no.maximizar = Integer.MIN_VALUE;
            for (Nos filhos : no.filhos) {
                no.nivel--;
                no.jogador = false;
                no.valorMinMax = minMax(no);
                no.maximizar = max(no.maximizar, 1);
                no.alpha = max(no.alpha, 2);
                if (no.beta <= no.alpha) {
                    break;
                }

                System.out.println("Saí");
            }
            System.out.println(no.maximizar);
            return no.maximizar;
        } else {
            no.minimizar = Integer.MAX_VALUE;
            for (Nos filhos : no.filhos) {
                no.nivel--;
                no.jogador = true;
                no.valorMinMax = minMax(no);
                no.minimizar = min(no.minimizar, 1);
                no.beta = min(no.beta, 2);
                if (no.beta <= no.alpha) {
                    break;
                }
            }
            System.out.println(no.minimizar);
            return no.minimizar;
        }
    }*/

}
