package graphmessagevisualizer;

import javax.swing.GroupLayout;
import javax.swing.JFrame;

public class PantallaGrafo extends JFrame {
	private static final long serialVersionUID = 1L;
	// Declarar variables necesarias
	private SuperficieGrafo superficie;
	// Declarar constantes necesarias
	private static final int DIMENSION = 600;
	private static final String NOMBRE = "Ver Grafo";

	/**
	 * Contructor de la clase
	 */
	public PantallaGrafo() {
		// Inicializar variables
		superficie = SuperficieGrafo.getSuperficie();
		add(superficie);
		// Dar nombre a la ventana
		setTitle(NOMBRE);
		// Operacion para cerrar
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// Dimensiones
		setBounds(400, 100, DIMENSION, DIMENSION);
		javax.swing.GroupLayout jPanel1Layout = new GroupLayout(superficie);
		superficie.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 629, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 417, Short.MAX_VALUE));
	}
}
