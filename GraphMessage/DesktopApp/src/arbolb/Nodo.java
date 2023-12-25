package arbolb;

import cliente.Mensaje;

/*
 * clase donde se crean los nodos del arbol
 */
public class Nodo {
	private Mensaje[] mensajes;
	private Nodo[] nodo;
	private static int numValores;
	private boolean tengoHijos = false;
	private int ocupados = 0;
	private Nodo padre;

	// constructor de la clase nodo donde se inicializa dos arreglos donde uno
	// se guardan nodos y otro donde se guardan valores
	// la capacidad de estos valores es para los nodos dos veces el grado +3 y
	// para los valores dos veces el grado + 1
	public Nodo() {
		nodo = new Nodo[Raiz.getGrado() * 2 + 3];
		mensajes = new Mensaje[Raiz.getGrado() * 2 + 1];
	}

	public Mensaje[] getMensajes() {
		return mensajes;
	}

	public void setMensajes(Mensaje[] mensajes) {
		this.mensajes = mensajes;
	}

	public Nodo[] getNodo() {
		return nodo;
	}

	public void setNodo(Nodo[] nodo) {
		this.nodo = nodo;
	}

	public static int getNumValores() {
		return numValores;
	}

	public static void setNumValores(int numValores) {
		Nodo.numValores = numValores;
	}

	public boolean isTengoHijos() {
		return tengoHijos;
	}

	public void setTengoHijos(boolean tengoHijos) {
		this.tengoHijos = tengoHijos;
	}

	public int getOcupados() {
		return ocupados;
	}

	public void setOcupados(int ocupados) {
		this.ocupados = ocupados;
	}

	public Nodo getPadre() {
		return padre;
	}

	public void setPadre(Nodo padre) {
		this.padre = padre;
	}
}