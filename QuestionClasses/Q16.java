package QuestionClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class Q16 implements IQuestionBase {
    /**
     * Cria uma representação de Lista de Adjacências a partir de uma Matriz de Adjacências.
     * @param matriz A matriz de adjacência do digrafo.
     * @return Digrafo representado por uma Lista de Ajacência.
     */
    public static Map<Integer, List<Integer>> criarListaDeAdjacencias(int[][] matriz) {
        Map<Integer, List<Integer>> listaDeAdjacencias = new HashMap<>();
        int numVertices = matriz.length;

        // Inicializa a lista
        for (int i = 0; i < numVertices; i++) {
            listaDeAdjacencias.put(i, new ArrayList<>());
        }

        // Percorre a matriz de adjacência
        for (int i = 0; i < numVertices; i++) { // 'i' é o vértice de origem
            for (int j = 0; j < numVertices; j++) { // 'j' é o vértice de destino
                // Se matriz[i][j] == 1, existe uma aresta de i -> j
                if (matriz[i][j] == 1) {
                    // 3. Adiciona 'j' à lista de vizinhos de 'i'
                    listaDeAdjacencias.get(i).add(j);
                }
            }
        }

        return listaDeAdjacencias;
    }

    /**
     * Imprime a Lista de Adjacências de forma legível.
     */
    public static void imprimirListaDeAdjacencias(Map<Integer, List<Integer>> lista) {
        List<Integer> vertices = new ArrayList<>(lista.keySet());
        Collections.sort(vertices); // Garante a impressão em ordem

        for (Integer vertice : vertices) {
            System.out.println(vertice + " -> " + lista.get(vertice));
        }
    }

    @Override
    public void execute() {
        // Matriz de adjacência
        int[][] matrizAdjacencia = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 0}
        };

        // Representação do digrafo como uma Lista de Adjacências
        Map<Integer, List<Integer>> listaDeAdjacencias = criarListaDeAdjacencias(matrizAdjacencia);

        System.out.println("### Representação do Digrafo (Lista de Adjacências) ###");
        imprimirListaDeAdjacencias(listaDeAdjacencias);
    }
}