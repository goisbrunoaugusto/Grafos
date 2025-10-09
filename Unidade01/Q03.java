import java.util.ArrayList;
import java.util.List;

public class Q03 {
    public static void main(String[] args) {
        // Matriz de incidência (Arestas x Vértices)
        int[][] matrizIncidencia = {
            {0, 1, 1, 0, 0, 0},
            {0, 1, 0, 1, 0, 0},
            {1, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 1}
        };

        String grafo = incidenciaParaGrafo(matrizIncidencia);

        System.out.println("### Grafo Gerado ###");
        System.out.println(grafo);
    }

    /**
     * Converte uma matriz de incidência de um grafo não-direcionado
     * @param incidencia A matriz de incidência (Arestas x Vértices).
     * @return Uma String representando o grafo.
     */
    public static String incidenciaParaGrafo(int[][] incidencia) {
        if (incidencia == null || incidencia.length == 0) {
            return "0";
        }

        int numArestas = incidencia.length;
        int numVertices = incidencia[0].length;
        
        StringBuilder sb = new StringBuilder();

        sb.append(numVertices).append("\n");

        // Percorre a matriz de incidência
        for (int i = 0; i < numArestas; i++) {
            List<Integer> verticesDaAresta = new ArrayList<>();
            for (int j = 0; j < numVertices; j++) {
                if (incidencia[i][j] == 1) {
                    verticesDaAresta.add(j);
                }
            }
            
            // Encontra os dois vértices (j) conectados por essa aresta (i)
            if (verticesDaAresta.size() == 2) {
                int idx1 = verticesDaAresta.get(0);
                int idx2 = verticesDaAresta.get(1);

                // Converte índices para caracteres (0 -> 'a', 1 -> 'b', etc.)
                char char1 = (char) ('a' + idx1);
                char char2 = (char) ('a' + idx2);
                
                sb.append(char1).append(",").append(char2).append("\n");
            }
        }

        return sb.toString().trim();
    }
}