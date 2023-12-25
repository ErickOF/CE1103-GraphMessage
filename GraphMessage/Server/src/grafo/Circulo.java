package grafo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Clase que representa los nodos graficamente
 * 
 * @author Erick
 */
public class Circulo {
	// Declarar variables a utilizar
	private Color color;
	private Coordenadas coordenadas;
	private int diametro;
	private String etiqueta;
	private Font fuente;
	private int grosorBorde;

	/**
	 * Contructor de la clase
	 * 
	 * @param coordenadas
	 */
	public Circulo(Coordenadas coordenadas) {
		// inicializando variables de instancia
		this.coordenadas = coordenadas;
		color = Color.yellow;
		diametro = 10;
		etiqueta = "";
		fuente = null;
		grosorBorde = 2;
	}

	/**
	 * Metodo que se encarga de dibujar la representacion del nodo en el panel
	 * 
	 * @param g
	 */
	public void dibujarCirculo(Graphics g) {
		// Si las coordenadas no estan vacias
		if (coordenadas.size() > 0) {
			// Poner color al circulo
			((Graphics2D) g).setColor(color);
			((Graphics2D) g).setStroke(new BasicStroke(getGrosorBorde()));
			// Crear el ovalo
			((Graphics2D) g).fillOval(coordenadas.get(0)[0], coordenadas.get(0)[1], diametro, diametro);
			// Color de la linea de contorno del circulo
			((Graphics2D) g).setColor(Color.black);
			// Dibujar la linea de contorno del circulo
			((Graphics2D) g).drawOval(coordenadas.get(0)[0], coordenadas.get(0)[1], diametro, diametro);
			// Si la etiqueta es diferente de vacio
			if (!"".equals(etiqueta)) {
				// Si la fuente no esta vacias
				if (fuente != null) {
					// Se pone una fuente para la escritura
					g.setFont(fuente);
				}
				// Escribir el nombre del usuario
				((Graphics2D) g).drawString(etiqueta, coordenadas.get(0)[0], coordenadas.get(0)[1]);
				((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			}
		}
	}

	// Getters y Setters

	/**
	 * Obtener el color del Circulo
	 * 
	 * @return
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Cambiar el color del circulo
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Obtener el diametro del circulo
	 * 
	 * @return
	 */
	public int getDiametro() {
		return diametro;
	}

	/**
	 * Cambiar el diametro del nodo
	 * 
	 * @param diametro
	 */
	public void setDiametro(int diametro) {
		this.diametro = diametro;
	}

	/**
	 * Obtener el nombre de la etiqueta
	 * 
	 * @return
	 */
	public String getEtiqueta() {
		return etiqueta;
	}

	/**
	 * Cambiar el nombre del circulo
	 * 
	 * @param etiqueta
	 */
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	/**
	 * Cambiar el nombre del circulo
	 * 
	 * @param etiqueta
	 * @param izquierda
	 */
	public void setEtiqueta(String etiqueta, boolean izquierda) {
		this.etiqueta = etiqueta;
	}

	/**
	 * Obtener la posicion en x del nodo
	 * 
	 * @return
	 */
	public int getX() {
		if (coordenadas.size() > 0) {
			return coordenadas.get(0)[0];
		} else {
			return -1;
		}
	}

	/**
	 * Obtener la posicion en y del nodo
	 * 
	 * @return
	 */
	public int getY() {
		if (coordenadas.size() > 0) {
			return coordenadas.get(0)[1];
		} else {
			return -1;
		}
	}

	/**
	 * obtener la fuente con la que se escribe
	 * 
	 * @return
	 */
	public Font getFuente() {
		return fuente;
	}

	/**
	 * Settear la fuente con la que se escribe
	 * 
	 * @param fuente
	 */
	public void setFuente(Font fuente) {
		this.fuente = fuente;
	}

	/**
	 * Obtener el grosor del borde del circulo
	 * 
	 * @return
	 */
	public int getGrosorBorde() {
		return grosorBorde;
	}

	/**
	 * Cambiar el grosor del circulo
	 * 
	 * @param grosorBorde
	 */
	public void setGrosorBorde(int grosorBorde) {
		this.grosorBorde = grosorBorde;
	}
}