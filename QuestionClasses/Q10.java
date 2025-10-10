package QuestionClasses;

import MainClasses.ConversorGrafo;
import MainClasses.Grafo;

import java.io.FileNotFoundException;

public class Q10 implements IQuestionBase {
    @Override
    public void execute() {
        System.out.println("=== Exclusão de um vértice (Lista de Adjacências) ===");
        try {
            Grafo grafo = Grafo.lerGrafoDeArquivo("Dados_trabalho_01/lista_adjacencia.txt", false);
            System.out.println("Grafo Original:");
            grafo.imprimirGrafo();
            System.out.println("-".repeat(50));

            int verticeParaRemover = 3;
            System.out.println("Removendo o vértice " + verticeParaRemover + "...");
            grafo.removerVertice(verticeParaRemover);

            System.out.println("\nGrafo após a remoção:");
            grafo.imprimirGrafo();

        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao processar o grafo: " + e.getMessage());
        }

        System.out.println("\n=== Exclusão de um vértice (Matriz de Adjacências) ===");
        try {
            ConversorGrafo conversor = new ConversorGrafo(0);
            conversor.carregarMatrizAdjacencia("Dados_trabalho_01/matriz_adjacencia.txt");

            System.out.println("Matriz original:");
            conversor.imprimirMatrizAdjacencia();
            System.out.println("-".repeat(50));

            int verticeParaRemover = 3;
            System.out.println("Removendo o vértice " + verticeParaRemover + "...");
            conversor.removerVerticeMatriz(verticeParaRemover);

            System.out.println("\nMatriz após a remoção:");
            conversor.imprimirMatrizAdjacencia();

        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao processar a matriz: " + e.getMessage());
        }
    }
}