package grafo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * 
 * @author Erick
 *
 */
public class LineaQuebrada {
	private Coordenadas coordenadas;
	private Color color;
	private float grosorLinea;
	private String etiqueta;
	private int longitud;
	private boolean mostrarEtiqueta;

	/**
	 * Constructor para objetos de la clase Lineaquebrada
	 */
	public LineaQuebrada(Coordenadas coordenadas) {
		// inicializando variables de instancia
		this.coordenadas = coordenadas;
		grosorLinea = 1f;
		color = Color.black;
		mostrarEtiqueta = true;
	}

	/**
	 * 
	 * @param g
	 */
	public void dibujarLineaQuebrada(Graphics g) {
		((Graphics2D) g).setColor(getColor());
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		BasicStroke stroke = new BasicStroke(grosorLinea);
		((Graphics2D) g).setStroke(stroke);
		if (coordenadas != null) {
			int[] aux = new int[0];
			for (int i = 0; i < coordenadas.size(); i++) {
				aux = coordenadas.get(i);
				if (i == 0) {
					((Graphics2D) g).drawLine(aux[0], aux[1], aux[0], aux[1]);
				} else {
					((Graphics2D) g).drawLine(coordenadas.get(i - 1)[0], coordenadas.get(i - 1)[1], aux[0], aux[1]);
				}
			}
			if (mostrarEtiqueta) {
				((Graphics2D) g).setColor(Color.blue);
				Font fuente = new Font("Monospaced", Font.BOLD, 12);
				g.setFont(fuente);
				if (coordenadas.size() == 2) {
					int xMenor = menor(coordenadas.get(0)[0], coordenadas.get(1)[0]);
					int yMenor = menor(coordenadas.get(0)[1], coordenadas.get(1)[1]);
					int xMayor = mayor(coordenadas.get(0)[0], coordenadas.get(1)[0]);
					int yMayor = mayor(coordenadas.get(0)[1], coordenadas.get(1)[1]);
					int distanciaVertical = yMayor - yMenor;
					int distanciaHorizontal = xMayor - xMenor;
					((Graphics2D) g).drawString(longitud + "", (distanciaHorizontal / 2) + xMenor,
							(distanciaVertical / 2) + yMenor);
				} else {
					int pos = coordenadas.size() / 2;
					((Graphics2D) g).drawString(longitud + "", coordenadas.get(pos)[0] + 3,
							coordenadas.get(pos)[1] - 8);
				}
			}
		}
		stroke = new BasicStroke(1);
		((Graphics2D) g).setStroke(stroke);
	}

	/**
	 * 
	 * @param n1
	 * @param n2
	 * @return
	 */
	private int mayor(int n1, int n2) {
		return (n1 > n2) ? n1 : n2;
	}

	/**
	 * 
	 * @param n1
	 * @param n2
	 * @return
	 */
	private int menor(int n1, int n2) {
		return (n1 < n2) ? n1 : n2;
	}

	/**
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * 
	 * @return
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * 
	 * @param grosorLinea
	 */
	public void setGrosorLinea(float grosorLinea) {
		this.grosorLinea = grosorLinea;
	}

	/**
	 * 
	 * @return
	 */
	public float getGrosorLinea() {
		return grosorLinea;
	}

	/**
	 * 
	 * @param etiqueta
	 */
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	/**
	 * 
	 * @return
	 */
	public String getEtiqueta() {
		return etiqueta;
	}

	/**
	 * 
	 * @return
	 */
	public int getLongitud() {
		return longitud;
	}

	/**
	 * 
	 * @param longitud
	 */
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	/**
	 * 
	 * @return
	 */
	public Coordenadas getCoordenadas() {
		return coordenadas;
	}

	/**
	 * 
	 * @param coordenadas
	 */
	public void setCoordenadas(Coordenadas coordenadas) {
		this.coordenadas = coordenadas;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isMostrarEtiqueta() {
		return mostrarEtiqueta;
	}

	/**
	 * 
	 * @param mostrarEtiqueta
	 */
	public void setMostrarEtiqueta(boolean mostrarEtiqueta) {
		this.mostrarEtiqueta = mostrarEtiqueta;
	}
}