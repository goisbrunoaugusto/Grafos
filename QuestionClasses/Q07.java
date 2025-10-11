package QuestionClasses;

import MainClasses.Grafo;

import java.io.FileNotFoundException;


public class Q07 implements IQuestionBase {
    @Override
    public void execute() {
        Grafo grafo;
        try {
            System.out.println("=== Função que determina o número total de vértices ===");
            grafo = Grafo.lerGrafoDeArquivo("Dados_trabalho_01/lista_adjacencia.txt", false);

            System.out.println("Número total de vértices: " + grafo.getVertices());
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao processar arquivo: " + e.getMessage());
        }
    }
}
