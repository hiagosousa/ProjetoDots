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
    
    public static Coordenada mapear(int posicao){
        switch(posicao){
            case 1:
                return new Coordenada(0,1);
            case 2:
                return new Coordenada(0,3);
            case 3:
                return new Coordenada(1,0);
            case 4:
                return new Coordenada(1,2);
            case 5:
                return new Coordenada(1,4);
            case 6:
                return new Coordenada(2,1);
            case 7:
                return new Coordenada(2,3);
            case 8:
                return new Coordenada(3,0);
            case 9:
                return new Coordenada(3,2);
            case 10:
                return new Coordenada(3,4);
            case 11:
                return new Coordenada(4,1);
            case 12:
                return new Coordenada(4,3);    
                
        }
        return new Coordenada(0,0);
    }

    public void formata() {
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

    public void imprime() {
        System.out.println("\n");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(tabuleiro[i][j]);
            }
            System.out.println();
        }
    }

    public boolean jogada(int jogadaX, int jogadaY) {
        if (jogadaX >= 5 || jogadaX < 0 || jogadaY >= 5 || jogadaY < 0) {
            System.out.println("Local Invalido, tente novamente:");
            return false;

        } else if (tabuleiro[jogadaX][jogadaY].equals("-") || tabuleiro[jogadaX][jogadaY].equals("|")) {
            System.out.println("Local ja ocupado por um traco! Tente novamente:");
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

    public boolean condicao(int jogadaX, int jogadaY, boolean erro, int jogador) {
        int novoQuadrado = 0;
        if (erro == true) {
            if (jogadaX >= 1 && jogadaY >= 2) {
                if ((tabuleiro[jogadaX][jogadaY].equals("|")) && (tabuleiro[jogadaX][jogadaY - 2].equals("|")) && (tabuleiro[jogadaX - 1][jogadaY - 1].equals("-") && tabuleiro[jogadaX + 1][jogadaY - 1].equals("-"))) {
                    switch (jogador) {
                        case 1:
                            tabuleiro[jogadaX][jogadaY - 1] = "1";
                            break;
                        case 2:
                            tabuleiro[jogadaX][jogadaY - 1] = "2";
                            break;
                        default:
                            System.out.println("Ocorreu algum erro!");
                            break;
                    }
                    novoQuadrado++;
                }
            }
            if ((jogadaX + 1) < 5 && (jogadaY + 2) < 5 && (jogadaX - 1 >= 0)) {
                if (tabuleiro[jogadaX][jogadaY].equals("|") && (tabuleiro[jogadaX][jogadaY + 2].equals("|")) && (tabuleiro[jogadaX + 1][jogadaY + 1].equals("-") && tabuleiro[jogadaX - 1][jogadaY + 1].equals("-"))) {
                    switch (jogador) {
                        case 1:
                            tabuleiro[jogadaX][jogadaY + 1] = "1";
                            break;
                        case 2:
                            tabuleiro[jogadaX][jogadaY + 1] = "2";
                            break;
                        default:
                            System.out.println("Ocorreu algum erro!");
                            break;
                    }
                    novoQuadrado++;
                }
            }
            if ((jogadaX + 2) < 5 && (jogadaY + 1) < 5 && (jogadaY - 1 >= 0)) {
                if (tabuleiro[jogadaX][jogadaY].equals("-") && (tabuleiro[jogadaX + 2][jogadaY].equals("-")) && (tabuleiro[jogadaX + 1][jogadaY + 1].equals("|")) && (tabuleiro[jogadaX + 1][jogadaY - 1].equals("|"))) {
                    switch (jogador) {
                        case 1:
                            tabuleiro[jogadaX + 1][jogadaY] = "1";
                            break;
                        case 2:
                            tabuleiro[jogadaX + 1][jogadaY] = "2";
                            break;
                        default:
                            System.out.println("Ocorreu algum erro!");
                            break;
                    }
                    novoQuadrado++;
                }
            }
            if ((jogadaX - 2) >= 0 && (jogadaY - 1) >= 0 && (jogadaY + 1) < 5) {
                if ((tabuleiro[jogadaX][jogadaY].equals("-")) && (tabuleiro[jogadaX - 2][jogadaY].equals("-")) && (tabuleiro[jogadaX - 1][jogadaY + 1].equals("|")) && (tabuleiro[jogadaX - 1][jogadaY - 1].equals("|"))) {
                    switch (jogador) {
                        case 1:
                            tabuleiro[jogadaX - 1][jogadaY] = "1";
                            break;
                        case 2:
                            tabuleiro[jogadaX - 1][jogadaY] = "2";
                            break;
                        default:
                            System.out.println("Ocorreu algum erro!");
                            break;
                    }
                    novoQuadrado++;
                }
            }
            if (novoQuadrado > 0) {
                return true;
            }

        }
        return false;
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
