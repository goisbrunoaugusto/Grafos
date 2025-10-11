package MainClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

// Classe principal para representação e manipulação de grafos
// Suporta lista de adjacência, matriz de incidência e algoritmos de busca
public class Grafo {
    private int vertices; // Número de vértices do grafo
    private List<List<Integer>> listaAdjacencia; // Lista de adjacência para representar conexões
    private List<Aresta> listaArestas; // Lista de arestas do grafo
    private int[][] matrizIncidencia; // Matriz de incidência (vértices x arestas)

    // Construtor: inicializa grafo com número de vértices especificado
    public Grafo(int vertices) {
        this.vertices = vertices;
        this.listaAdjacencia = new ArrayList<>();
        this.listaArestas = new ArrayList<>();

        // Cria lista vazia para cada vértice
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

    public void setListaArestas(List<Aresta> listaArestas) {
        this.listaArestas = listaArestas;
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

    // Adiciona aresta direcionada (origem → destino)
    public void adicionarAresta(int origem, int destino) {
        listaArestas.add(new Aresta(origem, destino));
        listaAdjacencia.get(origem).add(destino);
    }

    // Adiciona aresta não direcionada (origem ↔ destino)
    public void adicionarArestaNaoDirecionada(int origem, int destino) {
        listaAdjacencia.get(origem).add(destino);
        listaAdjacencia.get(destino).add(origem);
    }

    // Exibe o grafo em formato de lista de adjacência
    public void imprimirGrafo() {
        System.out.println("Grafo:");
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vértice " + (i+1) + ": ");
            for (Integer vizinho : listaAdjacencia.get(i)) {
                System.out.print(vizinho+1 + " ");
            }
            System.out.println();
        }
    }

    // Carrega grafo a partir de arquivo de texto
    // Formato: primeira linha = número de vértices, demais linhas = arestas
    // (origem,destino)
    public static Grafo lerGrafoDeArquivo(String caminhoArquivo, boolean direcionado) throws FileNotFoundException {
        Scanner arquivo = new Scanner(new File(caminhoArquivo));

        // Lê número de vértices da primeira linha
        int vertices = Integer.parseInt(arquivo.nextLine().trim());
        Grafo grafo = new Grafo(vertices);

        // Lê cada linha de aresta
        while (arquivo.hasNextLine()) {
            String linha = arquivo.nextLine().trim();
            if (linha.isEmpty())
                continue;

            // Divide linha por vírgula (formato: origem,destino)
            String[] partes = linha.split(",+");
            if (partes.length == 2) {
                int origem = Integer.parseInt(partes[0])-1;
                int destino = Integer.parseInt(partes[1])-1;

                // Adiciona aresta baseado no tipo de grafo
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

    public static Grafo LerDiGrafoDeMatrizDeIncidencia(String caminhoArquivo) throws FileNotFoundException{
        Scanner arquivo = new Scanner(new File(caminhoArquivo));

        // Lê número de vértices da primeira linha
        int vertices = Integer.parseInt(arquivo.nextLine().trim());
        List<List<Integer>> adjacenciaList = new ArrayList<>();
        for(int i = 0; i < vertices; i++){
            adjacenciaList.add(new ArrayList<>());
        }

        Grafo grafo = new Grafo(vertices);

        List<Aresta> arestaList = new ArrayList<>();

        int vertice = 0;
        while (arquivo.hasNextLine()) {
            String linha = arquivo.nextLine().trim();
            String[] incidencias = linha.split(" +");

            for(int i = 0; i < incidencias.length; i++){
                if(vertice == 0){
                    arestaList.add(new Aresta(-1,-1));
                }
                if (Integer.parseInt(incidencias[i]) == 1){
                    arestaList.get(i).destino = vertice;
                }else if (Integer.parseInt(incidencias[i]) == -1){
                    arestaList.get(i).origem = vertice;
                }

                if(arestaList.get(i).origem != -1 && arestaList.get(i).destino != -1 && !adjacenciaList.get(arestaList.get(i).origem).contains(arestaList.get(i).destino)){
                    adjacenciaList.get(arestaList.get(i).origem).add(arestaList.get(i).destino);
                }
            }

            vertice++;
        }

        grafo.setListaAdjacencia(adjacenciaList);
        grafo.setListaArestas(arestaList);
        return grafo;
    }

    // Verifica se dois vértices são adjacentes.
    public boolean saoAdjacentes(int v1, int v2) {
        // Validação para garantir que os vértices existem no grafo.
        if (v1 >= 0 && v1 < vertices && v2 >= 0 && v2 < vertices) {
            // Acessa a lista de vizinhos do vértice v1 e verifica se v2 está contido nela.
            return listaAdjacencia.get(v1).contains(v2);
        }
        // Retorna false se um dos vértices for inválido.
        return false;
    }

    // Busca em Profundidade (DFS) - implementação iterativa com pilha
    // Retorna ordem de visita e detecta arestas de retorno (ciclos)
    public List<Integer> BuscaProfundidade(int vertice) {
        int _verticeNaLista = vertice-1;
        Stack<Integer> pilha = new Stack<>();
        ArrayList<Boolean> visitados = new ArrayList<>(Collections.nCopies(listaAdjacencia.size(), false));
        ArrayList<Integer> predecessor = new ArrayList<>(Collections.nCopies(listaAdjacencia.size(), -1));
        List<Integer> ordemVisita = new ArrayList<>();
        List<String> arestasRetorno = new ArrayList<>();
        ArrayList<Boolean> naPilha = new ArrayList<>(Collections.nCopies(listaAdjacencia.size(), false));

        // Marca vértice inicial como visitado e adiciona à pilha
        visitados.set(_verticeNaLista, true);
        pilha.push(_verticeNaLista);
        naPilha.set(_verticeNaLista, true);
        ordemVisita.add(_verticeNaLista);

        // Executa DFS usando pilha
        while (!pilha.isEmpty()) {
            int u = pilha.peek();
            boolean possuiLigacao = false;

            // Procura por vértices não visitados
            for (int verticeLigado : listaAdjacencia.get(u)) {
                if (!visitados.get(verticeLigado)) {
                    // Vértice não visitado: adiciona à pilha
                    possuiLigacao = true;
                    visitados.set(verticeLigado, true);
                    predecessor.set(verticeLigado, u);
                    pilha.push(verticeLigado);
                    naPilha.set(verticeLigado, true);
                    ordemVisita.add(verticeLigado);
                    break;
                } else if (visitados.get(verticeLigado) && naPilha.get(verticeLigado)
                        && predecessor.get(u) != verticeLigado) {
                    // Detecta aresta de retorno (ciclo)
                    if(!arestasRetorno.contains("(" + (u+1) + "," + (verticeLigado+1) + ")")) arestasRetorno.add("(" + (u+1) + "," + (verticeLigado+1) + ")");
                }
            }

            // Se não há mais vértices para visitar, remove da pilha
            if (!possuiLigacao) {
                int removido = pilha.pop();
                naPilha.set(removido, false);
            }
        }

        // Exibe resultado da detecção de ciclos
        if (!arestasRetorno.isEmpty()) {
            System.out.println("Arestas de retorno encontradas: " + arestasRetorno);
        } else {
            System.out.println("Nenhuma aresta de retorno encontrada.");
        }

        return ordemVisita;
    }

    // Gera matriz de incidência (vértices x arestas)
    // -1 = vértice origem, 1 = vértice destino, 0 = não conectado
    public int[][] GerarMatrizIncidencia() {
        matrizIncidencia = new int[vertices][listaArestas.size()];
        for (int i = 0; i < listaArestas.size(); i++) {
            Aresta aresta = listaArestas.get(i);
            matrizIncidencia[aresta.origem][i] = -1;
            matrizIncidencia[aresta.destino][i] = 1;
        }

        return matrizIncidencia;
    }

    // Remove um vértice do grafo, ajustando a lista de adjacências.
    public void removerVertice(int verticeParaRemover) {
        // Validação do vértice
        if (verticeParaRemover < 0 || verticeParaRemover >= vertices) {
            System.out.println("Erro: Vértice " + verticeParaRemover + " não existe no grafo.");
            return;
        }

        // Remove o vértice da lista principal (remove suas arestas de saída)
        listaAdjacencia.remove(verticeParaRemover);
        vertices--;

        // Itera sobre todas as listas restantes para ajustar as arestas
        for (int i = 0; i < listaAdjacencia.size(); i++) {
            List<Integer> arestas = listaAdjacencia.get(i);

            // Remove todas as arestas que apontavam para o vértice removido
            arestas.removeIf(v -> v == verticeParaRemover);

            // Ajusta os índices dos vértices maiores que o removido
            for (int j = 0; j < arestas.size(); j++) {
                int vizinho = arestas.get(j);
                if (vizinho > verticeParaRemover) {
                    arestas.set(j, vizinho - 1);
                }
            }
        }
    }

    // Verifica se o grafo é bipartido usando o algoritmo de coloração com duas
    // cores
    public boolean ehBipartido() {
        // Array para armazenar as cores dos vértices.
        // -1: sem cor, 0: cor A, 1: cor B
        int[] cores = new int[vertices];
        Arrays.fill(cores, -1);

        // Percorre todos os vértices para garantir que grafos desconexos sejam
        // tratados.
        for (int i = 0; i < vertices; i++) {
            // Se o vértice ainda não foi colorido, inicia uma nova busca a partir dele.
            if (cores[i] == -1) {
                Queue<Integer> fila = new LinkedList<>();
                fila.add(i);
                cores[i] = 0; // Primeira cor

                // BFS para colorir vértices alternadamente
                while (!fila.isEmpty()) {
                    int u = fila.poll();

                    for (int vizinho : listaAdjacencia.get(u)) {
                        // Se o vizinho ainda não foi colorido
                        if (cores[vizinho] == -1) {
                            // colore com a cor oposta de u
                            cores[vizinho] = 1 - cores[u];
                            fila.add(vizinho);
                        } else if (cores[vizinho] == cores[u]) {
                            // Conflito de cores: grafo não é bipartido
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    // Realiza uma BFS a partir de um vértice inicial
    public List<Integer> buscaEmLargura(int verticeInicial) {
        List<Integer> ordemVisita = new ArrayList<>();
        boolean[] visitados = new boolean[vertices];
        Queue<Integer> fila = new LinkedList<>();

        // Validação do vértice inicial
        if (verticeInicial < 0 || verticeInicial >= vertices) {
            System.out.println("Erro: Vértice inicial " + verticeInicial + " é inválido.");
            return ordemVisita;
        }

        // Marca vértice inicial e adiciona à fila
        visitados[verticeInicial] = true;
        fila.add(verticeInicial);

        // Executa BFS
        while (!fila.isEmpty()) {
            int u = fila.poll();
            ordemVisita.add(u);

            // Itera sobre os vizinhos do vértice atual 'u'
            for (int vizinho : listaAdjacencia.get(u)) {
                if (!visitados[vizinho]) {
                    visitados[vizinho] = true;
                    fila.add(vizinho);
                }
            }
        }

        return ordemVisita;
    }

    // Exibe matriz de adjacência formatada
    public static void imprimirMatrizAdjacencia(int[][] matriz) {
        System.out.println("Matriz de Adjacência:");
        System.out.print("   ");
        // Cabeçalho com índices das colunas
        for (int i = 0; i < matriz.length; i++) {
            System.out.printf("%3d", i);
        }
        System.out.println();

        // Exibe cada linha da matriz
        for (int i = 0; i < matriz.length; i++) {
            System.out.printf("%2d:", i);
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.printf("%3d", matriz[i][j]);
            }
            System.out.println();
        }
    }
}