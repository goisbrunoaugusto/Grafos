import java.util.*;

public class Q20 {

    enum Cor {
        BRANCO, // Não visitado
        CINZA,  // Visitado e ainda ativo
        PRETO   // Visitado e finalizado
    }

    static class VerticeInfo {
        Cor cor;
        Integer predecessor;
        int tempoDescoberta; // Profundidade de entrada
        int tempoFinalizacao; // Profundidade de saída

        public VerticeInfo() {
            this.cor = Cor.BRANCO;
            this.predecessor = null;
            this.tempoDescoberta = 0;
            this.tempoFinalizacao = 0;
        }

        @Override
        public String toString() {
            return String.format("Cor: %s, Predecessor: %s, d: %d, f: %d",
                                 cor, predecessor, tempoDescoberta, tempoFinalizacao);
        }
    }

    static class Aresta {
        int origem;
        int destino;

        public Aresta(int origem, int destino) {
            this.origem = origem;
            this.destino = destino;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", origem, destino);
        }
    }

    static class ResultadoDFS {
        Map<Integer, VerticeInfo> vertices;
        List<Aresta> arestasArvore;
        List<Aresta> arestasRetorno;
        List<Aresta> arestasAvanco;
        List<Aresta> arestasCruzamento;

        public ResultadoDFS() {
            this.vertices = new HashMap<>();
            this.arestasArvore = new ArrayList<>();
            this.arestasRetorno = new ArrayList<>();
            this.arestasAvanco = new ArrayList<>();
            this.arestasCruzamento = new ArrayList<>();
        }
    }

    private static int tempoEntrada; // Profundidade de entrada
    private static int tempoSaida; // Profundidade de saída

    public static ResultadoDFS executarDFS(Map<Integer, List<Integer>> grafo) {
        ResultadoDFS resultado = new ResultadoDFS();
        
        // Inicialização dos vértices
        for (Integer u : grafo.keySet()) {
            resultado.vertices.put(u, new VerticeInfo());
        }

        tempoEntrada = 0;
        tempoSaida = 0;

        // Loop principal que garante que todos os vértices sejam visitados
        for (Integer u : grafo.keySet()) {
            if (resultado.vertices.get(u).cor == Cor.BRANCO) {
                dfsVisit(u, grafo, resultado);
            }
        }

        return resultado;
    }

    private static void dfsVisit(int u, Map<Integer, List<Integer>> grafo, ResultadoDFS resultado) {
        VerticeInfo uInfo = resultado.vertices.get(u);
        
        // Vértice 'u' foi descoberto
        tempoEntrada++;
        uInfo.tempoDescoberta = tempoEntrada;
        uInfo.cor = Cor.CINZA;

        // Explora os vizinhos de 'u'
        if (grafo.get(u) != null) {
            for (Integer v : grafo.get(u)) {
                VerticeInfo vInfo = resultado.vertices.get(v);
                Aresta aresta = new Aresta(u, v);

                // Classificação da aresta (u, v)
                if (vInfo.cor == Cor.BRANCO) {
                    // ARESTA DE ÁRVORE
                    resultado.arestasArvore.add(aresta);
                    vInfo.predecessor = u;
                    dfsVisit(v, grafo, resultado);
                } else if (vInfo.cor == Cor.CINZA) {
                    // ARESTA DE RETORNO (detecta um ciclo)
                    resultado.arestasRetorno.add(aresta);
                } else { // vInfo.cor == Cor.PRETO
                    if (uInfo.tempoDescoberta < vInfo.tempoDescoberta) {
                        // ARESTA DE AVANÇO
                        resultado.arestasAvanco.add(aresta);
                    } else {
                        // ARESTA DE CRUZAMENTO
                        resultado.arestasCruzamento.add(aresta);
                    }
                }
            }
        }

        // Vértice 'u' foi finalizado
        uInfo.cor = Cor.PRETO;
        tempoSaida++;
        uInfo.tempoFinalizacao = tempoSaida;
    }

    public static void main(String[] args) {
        // Digrafo representado por Lista de Adjacência
        Map<Integer, List<Integer>> digrafo = new HashMap<>();
        // Inicializando o vértice e seus vizinhos (EXEMPLO AULA 8)
        digrafo.put(0, Arrays.asList(1, 2, 7));
        digrafo.put(1, Arrays.asList(3));
        digrafo.put(2, new ArrayList<>());
        digrafo.put(3, Arrays.asList(2, 4));
        digrafo.put(4, Arrays.asList(1, 5));
        digrafo.put(5, Arrays.asList(3));
        digrafo.put(6, Arrays.asList(5, 7));
        digrafo.put(7, Arrays.asList(6));
        digrafo.put(8, Arrays.asList(4, 9));
        digrafo.put(9, new ArrayList<>());


        ResultadoDFS resultado = executarDFS(digrafo);

        // --- Impressão dos Resultados ---
        System.out.println("### Informações dos Vértices (Profundidade de Entrada e Saída) ###");
        List<Integer> verticesOrdenados = new ArrayList<>(resultado.vertices.keySet());
        Collections.sort(verticesOrdenados);
        
        for (Integer v : verticesOrdenados) {
            VerticeInfo info = resultado.vertices.get(v);
            System.out.printf("Vértice %d: Entrada = %d, Saída = %d\n", 
                              v, info.tempoDescoberta, info.tempoFinalizacao);
        }

        System.out.println("\n### Classificação das Arestas ###");
        System.out.println("Arestas de Árvore: " + resultado.arestasArvore);
        System.out.println("Arestas de Retorno: " + resultado.arestasRetorno);
        System.out.println("Arestas de Avanço: " + resultado.arestasAvanco);
        System.out.println("Arestas de Cruzamento: " + resultado.arestasCruzamento);
    }
}