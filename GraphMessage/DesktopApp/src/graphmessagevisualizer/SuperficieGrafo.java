package graphmessagevisualizer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import floyd.Floyd;
import grafo.Enlace;
import grafo.Grafo;
import grafo.Nodo;

/**
 * clase que se encarga de dibujar los componentes
 */
public class SuperficieGrafo extends JPanel {
	private static final long serialVersionUID = 1L;
	// Donde se dibujan los objetos
	private Graphics2D graficos_2d;
	// Declarar thread
	private Thread thread;
	private static SuperficieGrafo superficie;
	private Grafo grafo;

	// Constructor de la clase
	private SuperficieGrafo() {
		grafo = Grafo.getGrafo();
		// Inicializar Thread
		thread = new Thread("Dibujador");
		// Empezar Thread
		thread.start();
	}

	public static SuperficieGrafo getSuperficie() {
		if (superficie == null) {
			superficie = new SuperficieGrafo();
		}
		return superficie;
	}

	/**
	 * @param graficos
	 *            Metodo sobreescrito que se encarga de realizar los dibujos en
	 *            la GUI
	 */
	@Override
	public void paint(Graphics graficos) {
		super.paint(graficos);
		dibujarArista(graficos);
		dibujarNodos(graficos);
		Floyd f = Floyd.getFloyd(Grafo.getGrafo().getMatrizDeCostos());
		// int[][] matrizCostos = grafo.getMatrizDeCostos();
		// for (int i = 0; i < grafo.getTamaño(); i++) {
		// for (int j = 0; j < grafo.getTamaño(); j++) {
		// if (matrizCostos[i][j] != grafo.INFINITO) {
		// System.out.println("El costo de ir de " +
		// grafo.getListaNodos().get(i).getNickname() + " a "
		// + grafo.getListaNodos().get(j).getNickname() + " es de " +
		// matrizCostos[i][j] + "m");
		// } else if (matrizCostos[i][j] == 0) {
		// System.out.println("Ya se esta en " +
		// grafo.getListaNodos().get(i).getNickname());
		// } else {
		// System.out.println("No hay camino de " +
		// grafo.getListaNodos().get(i).getNickname() + " a "
		// + grafo.getListaNodos().get(j).getNickname());
		// }
		// }
		// }
		String ruta[] = f.getCaminos()[2][0].split(",");
		// for (int i = 0; i < ruta.length; i++) {
		// int index = 0;
		// for (Nodo nodo : grafo.getListaNodos()) {
		// if (nodo.getNickname().equals(ruta[i])) {
		// System.out.println(ruta[i]);
		// nodo.getCirculo().setColor(Color.RED);
		// // grafo.getListaNodos().get(index).getCirculo().setColor(Color.RED);
		// }
		// }
		// }
		// repaint();
	}

	/**
	 * Dibujar los nodos
	 */
	public void dibujarNodos(Graphics g) {
		// Obtener la lista de nodos
		ArrayList<Nodo> listaNodo = grafo.getListaNodos();
		for (Nodo nodo : listaNodo) {
			// Dibujar cada nodo
			nodo.getCirculo().dibujarCirculo(g);
		}
	}

	/**
	 * Dibujar la arista
	 */
	public void dibujarArista(Graphics g) {
		// Obtener la lista de nodos
		ArrayList<Nodo> listaNodo = grafo.getListaNodos();
		for (Nodo nodo : listaNodo) {
			// Obtener los enlaces con otros nodos
			ArrayList<Enlace> listaEnlace = nodo.getListaNodoAdyacente();
			if (listaEnlace != null) {
				for (Enlace enlace : listaEnlace) {
					if (nodo.getNickname() != enlace.getNodo().getNickname()) {
						int posCirculoX = nodo.getCirculo().getX();
						int posCirculoY = nodo.getCirculo().getY();
						int posEnlaceX = enlace.getNodo().getCirculo().getX();
						int posEnlaceY = enlace.getNodo().getCirculo().getY();
						g.drawLine(posCirculoX, posCirculoY, posEnlaceX, posEnlaceY);
						int distancia = enlace.getArista().getPeso();
						String etiqueta = String.valueOf(distancia / 2);
						int dx = (posCirculoX - posEnlaceX) / 2;
						int dy = (posCirculoY - posEnlaceY) / 2;
						g.drawString(etiqueta + "m", posEnlaceX + dx, posEnlaceY + dy);
					}
				}
			}
		}
	}
}