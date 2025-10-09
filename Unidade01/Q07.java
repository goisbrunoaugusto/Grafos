import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Q07 {
    public static void main(String[] args) {
        Grafo grafo;
        try {
            System.out.println("=== Função que determina o número total de vértices ===");
            grafo = Grafo.lerGrafoDeArquivo("lista_adjacencia.txt", false);

            System.out.println("\n" + "=".repeat(50));
            System.out.println("Número total de vértices:" + grafo.GetVertexCount());
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao processar arquivo: " + e.getMessage());
        }
    }
}
