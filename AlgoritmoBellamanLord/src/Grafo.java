import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Grafo {
	private int nnodos;
	private int nodos[][][];
	private char nombres[];
	Grafo(int n) {
		this.nnodos = n;
		this.nodos = new int[nnodos][nnodos][2];
		this.nombres = new char[nnodos];
	}
	public void ingresarArco(int n1, int n2, int peso) {
		this.nodos[n1][n2][0] = peso;
		this.nodos[n2][n1][0] = peso;
		this.nodos[n1][n2][1] = n1;
		this.nodos[n2][n1][1] = n2;
	}
	public void ingresarNombre(int nodo, char letra) {
		this.nombres[nodo] = letra;
	}
	public void calcular() {
		int i, j, k;
		for (i = 0; i < this.nnodos; i++) {
			for (j = 0; j < this.nnodos; j++) {
				for (k = 0; k < this.nnodos; k++) {
					if (this.nodos[i][k][0] + this.nodos[k][j][0] < this.nodos[i][j][0]) {
						this.nodos[i][j][0] = this.nodos[i][k][0]
								+ this.nodos[k][j][0];
						this.nodos[i][j][1] = k;
					}
				}
			}
		}
	}
	public int pesominimo(int org, int des) {
		return this.nodos[org][des][0];
	}
	public String caminocorto(int org, int des) {
		String cam;
		if (org == des) {
			cam = "->" + nombres[org];
		} else {
			cam = caminocorto(org, this.nodos[org][des][1]) + "->"
					+ nombres[des];
		}
		return cam;
	}
	public char getNombre(int nodo) {
		return this.nombres[nodo];
	}
	public static void main(String args[]) throws IOException {
		Grafo g;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp;
		int res;
		System.out.println("Entre el numero de nodos del grafo:\n");
		temp = br.readLine();
		res = Integer.parseInt(temp);
		g = new Grafo(res);
		for (int i = 0; i < res; i++) {
			System.out.println("Cual es el nombre del nodo [" + (i + 1)
					+ "]:\n");
			temp = br.readLine();
			g.ingresarNombre(i, temp.charAt(0));
		}
		for (int i = 0; i < res; i++) {
			for (int j = 0; j < res; j++) {
				if (i < j) {
					System.out.println("El nodo " + g.getNombre(i)
							+ " esta conectado con el nodo " + g.getNombre(j)
							+ " (s/n)\n");
					temp = br.readLine();
					if (temp.charAt(0) == 's') {
						int peso;
						System.out.println("Cual es el peso del arco:\n");
						temp = br.readLine();
						peso = Integer.parseInt(temp);
						g.ingresarArco(i, j, peso);
					} else {
						g.ingresarArco(i, j, 10000);
					}
				}
			}
		}
		g.calcular();
		for (int i = 0; i < res; i++) {
			for (int j = 0; j < res; j++) {
				if (i > j) {
					System.out.println("El camino mas corto entre los nodos:"
							+ g.getNombre(i) + "-" + g.getNombre(j) + " es: \n"
							+ g.caminocorto(i, j) + " y su peso es: "
							+ g.pesominimo(i, j));
				}
			}
		}
}
}
