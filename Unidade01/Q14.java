import java.io.FileNotFoundException;

public class Q14 {
    public static void main(String[] args) {
        Grafo grafo;
        try {
            System.out.println("=== Busca em Profundidade, com determinação de arestas de retorno, a partir de um vértice\n" + "em específico ===");
            grafo = Grafo.lerGrafoDeArquivo("lista_adjacencia.txt", false);

            System.out.println("\n" + "=".repeat(50));

            grafo.BuscaProfundidade(0);
            //todo : "Ordem dos vértices visitados"
            System.out.println("Ordem dos vértices visitados:");
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao processar arquivo: " + e.getMessage());
        }
    }
}
