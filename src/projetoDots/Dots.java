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
        int jogada;
        boolean jogador = false;
        boolean novoPonto, erro;
        boolean[] jogadasPossiveis = new boolean[12];
        String[][] tabuleiroFinal = new String[5][5];

        No noRaiz = new No();
        noRaiz.formata();
        noRaiz.jogada(0, 1);
        jogadasPossiveis[0] = true;

        System.out.println("Digite a posicao da jogada (Jogador 1):");
        jogada = sc.nextInt();
        Coordenada coordenada = No.mapear(jogada);

        erro = noRaiz.jogada(coordenada.linha, coordenada.coluna);
        if (erro == false) {
            while (erro == false) {
                jogada = sc.nextInt();
                coordenada = No.mapear(jogada);
                erro = noRaiz.jogada(coordenada.linha, coordenada.coluna);
            }
        }
        jogadasPossiveis[jogada - 1] = true;
        noRaiz.imprime();
        preencheJogadas(jogadasPossiveis, jogador, noRaiz);
        minmax(noRaiz, jogador);
        
        
        
        System.out.println("finalizei");
        ////

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
    
    public void imprimeFinal(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                System.out.println(" " + tabuleiroFinal[i][j]);
            }
            System.out.println("");           
        }
        System.out.println("");
    }
    
    public void fazerAsJogadas(No noRaiz, boolean jogador){
        imprimeFinal();
        
        int ganhador = noRaiz.finalizado();
        if(ganhador != 2){
            System.out.println("Vencedor: "+ ganhador);
            
        }
        else{
            if(!jogador){
                
            }
            
        }
    }

    public static int minmax(No no, boolean jogador) {
        int vencedor = no.finalizado();

        if (vencedor != 2) {
            if (vencedor == 1) {
                return 1;
            } else if (vencedor == -1) {
                return -1;
            } else {
                return 0;
            }
        } else {
            if (jogador) {
                for (int i = 0; i < no.filhos.size(); i++) {
                    int resultado = minmax(no.filhos.get(i), !jogador);
                    if (resultado < no.valorMinMax) {
                        no.valorMinMax = resultado;
                    }

                }
                return no.valorMinMax;
            } else if (!jogador) {
                for (int i = 0; i < no.filhos.size(); i++) {
                    int resultado = minmax(no.filhos.get(i), !jogador);
                    if (resultado > no.valorMinMax) {
                        no.valorMinMax = resultado;
                    }
                }
                return no.valorMinMax;
            }
        }
        return 0;
    }

    public static void preencheJogadas(boolean[] jogadasPossiveis, boolean jogador, No no) {
        for (int i = 0; i < jogadasPossiveis.length; i++) {
            if (jogadasPossiveis[i] == false) {
                jogadasPossiveis[i] = true;
                No filho = new No(no.tabuleiro);
                Coordenada coordenada = no.mapear(i + 1);
                boolean erro = filho.jogada(coordenada.linha, coordenada.coluna);
                no.filhos.add(filho);
                if (!jogador) {
                    filho.valorMinMax = Integer.MAX_VALUE;
                } else {
                    filho.valorMinMax = Integer.MIN_VALUE;
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

    public static int logicaDeDecisao(No no) {
        int pontuacao = 0;
        Coordenada coordenada = no.mapear(no.valorMinMax);
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
    }

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
