package MainClasses;

public class Aresta {
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
