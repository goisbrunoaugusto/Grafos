package QuestionClasses;

import java.io.FileNotFoundException;

import MainClasses.Grafo;

// Q01 - Criação de Grafo com Lista de Adjacência
// Demonstra como carregar um grafo a partir de arquivo e exibir suas conexões
public class Q01 implements IQuestionBase {

    @Override
    public void execute() {
        try {
            // Exibe o título da questão
            System.out.println("=== Criação de MainClasses.Grafo com Lista de Adjacência ===");

            // Carrega o grafo do arquivo (false = grafo não direcionado)
            // O arquivo tem formato: primeira linha = número de vértices, demais linhas =
            // arestas (origem,destino)
            Grafo grafo = Grafo.lerGrafoDeArquivo("Dados_trabalho_01/lista_adjacencia.txt", false);

            // Confirma que o carregamento foi bem-sucedido
            System.out.println("MainClasses.Grafo carregado com sucesso!");

            // Separador visual
            System.out.println("\n" + "=".repeat(50));

            // Exibe o grafo: mostra cada vértice e suas conexões
            // Formato: "Vértice X: conexão1 conexão2 ..."
            imprimirGrafoQ09(grafo);

        } catch (FileNotFoundException e) {
            // Erro quando o arquivo não existe ou não pode ser acessado
            System.out.println("Erro: Arquivo não encontrado - " + e.getMessage());
        } catch (Exception e) {
            // Outros erros (formatação, memória, etc.)
            System.out.println("Erro ao processar arquivo: " + e.getMessage());
        }
    }

    // Imprime o grafo com formatação corrigida (1-based)
    private void imprimirGrafoQ09(Grafo grafo) {
        System.out.println("Grafo:");
        for (int i = 0; i < grafo.getVertices(); i++) {
            System.out.print("Vértice " + (i + 1) + ": ");
            for (Integer vizinho : grafo.getListaAdjacencia().get(i)) {
                System.out.print((vizinho + 1) + " ");
            }
            System.out.println();
        }
    }
}