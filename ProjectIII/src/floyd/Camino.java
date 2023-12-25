package floyd;

import java.util.ArrayList;

import grafo.Grafo;
import grafo.Nodo;

public class Camino {
	// Declarar variables a utilizar
	private ArrayList<Nodo> caminosMinimos;
	private String llegada;

	/**
	 * Constructor de la clase
	 * 
	 * @param llegada
	 * @param partida
	 * @param caminos
	 */
	public Camino(String llegada, String[] caminos) {
		this.llegada = llegada;
		caminosMinimos = new ArrayList<>();
		for (int i = 0; i < caminos.length; i++) {
			caminosMinimos.add(Grafo.getGrafo().buscarNodo(caminos[i]));
		}
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Nodo> getCaminosMinimos() {
		return caminosMinimos;
	}

	/**
	 * 
	 * @param caminosMinimos
	 */
	public void setCaminosMinimos(ArrayList<Nodo> caminosMinimos) {
		this.caminosMinimos = caminosMinimos;
	}

	/**
	 * 
	 * @return
	 */
	public String getLlegada() {
		return llegada;
	}

	/**
	 * 
	 * @param llegada
	 */
	public void setLlegada(String llegada) {
		this.llegada = llegada;
	}
}