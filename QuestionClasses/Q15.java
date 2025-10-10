package QuestionClasses;

import java.util.*;
import java.io.*;

public class Q15 {

    private int tempo;
    private List<Integer>[] adj;
    private int[] pai, desc, lowpt;
    private boolean[] articulacao;
    private int V;

    public void inicializar(int vertices) {
        V = vertices;
        adj = new List[V];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
        pai = new int[V];
        desc = new int[V];
        lowpt = new int[V];
        articulacao = new boolean[V];
        Arrays.fill(pai, -1);
        Arrays.fill(desc, -1);
    }

    public void adicionarAresta(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }

    public void encontrarArticulacoesEBiconectividade() {
        tempo = 0;
        for (int i = 0; i < V; i++) {
            if (desc[i] == -1) {
                dfs(i);
            }
        }

        System.out.println("\nVértices de articulação:");
        boolean encontrou = false;
        for (int i = 0; i < V; i++) {
            if (articulacao[i]) {
                System.out.println("Vértice " + nomeVertice(i));
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum vértice de articulação encontrado.");
    }

    private void dfs(int u) {
        desc[u] = lowpt[u] = ++tempo;
        int filhos = 0;

        for (int v : adj[u]) {
            if (desc[v] == -1) {
                filhos++;
                pai[v] = u;
                dfs(v);
                lowpt[u] = Math.min(lowpt[u], lowpt[v]);

                if (pai[u] == -1 && filhos > 1)
                    articulacao[u] = true;

                if (pai[u] != -1 && lowpt[v] >= desc[u])
                    articulacao[u] = true;

            } else if (v != pai[u]) {
                lowpt[u] = Math.min(lowpt[u], desc[v]);
            }
        }
    }

    public void execute() {
        try {
            Scanner sc = new Scanner(new File("./Dados_trabalho_01/GRAFO_0.txt"));
            List<String[]> arestas = new ArrayList<>();

            if (!sc.hasNext()) {
                System.out.println("Arquivo vazio!");
                return;
            }

            // Lê primeira linha (quantidade de vértices, mas pode ignorar)
            String primeira = sc.nextLine().trim();
            while (primeira.isEmpty()) {
                if (!sc.hasNext()) {
                    System.out.println("Nenhum dado de aresta encontrado!");
                    return;
                }
                primeira = sc.nextLine().trim();
            }

            // lê arestas
            while (sc.hasNextLine()) {
                String linha = sc.nextLine().trim();
                if (linha.isEmpty()) continue;
                arestas.add(linha.split(","));
            }
            sc.close();

            // Descobre o número de vértices automaticamente
            Set<Integer> verticesUsados = new HashSet<>();
            for (String[] par : arestas) {
                verticesUsados.add(converterVertice(par[0]));
                verticesUsados.add(converterVertice(par[1]));
            }

            int numVertices = Collections.max(verticesUsados) + 1;

            Q15 g = new Q15();
            g.inicializar(numVertices);

            for (String[] par : arestas) {
                int u = converterVertice(par[0]);
                int v = converterVertice(par[1]);
                g.adicionarAresta(u, v);
            }

            g.encontrarArticulacoesEBiconectividade();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int converterVertice(String s) {
        s = s.trim();
        if (Character.isLetter(s.charAt(0))) {
            return s.toLowerCase().charAt(0) - 'a';
        } else {
            return Integer.parseInt(s) - 1;
        }
    }

    private static String nomeVertice(int i) {
        if (i < 26) return String.valueOf((char) ('a' + i));
        return String.valueOf(i + 1);
    }
}

