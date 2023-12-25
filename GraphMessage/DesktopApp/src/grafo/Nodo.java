package grafo;

import java.util.ArrayList;

/**
 * Clse nodo para el grafo
 *
 */
public class Nodo {
	// Variables a utilizar
	private Circulo circulo;
	private ArrayList<Enlace> listaNodoAdyacente;
	private String macAdress;
	private Object nickname;
	private boolean cliente = false;
	private boolean visitado = false;

	/**
	 * Constructor de la clase
	 */
	public Nodo() {
		this.nickname = new Object();
		circulo = null;
	}

	/**
	 * 
	 * @param nickname
	 * @param coordenada
	 */
	public Nodo(Object nickname, Coordenadas coordenada) {
		this.nickname = nickname;
		listaNodoAdyacente = new ArrayList<Enlace>();
		circulo = new Circulo(coordenada);
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Enlace> getListaNodoAdyacente() {
		ArrayList<Enlace> listAristaAux = null;
		if (!listaNodoAdyacente.isEmpty()) {
			listAristaAux = new ArrayList<Enlace>();
			for (Enlace enlace : listaNodoAdyacente) {
				if (enlace.getArista().isHabilitado()) {
					listAristaAux.add(enlace);
				}
			}
		}
		return listaNodoAdyacente;
	}

	public void setListaNodoAdyacente() {
		this.listaNodoAdyacente = new ArrayList<>();
	}

	/**
	 * 
	 * @param arista
	 */
	public void addNodoAdyacente(Enlace arista) {
		listaNodoAdyacente.add(arista);
	}

	/**
	 * 
	 * @param via
	 * @param nodo
	 */
	public void addNodoAdyacente(Arista via, Nodo nodo) {
		this.addNodoAdyacente(new Enlace(via, nodo));
	}

	/**
	 * 
	 * @param nombre
	 * @return
	 */
	public Enlace buscarEnlace(String nombre) {
		for (Enlace enlace : listaNodoAdyacente) {
			if (enlace.getNodo().nickname == nombre) {
				return enlace;
			}
		}
		return null;
	}

	// Getter y Setters
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public Circulo getCirculo() {
		return circulo;
	}

	public void setCirculo(Circulo circulo) {
		this.circulo = circulo;
	}

	public String getMacAdress() {
		return macAdress;
	}

	public void setMacAdress(String macAdress) {
		this.macAdress = macAdress;
	}

	public void setNickname(Object nickname) {
		this.nickname = nickname;
	}

	public Object getNickname() {
		return this.nickname;
	}

	public boolean isCliente() {
		return cliente;
	}

	public void setCliente(boolean cliente) {
		this.cliente = cliente;
	}
}