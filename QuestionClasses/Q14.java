package QuestionClasses;

import java.io.FileNotFoundException;
import java.util.List;

import MainClasses.Grafo;

// Q14 - Busca em Profundidade (DFS)
// Implementa busca em profundidade com detecção de arestas de retorno
public class Q14 implements IQuestionBase {
    @Override
    public void execute() {
        Grafo grafo;
        try {
            // Exibe o título da questão
            System.out.println(
                    "=== Busca em Profundidade, com determinação de arestas de retorno, a partir de um vértice em específico ===");

            // Carrega o grafo do arquivo (false = grafo não direcionado)
            grafo = Grafo.lerGrafoDeArquivo("Dados_trabalho_01/lista_adjacencia.txt", false);

            // Exibe o grafo carregado para referência
            System.out.println("\nGrafo carregado:");
            grafo.imprimirGrafo();

            // Separador visual
            System.out.println("\n" + "=".repeat(50));
            System.out.println("Iniciando DFS a partir do vértice 1:");

            // Executa a busca em profundidade a partir do vértice 1
            // Retorna a ordem dos vértices visitados e detecta arestas de retorno
            List<Integer> ordemVisita = grafo.BuscaProfundidade(1);

            // Exibe a ordem de visita dos vértices
            System.out.println("\nOrdem dos vértices visitados:");
            for (int i = 0; i < ordemVisita.size(); i++) {
                System.out.print(ordemVisita.get(i)+1);
                if (i < ordemVisita.size() - 1) {
                    System.out.print(" → ");
                }
            }
            System.out.println();

        } catch (FileNotFoundException e) {
            // Erro quando arquivo não é encontrado
            System.out.println("Erro: Arquivo não encontrado - " + e.getMessage());
        } catch (Exception e) {
            // Outros erros durante o processamento
            System.out.println("Erro ao processar arquivo: " + e.getMessage());
        }
    }
}
