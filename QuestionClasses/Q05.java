package QuestionClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Q05 {
    public void execute() {
        String arquivo = "./Dados_trabalho_01/GRAFO_1.txt"; 

        // Estrutura para armazenar a lista de adjacência
        Map<String, Set<String>> grafo = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            // Lê o número de vértices (pode não ser usado diretamente)
            int numVertices = Integer.parseInt(br.readLine().trim());

            // Lê as arestas
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                // Divide a aresta no formato "a,b"
                String[] partes = linha.split(",");
                if (partes.length != 2) continue;

                String v1 = partes[0].trim();
                String v2 = partes[1].trim();

                // Adiciona v1 -> v2
                grafo.putIfAbsent(v1, new HashSet<>());
                grafo.putIfAbsent(v2, new HashSet<>());

                grafo.get(v1).add(v2);
                grafo.get(v2).add(v1); // grafo não orientado
            }

            // Exibe os graus
            System.out.println("Grau de cada vértice:");
            for (String vertice : grafo.keySet()) {
                int grau = grafo.get(vertice).size();
                System.out.println(vertice + " -> " + grau);
            }

            // Verifica se algum vértice não apareceu nas arestas
            if (grafo.size() < numVertices) {
                System.out.println("\nVértices sem arestas (grau 0):");
                // cria letras ou números faltantes
                Set<String> existentes = grafo.keySet();
                for (int i = 1; i <= numVertices; i++) {
                    String nome = String.valueOf(i);
                    if (!existentes.contains(nome)) {
                        System.out.println(nome + " -> 0");
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro: primeira linha deve conter um número (quantidade de vértices)");
        }
    }
}
