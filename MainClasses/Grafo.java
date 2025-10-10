package MainClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Grafo {
    private int vertices;
    private List<List<Integer>> listaAdjacencia;
    private List<Aresta> listaArestas;
    private int[][] matrizIncidencia;

    public Grafo(int vertices) {
        this.vertices = vertices;
        this.listaAdjacencia = new ArrayList<>();
        this.listaArestas = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            listaAdjacencia.add(new ArrayList<>());
        }
    }

    public int getVertices() {
        return vertices;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public List<List<Integer>> getListaAdjacencia() {
        return listaAdjacencia;
    }

    public void setListaAdjacencia(List<List<Integer>> listaAdjacencia) {
        this.listaAdjacencia = listaAdjacencia;
    }


    public List<Aresta> getListaArestas() {
        return listaArestas;
    }

    public int[][] getMatrizIncidencia() {
        return matrizIncidencia;
    }

    public void setMatrizIncidencia(int[][] matrizIncidencia) {
        this.matrizIncidencia = matrizIncidencia;
    }

    public void adicionarAresta(int origem, int destino) {
        listaArestas.add(new Aresta(origem, destino));
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

            String[] partes = linha.split(",+");
            if (partes.length == 2) {
                int origem = Integer.parseInt(partes[0])-1;
                int destino = Integer.parseInt(partes[1])-1;

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

    public int[][] GerarMatrizIncidencia() {
        matrizIncidencia = new int[vertices][listaArestas.size()];
        for (int i = 0; i < listaArestas.size(); i++){
            Aresta aresta = listaArestas.get(i);
            matrizIncidencia[aresta.origem][i] = -1;
            matrizIncidencia[aresta.destino][i] = 1;
        }

        return matrizIncidencia;
    }
}