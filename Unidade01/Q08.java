public class Q08 {

    public static void main(String[] args) {
        String grafo = 
            "8\n" +
            "a,b\n" +
            "b,c\n" +
            "b,d\n" +
            "c,d\n" +
            "c,e\n" +
            "e,f\n" +
            "f,g\n" +
            "f,h\n" +
            "g,h";

        int totalArestas = contarArestas(grafo);

        System.out.println("Total de arestas: " + totalArestas);
    }

    /**
     * Determina o número total de arestas de um grafo definido por uma string
     * no formato Edge List.
     * @param definicaoGrafo A string que contém a definição do grafo.
     * @return O número total de arestas.
     */
    public static int contarArestas(String definicaoGrafo) {
        if (definicaoGrafo == null || definicaoGrafo.trim().isEmpty()) {
            return 0;
        }

        // Remover espaços em branco no início ou fim
        // Dividir a string em linhas.
        String[] linhas = definicaoGrafo.trim().split("\n");

        if (linhas.length <= 1) {
            return 0;
        }

        // O número de arestas é o total de linhas menos a primeira linha (que é o número de vértices).
        int numeroDeArestas = linhas.length - 1;
        
        return numeroDeArestas;
    }
}