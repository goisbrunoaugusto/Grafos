
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Grafo {
    private int vertices;
    private List<List<Integer>> listaAdjacencia;

    public Grafo(int vertices) {
        this.vertices = vertices;
        this.listaAdjacencia = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            listaAdjacencia.add(new ArrayList<>());
        }
    }

    public void adicionarAresta(int origem, int destino) {
        listaAdjacencia.get(origem).add(destino);
    }

    public void adicionarArestaNaoDirecionada(int origem, int destino) {
        listaAdjacencia.get(origem).add(destino);
        listaAdjacencia.get(destino).add(origem);
    }

    public void imprimirGrafo() {
        System.out.println("Lista de Adjacência:");
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vértice " + i + ": ");
            for (Integer vizinho : listaAdjacencia.get(i)) {
                System.out.print(vizinho + " ");
            }
            System.out.println();
        }
    }

    public static Grafo lerGrafoDeArquivo(String caminhoArquivo, boolean direcionado) throws FileNotFoundException {
        Scanner arquivo = new Scanner(new File(caminhoArquivo));

        int vertices = Integer.parseInt(arquivo.nextLine().trim());
        Grafo grafo = new Grafo(vertices);

        while (arquivo.hasNextLine()) {
            String linha = arquivo.nextLine().trim();
            if (linha.isEmpty())
                continue;

            String[] partes = linha.split("\\s+");
            if (partes.length == 2) {
                int origem = Integer.parseInt(partes[0]);
                int destino = Integer.parseInt(partes[1]);

                if (direcionado) {
                    grafo.adicionarAresta(origem, destino);
                } else {
                    grafo.adicionarArestaNaoDirecionada(origem, destino);
                }
            }
        }

        arquivo.close();
        return grafo;
    }
}

public class Q01 {
    public static void main(String[] args) {
        try {
            System.out.println("=== Criação de Grafo com Lista de Adjacência ===");

            Grafo grafo = Grafo.lerGrafoDeArquivo("lista_adjacencia.txt", false);
            System.out.println("Grafo carregado com sucesso!");

            System.out.println("\n" + "=".repeat(50));
            grafo.imprimirGrafo();

        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao processar arquivo: " + e.getMessage());
        }
    }
}