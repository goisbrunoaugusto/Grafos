package QuestionClasses;

import MainClasses.ConversorGrafo;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q04 implements IQuestionBase {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        ConversorGrafo conversor = null;

        while (true) {
            System.out.println("\n=== CONVERSOR DE GRAFOS ===");
            System.out.println("1. Carregar lista de adjacência e converter para matriz");
            System.out.println("2. Carregar matriz de adjacência e converter para lista");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Digite o caminho do arquivo de lista de adjacência: ");
                        String arquivoLista = scanner.next();
                        System.out.print("MainClasses.Grafo é direcionado? (true/false): ");
                        boolean direcionado = scanner.nextBoolean();

                        conversor = new ConversorGrafo(0);
                        conversor.carregarListaAdjacencia(arquivoLista, direcionado);

                        System.out.println("\nLista de adjacência carregada:");
                        conversor.imprimirListaAdjacencia();

                        conversor.listaParaMatriz();
                        System.out.println("\nConvertido para matriz de adjacência:");
                        conversor.imprimirMatrizAdjacencia();
                        break;

                    case 2:
                        System.out.print("Digite o caminho do arquivo de matriz de adjacência: ");
                        String arquivoMatriz = scanner.next();

                        conversor = new ConversorGrafo(0);
                        conversor.carregarMatrizAdjacencia(arquivoMatriz);

                        System.out.println("\nMatriz de adjacência carregada:");
                        conversor.imprimirMatrizAdjacencia();

                        conversor.matrizParaLista();
                        System.out.println("\nConvertido para lista de adjacência:");
                        conversor.imprimirListaAdjacencia();
                        break;

                    case 3:
                        System.out.println("Encerrando programa...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Erro: Arquivo não encontrado - " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
