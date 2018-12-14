import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;
public class BellmanFord {
	LinkedList<Aristas> aristas;
    float etiquetas[];
    int predecesor[];
    int numeroVertices, totalAristas, nodoOrigen;
    final int INFINITY = 999;
 
    private static class Aristas {
 
        int origen, destino;
        float coste;
 
        public Aristas(int a, int b, float c) {
            origen = a;
            destino = b;
            coste = c;
        }
 
        @Override
        public String toString() {
            return "Aristas{" + "origen=" + origen + ", destino=" + destino + ", coste=" + coste + '}';
        }
    }
 
    public BellmanFord() throws IOException {
        float item;
        aristas = new LinkedList<Aristas>();
        DataInputStream in = new DataInputStream(System.in);
        System.out.print("Introduce numero de vertices ");
        numeroVertices = Integer.parseInt(in.readLine());
        System.out.println("Matriz de costes");
        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < numeroVertices; j++) {
                if (i != j) {
                    System.out.println("Introduce coste del nodo " + (i + 1) + " al nodo " + (j + 1));
                    item = Float.parseFloat(in.readLine());
                    if (item != 0) {
                        aristas.add(new Aristas(i, j, item));
                    }
                }
            }
        }
        totalAristas = aristas.size();
        etiquetas = new float[numeroVertices];
        predecesor = new int[numeroVertices];
        System.out.print("Introduce el vertice origen");
        nodoOrigen = Integer.parseInt(in.readLine()) - 1;
    }
 
    public void relajoArista() {
        int i, j;
        for (i = 0; i < numeroVertices; ++i) {
            etiquetas[i] = INFINITY;
        }
        etiquetas[nodoOrigen] = 0;
        for (i = 0; i < numeroVertices - 1; ++i) {
            for (j = 0; j < totalAristas; ++j) {
                System.out.println(aristas.get(j));
                if (etiquetas[aristas.get(j).origen] + aristas.get(j).coste < etiquetas[aristas.get(j).destino]) {
                    etiquetas[aristas.get(j).destino] = etiquetas[aristas.get(j).origen] + aristas.get(j).coste;
                    predecesor[aristas.get(j).destino] = aristas.get(j).origen;
                }
            }
            for (int p = 0; etiquetas.length < p; p++) {
                System.out.println("\t" + etiquetas[p]);
            }
        }
    }
 
    public boolean ciclo() {
        int j;
        for (j = 0; j < totalAristas; ++j) {
            if (etiquetas[aristas.get(j).origen] + aristas.get(j).coste < etiquetas[aristas.get(j).destino]) {
                return false;
            }
        }
        return true;
}
}
