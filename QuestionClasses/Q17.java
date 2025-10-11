package QuestionClasses;

import MainClasses.Aresta;
import MainClasses.ConversorGrafo;
import MainClasses.Grafo;

import java.io.FileNotFoundException;
import java.util.List;

public class Q17 implements IQuestionBase{

    @Override
    public void execute() {
        Grafo grafo;
        try {
            System.out.println("=== Função que gera digrafo apartir de uma matriz de incidência ===");
            grafo = Grafo.LerDiGrafoDeMatrizDeIncidencia("Dados_trabalho_01/matriz_incidencia_direcionado");
            grafo.imprimirGrafo();

            System.out.println("=== Função que imprime a matriz de incidência ===");

            System.out.println("Total de arestas: " + grafo.getListaArestas().size());
            int[][] matrizIncidencia = grafo.GerarMatrizIncidencia();


            System.out.println("Matriz de Incidência:");
            System.out.print("   ");
            for (int i = 0; i < matrizIncidencia[0].length; i++) {
                System.out.printf("%3d", i+1);
            }
            System.out.println();

            for (int i = 0; i < matrizIncidencia.length; i++) {
                System.out.printf("%2d:", i+1);
                for (int j = 0; j < matrizIncidencia[0].length; j++) {
                    System.out.printf("%3d", matrizIncidencia[i][j]);
                }
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao processar arquivo: " + e.getMessage());
        }
    }
}
