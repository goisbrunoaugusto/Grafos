package QuestionClasses;

import MainClasses.Grafo;

import java.io.FileNotFoundException;

public class Q12 implements IQuestionBase {
    @Override
    public void execute() {
        System.out.println("=== Verificação de Grafo Bipartido ===");

        // Teste grafo não bipartido
        System.out.println("\n--- Testando o grafo de 'lista_adjacencia.txt' ---");
        try {
            Grafo grafoNaoBipartido = Grafo.lerGrafoDeArquivo("Dados_trabalho_01/lista_adjacencia.txt", false);

            if (grafoNaoBipartido.ehBipartido()) {
                System.out.println("O grafo é bipartido.");
            } else {
                System.out.println("O grafo não é bipartido.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado - " + e.getMessage());
        }

        System.out.println("\n" + "-".repeat(50));

        // Teste grafo bipartido
        System.out.println("\n--- Testando um grafo bipartido de exemplo ---");
        Grafo grafoBipartido = new Grafo(4);
        grafoBipartido.adicionarArestaNaoDirecionada(0, 1);
        grafoBipartido.adicionarArestaNaoDirecionada(1, 2);
        grafoBipartido.adicionarArestaNaoDirecionada(2, 3);
        grafoBipartido.adicionarArestaNaoDirecionada(3, 0);
        grafoBipartido.imprimirGrafo();

        if (grafoBipartido.ehBipartido()) {
            System.out.println("O grafo é bipartido.");
        } else {
            System.out.println("O grafo não é bipartido.");
        }
    }
}
