package QuestionClasses;

import MainClasses.Grafo;

import java.io.FileNotFoundException;

public class Q01 implements IQuestionBase {
    @Override
    public void execute() {
        try {
            System.out.println("=== Criação de MainClasses.Grafo com Lista de Adjacência ===");

            Grafo grafo = Grafo.lerGrafoDeArquivo("Dados_trabalho_01/lista_adjacencia.txt", false);
            System.out.println("MainClasses.Grafo carregado com sucesso!");

            System.out.println("\n" + "=".repeat(50));
            grafo.imprimirGrafo();

        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao processar arquivo: " + e.getMessage());
        }
    }
}