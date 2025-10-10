package QuestionClasses;

import MainClasses.Grafo;

import java.io.FileNotFoundException;

public class Q06 implements IQuestionBase {
    @Override
    public void execute() {
        System.out.println("=== Verificação se dois vértices são adjacentes ===");
        try {
            Grafo grafo = Grafo.lerGrafoDeArquivo("Dados_trabalho_01/lista_adjacencia.txt", false);
            verificarEImprimir(grafo, 0, 1);
            verificarEImprimir(grafo, 0, 2);
            verificarEImprimir(grafo, 0, 3);
            verificarEImprimir(grafo, 0, 4);
            verificarEImprimir(grafo, 1, 2);
            verificarEImprimir(grafo, 1, 3);
            verificarEImprimir(grafo, 1, 4);
            verificarEImprimir(grafo, 2, 3);
            verificarEImprimir(grafo, 2, 4);
            verificarEImprimir(grafo, 3, 4);

        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao processar o grafo: " + e.getMessage());
        }
    }

    private void verificarEImprimir(Grafo grafo, int v1, int v2) {
        boolean adjacentes = grafo.saoAdjacentes(v1, v2);
        if (adjacentes) {
            System.out.printf("Os vértices %d e %d são adjacentes.\n", v1, v2);
        } else {
            System.out.printf("Os vértices %d e %d não são adjacentes.\n", v1, v2);
        }
    }
}
