package QuestionClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Q11 {

    public void execute() {
        String arquivo = "./Dados_trabalho_01/GRAFO_1.txt"; // arquivo com o grafo

        Map<String, Set<String>> grafo = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            // número de vértices
            int numVertices = Integer.parseInt(br.readLine().trim());

            // Lê as arestas
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                String[] partes = linha.split(",");
                if (partes.length != 2) continue;

                String v1 = partes[0].trim();
                String v2 = partes[1].trim();

                grafo.putIfAbsent(v1, new HashSet<>());
                grafo.putIfAbsent(v2, new HashSet<>());

                grafo.get(v1).add(v2);
                grafo.get(v2).add(v1); // grafo não orientado
            }

            // Caso o arquivo não tenha todas as letras/números explicitamente, cria nós isolados
            if (grafo.size() < numVertices) {
                System.out.println("Há vértices sem arestas");
            }

            // Escolhe um vértice inicial (o primeiro da lista)
            String inicial = grafo.keySet().iterator().next();

            // Faz uma busca (DFS ou BFS)
            Set<String> visitados = new HashSet<>();
            dfs(grafo, inicial, visitados);

            // Verifica se todos foram visitados
            boolean conexo = visitados.size() == grafo.size();

            System.out.println("O grafo é conexo? " + (conexo ? "Sim" : "Não"));
            System.out.println("\nVértices visitados: " + visitados);

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Erro: o grafo está vazio ou mal formatado");
        }
    }

    // Busca em profundidade (DFS)
    private static void dfs(Map<String, Set<String>> grafo, String atual, Set<String> visitados) {
        visitados.add(atual);
        for (String vizinho : grafo.getOrDefault(atual, new HashSet<>())) {
            if (!visitados.contains(vizinho)) {
                dfs(grafo, vizinho, visitados);
            }
        }
    }
}
