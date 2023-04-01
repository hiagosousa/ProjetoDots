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

        TabuleiroDots tabuleiro = new TabuleiroDots();
        tabuleiro.formata();

        int jogador = 1;
        

        boolean erro, novoPonto, estado = false;
        boolean[] jogadasPossiveis = new boolean[10];

        System.out.println("Digite a posicao da jogada (Jogador " + jogador + "):");
        int jogada = sc.nextInt();
        Coordenada coordenada = tabuleiro.mapear(jogada);
        erro = tabuleiro.jogada(coordenada.linha, coordenada.linha);
        if (erro == false) {
            while (erro == false) {
                jogada = sc.nextInt();
                coordenada = tabuleiro.mapear(jogada);
                erro = tabuleiro.jogada(coordenada.linha, coordenada.linha);
            }
        }
        tabuleiro.imprime();
        tabuleiro.jogada(0, 1);
        
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
        System.out.println("Fim de Jogo!");
    }
    
    public static int max(int a, int b){
        if( a > b){
            return a;
        }
        else if (a < b){
            return b;
        }
        else return a;
    }
    
    public static int min(int a, int b){
        if( a < b){
            return a;
        }
        else if (a > b){
            return b;
        }
        else return a;
    }
    
    public static int logicaDeDecisao(Nos no){
        Coordenada coordenada = TabuleiroDots.mapear(no.valorMinMax);
        if (coordenada.linha >= 5 || coordenada.linha < 0 || coordenada.coluna >= 5 || coordenada.coluna < 0) {
            System.out.println("Local Invalido, tente novamente:");
            return false;

        } else if (no.tabuleiro[coordenada.linha][coordenada.coluna].equals("-") || no.tabuleiro[coordenada.linha][coordenada.coluna].equals("|")) {
            //System.out.println("Local ja ocupado por um traco! Tente novamente:");
            return false;

        }       
        
        
        
        else if (no.tabuleiro[coordenada.linha][coordenada.coluna].equals("*")) {
            System.out.println("Posicao Invalida! Tente novamente:");
            return false;

        } else if (coordenada.linha % 2 != 0 && coordenada.coluna % 2 == 0) {
            no.tabuleiro[coordenada.linha][coordenada.coluna] = "|";
            return true;

        } else if (coordenada.linha % 2 == 0 && coordenada.coluna % 2 != 0) {
            no.tabuleiro[coordenada.linha][coordenada.coluna] = "-";
            return true;

        }
        
        return resultado;
    }
    
    

    public static int minMax(Nos no) {

        if (no.filhos.isEmpty() || no.nivel == 0) {
            System.out.println("Fim do no");
            no.valorMinMax = logicaDeDecisao();
            return no.valorMinMax;
        }
        if (no.jogador == true) {
            no.maximizar = Integer.MIN_VALUE;
            for (Nos filhos : no.filhos) {
                no.nivel--;
                no.jogador = false;
                valorMinMax = minMax(no);
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
                valorMinMax = minMax(no);
                no.minimizar = min(no.minimizar, 1);
                no.beta = min(no.beta, 2);
                if (no.beta <= no.alpha){
                    break;
                }else{
                    
                }
            }
            System.out.println(no.minimizar);
            return no.minimizar;
        }
    }

}
