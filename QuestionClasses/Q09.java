package QuestionClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import MainClasses.Grafo;

// Q09 - Adicionar Vértice ao Grafo
// Permite adicionar novos vértices com conexões a grafos existentes (lista ou matriz)
public class Q09 implements IQuestionBase {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
            // Exibe o título da questão
            System.out.println("=== ADICIONAR VÉRTICE AO GRAFO ===");

            // Menu para escolher tipo de grafo
            System.out.println("Escolha o tipo de grafo:");
            System.out.println("1. Lista de adjacência");
            System.out.println("2. Matriz de adjacência");
            System.out.print("Opção: ");

            int tipoGrafo = scanner.nextInt();

            // Solicita caminho do arquivo
            System.out.print("Digite o caminho do arquivo: ");
            String caminhoArquivo = scanner.next();

            // Pergunta se o grafo é direcionado
            System.out.print("Grafo é direcionado? (true/false): ");
            boolean direcionado = scanner.nextBoolean();

            // Carrega e exibe o grafo baseado no tipo escolhido
            if (tipoGrafo == 1) {
                mostrarGrafoLista(caminhoArquivo, direcionado);
            } else if (tipoGrafo == 2) {
                mostrarGrafoMatriz(caminhoArquivo);
            } else {
                System.out.println("Opção inválida!");
                return;
            }

            // Solicita entrada do novo vértice e suas conexões
            System.out.print("\nDigite o novo vértice e suas conexões (formato: vértice conexão1 conexão2 ...): ");
            scanner.nextLine();
            String entrada = scanner.nextLine().trim();
            String[] partes = entrada.split("\\s+");

            // Valida se a entrada tem pelo menos o vértice
            if (partes.length < 1) {
                System.out.println("Entrada inválida!");
                return;
            }

            // Extrai o vértice e suas conexões da entrada
            int novoVertice = Integer.parseInt(partes[0]);
            List<Integer> conexoes = new ArrayList<>();

            for (int i = 1; i < partes.length; i++) {
                conexoes.add(Integer.parseInt(partes[i]));
            }

            // Adiciona o vértice baseado no tipo de grafo
            if (tipoGrafo == 1) {
                adicionarVerticeLista(caminhoArquivo, direcionado, novoVertice, conexoes);
            } else if (tipoGrafo == 2) {
                adicionarVerticeMatriz(caminhoArquivo, direcionado, novoVertice, conexoes);
            }

        } catch (FileNotFoundException e) {
            // Erro quando arquivo não é encontrado
            System.out.println("Erro: Arquivo não encontrado - " + e.getMessage());
        } catch (NumberFormatException e) {
            // Erro quando entrada não é um número válido
            System.out.println("Erro: Formato de número inválido - " + e.getMessage());
        } catch (Exception e) {
            // Outros erros inesperados
            System.out.println("Erro: " + e.getMessage());
        } finally {
            // Fecha o scanner para liberar recursos
            scanner.close();
        }
    }

    // Carrega e exibe grafo em formato de lista de adjacência
    private void mostrarGrafoLista(String caminhoArquivo, boolean direcionado) throws FileNotFoundException {
        Grafo grafo = Grafo.lerGrafoDeArquivo(caminhoArquivo, direcionado);
        System.out.println("\nGrafo carregado (Lista de Adjacência):");
        // grafo.imprimirGrafo();
        imprimirGrafoQ09(grafo);
    }

    // Carrega e exibe grafo em formato de matriz de adjacência
    private void mostrarGrafoMatriz(String caminhoArquivo) throws FileNotFoundException {
        Scanner arquivo = new Scanner(new File(caminhoArquivo));

        // Lê número de vértices da primeira linha
        int vertices = Integer.parseInt(arquivo.nextLine().trim());
        int[][] matrizAdjacencia = new int[vertices][vertices];

        // Lê cada linha da matriz (formato: valores separados por espaço)
        for (int i = 0; i < vertices; i++) {
            String linha = arquivo.nextLine().trim();
            String[] valores = linha.split("\\s+");
            for (int j = 0; j < vertices; j++) {
                matrizAdjacencia[i][j] = Integer.parseInt(valores[j]);
            }
        }
        arquivo.close();

        System.out.println("\nGrafo carregado (Matriz de Adjacência):");
        Grafo.imprimirMatrizAdjacencia(matrizAdjacencia);
    }

    // Adiciona vértice a um grafo representado por lista de adjacência
    private void adicionarVerticeLista(String caminhoArquivo, boolean direcionado, int novoVertice,
            List<Integer> conexoes) throws FileNotFoundException {
        Grafo grafo = Grafo.lerGrafoDeArquivo(caminhoArquivo, direcionado);

        // Expande o grafo se o novo vértice for maior que os existentes
        if (novoVertice >= grafo.getVertices()) {
            expandirGrafo(grafo, novoVertice + 1);
        }

        // Adiciona as conexões do novo vértice
        for (int conexao : conexoes) {
            // Converte para índice 0-based
            int conexaoIndex = conexao - 1;
            if (conexaoIndex >= 0 && conexaoIndex < grafo.getVertices()) {
                if (direcionado) {
                    // Grafo direcionado: apenas uma direção
                    grafo.getListaAdjacencia().get(novoVertice - 1).add(conexaoIndex);
                } else {
                    // Grafo não direcionado: conexão bidirecional
                    grafo.getListaAdjacencia().get(novoVertice - 1).add(conexaoIndex);
                    grafo.getListaAdjacencia().get(conexaoIndex).add(novoVertice - 1);
                }
            }
        }

        System.out.println("\nVértice " + novoVertice + " adicionado com sucesso!");
        System.out.println("Grafo atualizado:");
        // grafo.imprimirGrafo();
        imprimirGrafoQ09(grafo);
    }

    // Adiciona vértice a um grafo representado por matriz de adjacência
    private void adicionarVerticeMatriz(String caminhoArquivo, boolean direcionado, int novoVertice,
            List<Integer> conexoes) throws FileNotFoundException {
        Scanner arquivo = new Scanner(new File(caminhoArquivo));

        // Lê número de vértices e cria matriz
        int vertices = Integer.parseInt(arquivo.nextLine().trim());
        int[][] matrizAdjacencia = new int[vertices][vertices];

        // Carrega a matriz do arquivo
        for (int i = 0; i < vertices; i++) {
            String linha = arquivo.nextLine().trim();
            String[] valores = linha.split("\\s+");
            for (int j = 0; j < vertices; j++) {
                matrizAdjacencia[i][j] = Integer.parseInt(valores[j]);
            }
        }
        arquivo.close();

        // Expande a matriz se necessário
        if (novoVertice >= vertices) {
            int[][] novaMatriz = new int[novoVertice + 1][novoVertice + 1];
            // Copia valores da matriz antiga para a nova
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    novaMatriz[i][j] = matrizAdjacencia[i][j];
                }
            }
            matrizAdjacencia = novaMatriz;
            vertices = novoVertice + 1;
        }

        // Adiciona as conexões na matriz
        for (int conexao : conexoes) {
            if (conexao < vertices) {
                if (direcionado) {
                    // Grafo direcionado: apenas uma direção
                    matrizAdjacencia[novoVertice][conexao] = 1;
                } else {
                    // Grafo não direcionado: conexão bidirecional
                    matrizAdjacencia[novoVertice][conexao] = 1;
                    matrizAdjacencia[conexao][novoVertice] = 1;
                }
            }
        }

        System.out.println("\nVértice " + novoVertice + " adicionado com sucesso!");
        System.out.println("Matriz atualizada:");
        Grafo.imprimirMatrizAdjacencia(matrizAdjacencia);
    }

    // Expande o grafo adicionando novos vértices vazios
    private void expandirGrafo(Grafo grafo, int novoTamanho) {
        int tamanhoAtual = grafo.getVertices();
        grafo.setVertices(novoTamanho);

        // Adiciona listas vazias para os novos vértices
        for (int i = tamanhoAtual; i < novoTamanho; i++) {
            grafo.getListaAdjacencia().add(new ArrayList<>());
        }
    }

    // Imprime o grafo com formatação corrigida (1-based)
    private void imprimirGrafoQ09(Grafo grafo) {
        System.out.println("Grafo:");
        for (int i = 0; i < grafo.getVertices(); i++) {
            System.out.print("Vértice " + (i + 1) + ": ");
            for (Integer vizinho : grafo.getListaAdjacencia().get(i)) {
                System.out.print((vizinho + 1) + " ");
            }
            System.out.println();
        }
    }

}