Esse código possui três classes: Dots, que possui o código relacionado ao jogo e ao minMax; No, que é relacionado aos nós e à árvore; e Coordenada, que traduz a posição escolhida pelo jogador e IA para coordenadas de linha e coluna.

Originalmente, a heurística desse código seria o uso do MinMax com podas, além de, ao invés de -1, 0, ou 1, serem definidos pesos através de um sistema de avaliação para incentivar as jogadas da IA de acordo com o "maior peso".
No fim, o código com essa heurística teve falhas, portanto foi utilizado o MinMax padrão. O código relacionado à heurística original está comentado nas classes correspondentes.
Com relação ao jogo, a IA está realizando decisões por conta própria através do MinMax padrão, porém o estado do jogo não é atualizado por turno.

Os problemas encontrados no código foram presentes especialmente no uso do MinMax, pois o código dele tinha a tendência de imprimir apenas zeros, e com relação à atualização do estado do tabuleiro quando presente em uma recursão.