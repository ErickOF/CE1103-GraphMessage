package graphmessagevisualizer;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import arbolb.Raiz;

public class TraficoMensajes extends JFrame {
	private static final long serialVersionUID = 1L;
	// Declarar variables a utilizar
	private JLabel eHora;
	private JLabel eMensajes;
	private JPanel jpanel;
	private String[] horas;
	private JList<String> listaHora;
	private JList<String> listaMensajes;
	private String[] mensajes;
	// CONSTANTES a utilizar
	private static final int ALTO_VENTANA = 400;
	private static final int ANCHO_VENTANA = 400;
	private static final String NOMBRE = "Trafico de Mensajes";
	private static final String TEXTO_HORA = "Hora";
	private static final String TEXTO_MSJ = "Mensajes";
	// Variables instanciadas
	private Raiz raiz = Raiz.getRaiz();

	/**
	 * Constructor de la clase
	 */
	public TraficoMensajes() {
		// Cuando la ventana se cierra el programa se detiene
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// No se le puede cambiar el tamaño
		setResizable(false);
		// Tamaño de la ventana
		setSize(ANCHO_VENTANA, ALTO_VENTANA);
		// Dar titulo a la ventana
		setTitle(NOMBRE);
		// Llamar metodo donde se inician los componentes
		iniciarComponentes();
		setContenido();
	}

	/**
	 * Iniciar los componentes de la clase
	 */
	private void iniciarComponentes() {
		// Instanciar
		eHora = new JLabel(TEXTO_HORA);
		eHora = new JLabel(TEXTO_HORA);
		eMensajes = new JLabel(TEXTO_MSJ);
		jpanel = new JPanel();
		listaHora = new JList<>();
		listaMensajes = new JList<>();

		// Configurar Etiqueta Hora
		eHora.setFont(new Font("Times New Roman", Font.BOLD, 20));
		eHora.setSize(150, 50);
		eHora.setLocation(20, 5);

		// Configurar Etiqueta Msj
		eMensajes.setFont(new Font("Times New Roman", Font.BOLD, 20));
		eMensajes.setSize(200, 50);
		eMensajes.setLocation(210, 5);

		// Configurar Lista Hora
		listaMensajes.setSize(180, 300);
		listaMensajes.setLocation(200, 55);

		// Configurar Lista Msj
		listaHora.setSize(160, 300);
		listaHora.setLocation(10, 55);

		// Configurar panel
		jpanel.setLayout(null);

		// Agregar al panel
		jpanel.add(eHora);
		jpanel.add(eMensajes);
		jpanel.add(listaHora);
		jpanel.add(listaMensajes);

		// Agregar el panel
		add(jpanel);
	}

	/**
	 * Configurar el contenido de la clase
	 */
	public void setContenido() {
		// Arrays donde se guardan los datos a mostrar
		mensajes = new String[raiz.getContador()];
		horas = new String[raiz.getContador()];
		// Recorrer los mensajes dondes se quiere conseguir informacion
		for (int i = 0; i < mensajes.length; i++) {
			// Ir agregando los datos
			mensajes[i] = raiz.getPrimerNodo().getMensajes()[i].getTexto();
			horas[i] = raiz.getPrimerNodo().getMensajes()[i].getHoraEnvio().toString();
		} // Mostrar los datos en el Vista
		listaMensajes.setListData(mensajes);
		listaHora.setListData(horas);
	}
}