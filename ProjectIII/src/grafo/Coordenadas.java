package grafo;

import java.util.ArrayList;

/**
 * Clase que contiene las coordenadas del circulo y el nodo
 * 
 * @author Erick
 *
 */
public class Coordenadas extends ArrayList<int[]> {
	private static final long serialVersionUID = 1L;
	// Declarar variables a utilizar
	private int xMaxima;
	private int yMaxima;

	/**
	 * Contructor de la clase por defecto
	 */
	public Coordenadas() {
		this(0, 0);
	}

	/**
	 * Contructor de la clase
	 * 
	 * @param xMaxima
	 * @param yMaxima
	 */
	public Coordenadas(int xMaxima, int yMaxima) {
		// inicializando variables de instancia
		super();
		this.xMaxima = xMaxima;
		this.yMaxima = yMaxima;
	}

	/**
	 * Constructor de la clase
	 * 
	 * @param xMaxima
	 * @param yMaxima
	 * @param x
	 * @param y
	 */
	public Coordenadas(int xMaxima, int yMaxima, int x, int y) {
		super();
		this.xMaxima = xMaxima;
		this.yMaxima = yMaxima;
		// Agregar las coordenadas
		addCoordenada(x, y);
	}

	/**
	 * Metodo para agregar las coordenadas
	 * 
	 * @param x
	 * @param y
	 */
	public void addCoordenada(int x, int y) {
		// Si las coordenadas son menores a las mayores posibles
		if (x >= 0 && x <= xMaxima && y >= 0 && y <= yMaxima) {
			int[] parXY = { x, y };
			// Agregar el par de coordenadas
			add(parXY);
		}
	}

	/**
	 * Obtener la maxima coordenada posible
	 * 
	 * @return
	 */
	public int getxMaxima() {
		return xMaxima;
	}

	/**
	 * Cambiar el x maximo
	 * 
	 * @param xMaxima
	 */
	public void setXMaxima(int xMaxima) {
		this.xMaxima = xMaxima;
	}

	/**
	 * Obtener el maximo y posible
	 * 
	 * @return
	 */
	public int getyMaxima() {
		return yMaxima;
	}

	/**
	 * Cambiar el maximo y
	 * 
	 * @param yMaxima
	 */
	public void setYMaxima(int yMaxima) {
		this.yMaxima = yMaxima;
	}
}