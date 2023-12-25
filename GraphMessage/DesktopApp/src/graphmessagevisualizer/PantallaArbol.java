package graphmessagevisualizer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import arbolb.Raiz;

public class PantallaArbol extends JFrame {
	private static final long serialVersionUID = 1L;
	// Declarar variables a utilizar
	private JTextArea campoTexto;
	private JPanel jpanel;
	private Raiz raiz = Raiz.getRaiz();
	// CONSTANTES a utilizar
	private static final int ALTO_VENTANA = 400;
	private static final int ANCHO_VENTANA = 600;
	private static final String NOMBRE = "Ver Arbol B";

	public PantallaArbol() {
		// Cuando la ventana se cierra el programa se detiene
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// Dar nombre a la ventana
		setTitle(NOMBRE);
		// No se le puede cambiar el tamaño
		setResizable(false);
		// Tamaño de la ventana
		setSize(ANCHO_VENTANA, ALTO_VENTANA);
		// Llamar metodo donde se inician los componentes
		iniciarComponentes();
		mostrarArbol();

	}

	private void iniciarComponentes() {
		// Instanciar componentes
		campoTexto = new JTextArea();
		jpanel = new JPanel();

		// Configurar Campo de Texto
		campoTexto.setSize(350, 570);
		campoTexto.setLocation(10, 10);

		// Configurar panel
		jpanel.setLayout(null);

		// Agregar al panel
		jpanel.add(campoTexto);

		// Agregar panel
		add(jpanel);
	}

	private void mostrarArbol() {
		raiz.setArbol("");
		String temp = "raiz [ ";
		for (int i = 0; i < raiz.getPrimerNodo().getMensajes().length
				&& raiz.getPrimerNodo().getMensajes()[i] != null; i++) {
			temp += raiz.getPrimerNodo().getMensajes()[i].getValor() + ", ";
		}
		temp += " ]\n";
		temp += raiz.llamarRecorrer();
		campoTexto.setText(temp);
	}
}
