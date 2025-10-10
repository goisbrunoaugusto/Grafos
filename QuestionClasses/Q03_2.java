package QuestionClasses;

/*
 * QUESTÃO 03
 * Dada uma matriz de adjacência que representa um grafo direcionado,
 * converte em uma matriz de incidência.
*/
public class Q03_2 implements IQuestionBase {

    /**
     * Converte uma matriz de adjacência de um grafo direcionado para uma matriz de incidência.
     * @param adjacencia A matriz de adjacência (V x V).
     * @return A matriz de incidência (V x E).
     */
    public static int[][] converterParaMatrizIncidencia(int[][] adjacencia) {
        if (adjacencia == null || adjacencia.length == 0) {
            return new int[0][0];
        }

        int numVertices = adjacencia.length;
        int numArestas = 0;

        // Num total de arestas
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (adjacencia[i][j] == 1) {
                    numArestas++;
                }
            }
        }

        if (numArestas == 0) {
            return new int[numVertices][0];
        }

        // Criar a matriz de incidência (V x E)
        int[][] incidencia = new int[numArestas][numVertices];
        int indiceAresta = 0;

        // Preencher a matriz de incidência
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (adjacencia[i][j] == 1) {
                    incidencia[indiceAresta][i] = -1; // origem
                    incidencia[indiceAresta][j] = 1; // destino
                    
                    indiceAresta++;
                }
            }
        }

        return incidencia;
    }


    public static void imprimirMatriz(int[][] matriz) {
        if (matriz == null || matriz.length == 0 || matriz[0].length == 0) {
            System.out.println("[Matriz Vazia]");
            return;
        }
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.printf("%3d ", matriz[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public void execute() {
        // int[][] matrizAdjacencia = {
        //     {0, 0, 0, 0, 0, 0},
        //     {0, 0, 0, 0, 0, 0},
        //     {0, 1, 0, 0, 0, 0},
        //     {1, 1, 1, 0, 0, 0},
        //     {0, 0, 0, 1, 0, 1},
        //     {0, 0, 0, 1, 0, 0}
        // };
        int[][] matrizAdjacencia = {
                {0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 0}
        };

        System.out.println("Matriz de Adjacência (Entrada):");
        imprimirMatriz(matrizAdjacencia);

        int[][] matrizIncidencia = converterParaMatrizIncidencia(matrizAdjacencia);

        System.out.println("\nMatriz de Incidência (Saída):");
        imprimirMatriz(matrizIncidencia);
    }
}