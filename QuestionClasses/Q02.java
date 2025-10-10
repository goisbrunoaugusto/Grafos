package QuestionClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q02 implements IQuestionBase{

    public static int[][] lerMatrizDeArquivo(String nomeArquivo) 
            throws FileNotFoundException, IllegalArgumentException {
        
        File arquivo = new File(nomeArquivo);
        Scanner scanner = new Scanner(arquivo);

        if (!scanner.hasNextInt()) {
            scanner.close();
            throw new IllegalArgumentException("o arquivo não começa com o número de vertices");
        }
        
        int numVertices = scanner.nextInt();
        
        int[][] matriz = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (!scanner.hasNextInt()) {
                    scanner.close();
                    throw new IllegalArgumentException(
                        "faltam elementos na linha " + i
                    );
                }
                matriz[i][j] = scanner.nextInt();
            }
        }
        
        scanner.close();
        return matriz;
    }

    public static void exibirMatrizDeAdjacencia(int[][] matrizAdjacencia) {
        int numVertices = matrizAdjacencia.length;

        if (numVertices == 0) {
            System.out.println("a matriz de adjacência está vazia");
            return;
        }

        System.out.println("\n--- A Matriz de Adjacência (Vértice -> Conexões) ---");

        for (int i = 0; i < numVertices; i++) {
            System.out.print("Vértice " + i + " está ligado a: ");
            boolean temConexao = false;

            for (int j = 0; j < numVertices; j++) {
                if (matrizAdjacencia[i][j] > 0) {
                    System.out.print(j + " ");
                    temConexao = true;
                }
            }
            
            if (!temConexao) {
                System.out.print("[Nenhum]");
            }
            System.out.println(); 
        }
        System.out.println("-------------------------------------------------");
    }

    @Override
    public void execute() {
        final String NOME_DO_ARQUIVO = "Dados_trabalho_01/matriz_adjacencia.txt";

        try {
            int[][] matrizLida = lerMatrizDeArquivo(NOME_DO_ARQUIVO);

            exibirMatrizDeAdjacencia(matrizLida);

        } catch (FileNotFoundException e) {
            System.err.println("\nERRO: o arquivo '" + NOME_DO_ARQUIVO + "' não foi encontrado");
        } catch (IllegalArgumentException e) {
            System.err.println("\nERRO de formato: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\nOcorreu um erro inesperado " + e.getMessage());
        }
    }
}