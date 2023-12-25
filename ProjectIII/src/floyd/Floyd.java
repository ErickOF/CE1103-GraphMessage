package floyd;

import grafo.Grafo;

public class Floyd {
	// Declarar variables a utilizar
	private String caminoRecorrido;
	private String cadena;
	private String[][] matrizCaminos;
	private Grafo grafo = Grafo.getGrafo();
	private int[][] mCostos;
	private float temp1;
	private float temp2;
	private float temp3;
	private float temp4;
	private float minimo;
	private static Floyd floyd;

	/**
	 * Constructor de la clase
	 * 
	 * @param mCostos
	 */
	private Floyd(int[][] mCostos) {
		this.mCostos = mCostos;
		caminosMinimos();
		mostrar();
	}

	/**
	 * 
	 * @param mCostos
	 * @return
	 */
	public static Floyd getFloyd(int[][] mCostos) {
		if (floyd == null) {
			floyd = new Floyd(mCostos);
		}
		return floyd;
	}

	/**
	 * Metodo que calcula los caminos minimos
	 * 
	 * @return
	 */
	private void caminosMinimos() {
		// Saber cuantos vertices tiene la matriz
		int vertices = this.mCostos.length;
		int matrizAdyacencia[][] = this.mCostos;
		// Caminos para irse entre los veritices
		String caminos[][] = new String[vertices][vertices];
		String caminosAux[][] = new String[vertices][vertices];
		matrizCaminos = new String[vertices][vertices];
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				caminos[i][j] = "";
				caminosAux[i][j] = "";
				matrizCaminos[i][j] = "";
			}
		}
		for (int k = 0; k < vertices; k++) {
			for (int i = 0; i < vertices; i++) {
				for (int j = 0; j < vertices; j++) {
					temp1 = matrizAdyacencia[i][j];
					temp2 = matrizAdyacencia[i][k];
					temp3 = matrizAdyacencia[k][j];
					temp4 = temp2 + temp3;
					// Encontrando al minimo
					minimo = Math.min(temp1, temp4);
					if (temp1 != temp4) {
						if (minimo == temp4) {
							caminoRecorrido = "";
							caminosAux[i][j] = k + "";
							caminos[i][j] += caminosRecursivos(i, k, caminosAux, caminoRecorrido)
									+ grafo.getListaNodos().get(k).getNickname();
						}
					}
					matrizAdyacencia[i][j] = (int) minimo;
				}
			}
		}
		// Agregando el camino a cadena
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				cadena += "[" + matrizAdyacencia[i][j] + "]";
			}
			cadena += "\n";
		}
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				if (matrizAdyacencia[i][j] != Grafo.INFINITO) {
					if (i != j) {
						if (caminos[i][j].equals("")) {
							matrizCaminos[i][j] += grafo.getListaNodos().get(i).getNickname().toString() + ","
									+ grafo.getListaNodos().get(j).getNickname().toString();
						} else {
							matrizCaminos[i][j] += grafo.getListaNodos().get(i).getNickname().toString() + ","
									+ caminos[i][j] + "," + grafo.getListaNodos().get(j).getNickname().toString();
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param i
	 * @param k
	 * @param caminosAux
	 * @param caminoRecorrido
	 * @return
	 */
	private String caminosRecursivos(int i, int k, String[][] caminosAux, String caminoRecorrido) {
		if (caminosAux[i][k] == "") {
			return "";
		} else {
			// Recursividad
			k = Integer.parseInt(caminosAux[i][k].toString());
			return caminoRecorrido += caminosRecursivos(i, k, caminosAux, caminoRecorrido)
					+ grafo.getListaNodos().get(k).getNickname() + ",";

		}
	}

	private void mostrar() {
		for (int i = 0; i < matrizCaminos.length; i++) {
			for (int j = 0; j < matrizCaminos[0].length; j++) {
				System.out.println(matrizCaminos[i][j]);
			}
		}
	}

	public String[][] getCaminos() {
		return matrizCaminos;
	}
}