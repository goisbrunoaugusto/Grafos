package QuestionClasses;

import java.io.FileNotFoundException;
import java.util.Scanner;

import MainClasses.ConversorGrafo;

// Q04 - Conversor de Grafos
// Permite converter entre lista de adjacência e matriz de adjacência
public class Q04 implements IQuestionBase {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        ConversorGrafo conversor = null;

        // Loop principal do menu interativo
        while (true) {
            // Exibe o menu de opções
            System.out.println("\n=== CONVERSOR DE GRAFOS ===");
            System.out.println("1. Carregar lista de adjacência e converter para matriz");
            System.out.println("2. Carregar matriz de adjacência e converter para lista");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            try {
                switch (opcao) {
                    case 1:
                        // Opção 1: Lista → Matriz
                        System.out.print("Digite o caminho do arquivo de lista de adjacência: ");
                        String arquivoLista = scanner.next();
                        System.out.print("MainClasses.Grafo é direcionado? (true/false): ");
                        boolean direcionado = scanner.nextBoolean();

                        // Cria conversor e carrega lista de adjacência
                        conversor = new ConversorGrafo(0);
                        conversor.carregarListaAdjacencia(arquivoLista, direcionado);

                        // Mostra a lista carregada
                        System.out.println("\nLista de adjacência carregada:");
                        conversor.imprimirListaAdjacencia();

                        // Converte para matriz e exibe
                        conversor.listaParaMatriz();
                        System.out.println("\nConvertido para matriz de adjacência:");
                        conversor.imprimirMatrizAdjacencia();
                        break;

                    case 2:
                        // Opção 2: Matriz → Lista
                        System.out.print("Digite o caminho do arquivo de matriz de adjacência: ");
                        String arquivoMatriz = scanner.next();

                        // Cria conversor e carrega matriz de adjacência
                        conversor = new ConversorGrafo(0);
                        conversor.carregarMatrizAdjacencia(arquivoMatriz);

                        // Mostra a matriz carregada
                        System.out.println("\nMatriz de adjacência carregada:");
                        conversor.imprimirMatrizAdjacencia();

                        // Converte para lista e exibe
                        conversor.matrizParaLista();
                        System.out.println("\nConvertido para lista de adjacência:");
                        conversor.imprimirListaAdjacencia();
                        break;

                    case 3:
                        // Opção 3: Sair do programa
                        System.out.println("Encerrando programa...");
                        scanner.close();
                        return;

                    default:
                        // Opção inválida
                        System.out.println("Opção inválida!");
                }
            } catch (FileNotFoundException e) {
                // Erro quando arquivo não é encontrado
                System.out.println("Erro: Arquivo não encontrado - " + e.getMessage());
            } catch (Exception e) {
                // Outros erros (formatação, etc.)
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
