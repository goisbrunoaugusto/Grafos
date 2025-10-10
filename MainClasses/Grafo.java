package MainClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Grafo {
    private int vertices;

    public List<List<Integer>> getListaAdjacencia() {
        return listaAdjacencia;
    }

    public void setListaAdjacencia(List<List<Integer>> listaAdjacencia) {
        this.listaAdjacencia = listaAdjacencia;
    }

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

    public boolean saoAdjacentes(int v1, int v2) {
        if (v1 >= 0 && v1 < vertices && v2 >= 0 && v2 < vertices) {
            return listaAdjacencia.get(v1).contains(v2);
        }

        return false;
    }

    //todo : "Arestas de retorno"
    public void BuscaProfundidade(int vertice){
        Stack<Integer> pilha = new Stack<>();
        ArrayList<Boolean> visitados = new ArrayList<>(Collections.nCopies(listaAdjacencia.size(), false));
        ArrayList<Integer> predecessor = new ArrayList<>(Collections.nCopies(listaAdjacencia.size(), null));

        visitados.set(vertice, true);
        pilha.add(vertice);
        while(!pilha.isEmpty()){
            int u = pilha.peek();
            boolean possuiLigacao = false;
            for(int verticeLigado : listaAdjacencia.get(u)){
                if(!visitados.get(verticeLigado)){
                    possuiLigacao = true;

                    visitados.set(verticeLigado, true);
                    predecessor.set(verticeLigado, u);
                    pilha.push(verticeLigado);
                }
            }
            if(!possuiLigacao ){
                pilha.pop();
            }
        }
    }

    public int GetVertexCount(){
        return vertices;
    }
}