package grafo;

/**
 * Clase que sencarga de realizar la union entre una arista y un nodo
 * 
 * @author Erick
 */
public class Enlace {
	// Declarar variables necesarias
	private Arista arista;
	private Nodo nodo;

	/**
	 * Constructor de la clase Arista
	 */
	public Enlace(Arista arista, Nodo nodo) {
		this.arista = arista;
		this.nodo = nodo;
	}

	// Getters y Setters

	/**
	 * Obtener la arista a la que se enlazo
	 * 
	 * @return
	 */
	public Arista getArista() {
		return this.arista;
	}

	/**
	 * Settear la arista enlazada
	 * 
	 * @param arista
	 */
	public void setArista(Arista arista) {
		this.arista = arista;
	}

	/**
	 * Obtener el nodo enlazado
	 * 
	 * @return
	 */
	public Nodo getNodo() {
		return this.nodo;
	}

	/**
	 * Settear el nodo enlazado
	 * 
	 * @param nodo
	 */
	public void setNodo(Nodo nodo) {
		this.nodo = nodo;
	}
}