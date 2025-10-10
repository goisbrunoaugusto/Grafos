package QuestionClasses;

import MainClasses.Grafo;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class Q19 implements IQuestionBase{
    @Override
    public void execute() {
        System.out.println("=== Busca em Largura (BFS) em dígrafo ===");
        try {
            Grafo grafo = Grafo.lerGrafoDeArquivo("Dados_trabalho_01/DIGRAFO1.txt", true);

            int verticeInicialNoPrograma = 0;
            int verticeInicialNoArquivo = verticeInicialNoPrograma + 1;

            System.out.println("\nBFS a partir do vértice " + verticeInicialNoArquivo);

            List<Integer> resultado = grafo.buscaEmLargura(verticeInicialNoPrograma);

            List<Integer> resultadoMapeado = resultado.stream().map(v -> v + 1).collect(Collectors.toList());

            System.out.println("Ordem de visita: " + resultadoMapeado);

        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
