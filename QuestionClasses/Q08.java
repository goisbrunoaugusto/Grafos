package QuestionClasses;

import MainClasses.Aresta;

import java.util.ArrayList;
import java.util.List;

public class Q08 implements IQuestionBase {
    @Override
    public void execute() {
        // Matriz de adjacência para um grafo NÃO-DIRECIONADO com 5 vértices.
        // Este grafo tem 6 arestas: (0,1), (0,4), (1,2), (1,3), (2,3), (3,4)
        int[][] matrizAdjacencia = {
            {0, 1, 0, 0, 1},
            {1, 0, 1, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 1, 0, 1},
            {1, 0, 0, 1, 0}
        };

        int totalArestas = contarArestas(matrizAdjacencia);

        System.out.println("Analisando a matriz de adjacência de um grafo não-direcionado...");
        System.out.println("Total de arestas: " + totalArestas);
    }

    /**
     * Determina o número total de arestas de um GRAFO NÃO-DIRECIONADO
     * a partir de sua matriz de adjacência.
     * @param matriz A matriz de adjacência do grafo.
     * @return O número total de arestas.
     */
    public static int contarArestas(int[][] matriz) {
        if (matriz == null || matriz.length == 0) {
            return 0;
        }

        int numeroDeArestas = 0;
        int numVertices = matriz.length;

        // Para um grafo não-direcionado, a matriz é simétrica.
        // Percorremos apenas o triângulo superior da matriz (onde j > i)
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (matriz[i][j] == 1) {
                    numeroDeArestas++;
                }
            }
        }
        
        return numeroDeArestas;
    }
}