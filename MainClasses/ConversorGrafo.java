package MainClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConversorGrafo {
    private int vertices;
    private List<List<Integer>> listaAdjacencia;
    private int[][] matrizAdjacencia;

    public ConversorGrafo(int vertices) {
        this.vertices = vertices;
        this.listaAdjacencia = new ArrayList<>();
        this.matrizAdjacencia = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            listaAdjacencia.add(new ArrayList<>());
        }
    }

    public void carregarListaAdjacencia(String caminhoArquivo, boolean direcionado) throws FileNotFoundException {
        Scanner arquivo = new Scanner(new File(caminhoArquivo));

        int vertices = Integer.parseInt(arquivo.nextLine().trim());
        this.vertices = vertices;
        this.listaAdjacencia = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            listaAdjacencia.add(new ArrayList<>());
        }

        while (arquivo.hasNextLine()) {
            String linha = arquivo.nextLine().trim();
            if (linha.isEmpty())
                continue;

            String[] partes = linha.split("\\s+");
            if (partes.length == 2) {
                int origem = Integer.parseInt(partes[0]);
                int destino = Integer.parseInt(partes[1]);

                if (direcionado) {
                    listaAdjacencia.get(origem).add(destino);
                } else {
                    listaAdjacencia.get(origem).add(destino);
                    listaAdjacencia.get(destino).add(origem);
                }
            }
        }
        arquivo.close();
    }

    public void carregarMatrizAdjacencia(String caminhoArquivo) throws FileNotFoundException {
        Scanner arquivo = new Scanner(new File(caminhoArquivo));

        int vertices = Integer.parseInt(arquivo.nextLine().trim());
        this.vertices = vertices;
        this.matrizAdjacencia = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            String linha = arquivo.nextLine().trim();
            String[] valores = linha.split("\\s+");
            for (int j = 0; j < vertices; j++) {
                matrizAdjacencia[i][j] = Integer.parseInt(valores[j]);
            }
        }
        arquivo.close();
    }

    public void listaParaMatriz() {
        this.matrizAdjacencia = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            for (int vizinho : listaAdjacencia.get(i)) {
                matrizAdjacencia[i][vizinho] = 1;
            }
        }
    }

    public void matrizParaLista() {
        this.listaAdjacencia = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            listaAdjacencia.add(new ArrayList<>());
        }

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (matrizAdjacencia[i][j] == 1) {
                    listaAdjacencia.get(i).add(j);
                }
            }
        }
    }

    public void imprimirListaAdjacencia() {
        System.out.println("Lista de Adjacência:");
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vértice " + i + ": ");
            for (Integer vizinho : listaAdjacencia.get(i)) {
                System.out.print(vizinho + " ");
            }
            System.out.println();
        }
    }

    public void imprimirMatrizAdjacencia() {
        System.out.println("Matriz de Adjacência:");
        System.out.print("   ");
        for (int i = 0; i < vertices; i++) {
            System.out.printf("%3d", i);
        }
        System.out.println();

        for (int i = 0; i < vertices; i++) {
            System.out.printf("%2d:", i);
            for (int j = 0; j < vertices; j++) {
                System.out.printf("%3d", matrizAdjacencia[i][j]);
            }
            System.out.println();
        }
    }

    public void removerVerticeMatriz(int verticeParaRemover) {
        if (verticeParaRemover < 0 || verticeParaRemover >= vertices) {
            System.out.println("Erro: Vértice " + verticeParaRemover + " não existe na matriz.");
            return;
        }

        int novoNumVertices = vertices - 1;
        int[][] novaMatriz = new int[novoNumVertices][novoNumVertices];

        int novoI = 0;

        for (int i = 0; i < vertices; i++) {
            if (i == verticeParaRemover) {
                continue;
            }

            int novoJ = 0;
            for (int j = 0; j < vertices; j++) {
                if (j == verticeParaRemover) {
                    continue;
                }

                novaMatriz[novoI][novoJ] = matrizAdjacencia[i][j];
                novoJ++;
            }
            novoI++;
        }

        this.matrizAdjacencia = novaMatriz;
        this.vertices = novoNumVertices;
    }
}
