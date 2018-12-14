/*
 * REFERENCIAS
 * NE. (2010). Apuntes de Algoritmos y Programación.
 * 2018, de jorgep.blogspot.com
 * Sitio web: http://jorgep.blogspot.com/2010/10/ruta-mas-corta-solucion-por-el.html
 */
import java.io.IOException;
public class Prueba {

	public static void main(String[] args) {
		BellmanFord bellman;
		try {
			bellman = new BellmanFord();
			bellman.relajoArista();
	        if (bellman.ciclo()) {
	            for (int i = 0; i < bellman.numeroVertices; i++) {
	                System.out.println("Coste desde " + bellman.nodoOrigen + " a " + (i + 1) + " =>" + bellman.etiquetas[i]);
	            }
	            for (int i = 0; i < bellman.numeroVertices; i++) {
	                System.out.println("El predecesor de " + (i + 1) + " es " + (bellman.predecesor[i] + 1));
	            }
	        } else {
	            System.out.println("Hay un ciclo negativo");
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
