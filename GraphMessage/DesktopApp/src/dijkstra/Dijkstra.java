package dijkstra;

import java.util.ArrayList;

import grafo.Enlace;
import grafo.Grafo;
import grafo.Nodo;

/**
 * Clase donde se aplica el Dijkstra
 * 
 * @author Erick
 *
 */
public class Dijkstra {
	// Variables a utilizar
	private int matrizDeCostos[][];
	private String matrizDePaso[][];
	private Grafo grafo;
	// CONSTANTES a utilizar
	private static final int INFINITO = 999999999;
	private static final int NO_EVALUA = 900000000;

	/**
	 * 
	 */
	public Dijkstra() {
		this.grafo = Grafo.getGrafo();
		// Llenar matriz la matriz de adyacencia
		iniciarMatrizDeCostos();
		iniciarMatrizDePaso();
	}

	private void iniciarMatrizDeCostos() {
		matrizDeCostos = new int[grafo.getTamaño()][grafo.getTamaño()];
	}

	private void iniciarMatrizDePaso() {
		matrizDePaso = new String[grafo.getTamaño()][grafo.getTamaño()];
	}

	/**
	 * 
	 * @param nPartida
	 */
	public void caminosMinimos(String nPartida) {
		ArrayList<Nodo> listaNodos = ordenar(grafo.getListaNodos(), grafo.buscarNodo(nPartida));
		for (int i = 0; i < grafo.getTamaño(); i++) {
			Nodo inicio = listaNodos.get(i);
			ArrayList<Enlace> adyacentesInicio = inicio.getListaNodoAdyacente();
			for (int j = 0; j < grafo.getTamaño(); j++) {
				if (matrizDeCostos[j][i] != NO_EVALUA) {
					Nodo nEvaluado = listaNodos.get(j);
					if (isPath(listaNodos, nEvaluado)) {
						matrizDeCostos[j][i] = adyacentesInicio.get(j).getArista().getPeso();
						matrizDePaso[j][i] = nEvaluado.getNickname().toString();
					} else {
						matrizDeCostos[j][i] = INFINITO;
						matrizDePaso[j][i] = nEvaluado.getNickname().toString();
					}
				}
			}
			int filaMin = getMin(matrizDeCostos, i);
			for (int columna = 0; columna < matrizDeCostos[0].length; columna++) {
				if (columna > i) {
					matrizDeCostos[filaMin][columna] = NO_EVALUA;
					matrizDePaso[filaMin][columna] = listaNodos.get(columna).getNickname().toString();
				}
			}
		}
		// grafo.buscarNodo(nPartida).setMatrizDeCostos(matrizDeCostos);
		// grafo.buscarNodo(nPartida).setMatrizDePaso(matrizDePaso);
	}

	/**
	 * Metodo que pone al nodo de partida de primero
	 * 
	 * @param listaNodos
	 * @param nPartida
	 * @return
	 */
	private ArrayList<Nodo> ordenar(ArrayList<Nodo> listaNodos, Nodo nPartida) {
		// Lista donde se van a guardar los nuevos nodos
		ArrayList<Nodo> lista = new ArrayList<>();
		// Agregar de primero al nodo de partida
		lista.add(nPartida);
		// Recorrer la lista de nodos
		for (int i = 0; i < listaNodos.size(); i++) {
			// Temporal que guarda el nodo
			Nodo temp = listaNodos.get(i);
			// Si el nodo es diferente del de partida se agrega a la lista
			if (!nPartida.getNickname().equals(temp.getNickname())) {
				lista.add(temp);
			}

		} // Retornar la nueva lista
		return lista;
	}

	/**
	 * Metodo que verifica si el nodo tiene camino a otro
	 * 
	 * @param listaNodos
	 * @param aBuscar
	 * @return
	 */
	private boolean isPath(ArrayList<Nodo> listaNodos, Nodo aBuscar) {
		// Recorrer la lista de nodos
		for (Nodo nodo : listaNodos) {
			// Si el nodo tiene enlace con el de partida
			if (nodo.getNickname().equals(aBuscar.getNickname())) {
				// Se retorna true
				return true;
			}
		} // Sino se retorna false
		return false;
	}

	/**
	 * Metodo que se encarga de encontrar el camino minimo de un nodo a otro
	 * nodo
	 * 
	 * @param matrizDeCostos
	 * @param indice
	 * @return
	 */
	private int getMin(int[][] matrizDeCostos, int indice) {
		// Indice de la fila con el menor valor
		int filaMin = 0;
		// Recorrer las filas para encontrar el valor minimo
		for (int fila = 1; fila < matrizDeCostos.length; fila++) {
			// Si el menor encontrado hasta el momento es mayor que otro
			if (matrizDeCostos[filaMin][indice] > matrizDeCostos[fila][indice]) {
				// Se reasigna el indice al menor encontrado
				filaMin = fila;
			}
		} // Se retorna la fila minima
		return filaMin;
	}
	/**
	 * @Ale1-Nathy
	 * @Erick3->
	 * @Fran4->Erick
	 * @Mari2->Fran
	 * @Nathy5->Sebas
	 * @Sebas6->
	 */
}