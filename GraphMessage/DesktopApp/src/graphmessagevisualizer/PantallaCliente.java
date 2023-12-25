package graphmessagevisualizer;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import cliente.Mensaje;

public class PantallaCliente extends JFrame {
	// Declarar variables a utilizar
	private JButton bVerCamino;
	private JLabel eContenido;
	private JLabel eDestinarios;
	private JLabel eMensajes;
	private JLabel eTiempo;
	private JList<String> listaContenido;
	private JList<String> listaDestinarios;
	private JList<String> listaMensajes;
	private JList<String> listaTiempo;
	private JPanel jpanel;
	// CONSTANTES a utilizar
	private static final int ALTO_VENTANA = 400;
	private static final int ANCHO_VENTANA = 600;
	private static final long serialVersionUID = 1L;
	private static final String TEXTO_CONTENIDO = "Contenido";
	private static final String TEXTO_DESTINATARIO = "Destinatario";
	private static final String TEXTO_MSJ = "Mensaje";
	private static final String TEXTO_TIEMPO = "Tiempo";
	private static final String TEXTO_VER_CAMINO = "Ver Grafo";

	public PantallaCliente() {
		// Cuando la ventana se cierra el programa se detiene
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// No se le puede cambiar el tamaño
		setResizable(false);
		// Tamaño de la ventana
		setSize(ANCHO_VENTANA, ALTO_VENTANA);
		// Llamar metodo donde se inician los componentes
		iniciarComponentes();
	}

	/**
	 * Inicializar componentes
	 */
	private void iniciarComponentes() {
		// Inicializar
		bVerCamino = new JButton(TEXTO_VER_CAMINO);
		eContenido = new JLabel(TEXTO_CONTENIDO);
		eDestinarios = new JLabel(TEXTO_DESTINATARIO);
		eMensajes = new JLabel(TEXTO_MSJ);
		eTiempo = new JLabel(TEXTO_TIEMPO);
		jpanel = new JPanel();
		listaContenido = new JList<>();
		listaDestinarios = new JList<>();
		listaMensajes = new JList<>();
		listaTiempo = new JList<>();

		// Configurar Boton Ver Camino
		bVerCamino.setSize(100, 25);
		bVerCamino.setLocation(480, 330);

		// Configurar Etiquete Contenido
		eContenido.setSize(150, 50);
		eContenido.setLocation(300, 5);
		eContenido.setFont(new Font("Times New Roman", Font.BOLD, 20));

		// Configurar Etiqueta Destinatario
		eDestinarios.setSize(150, 50);
		eDestinarios.setLocation(100, 5);
		eDestinarios.setFont(new Font("Times New Roman", Font.BOLD, 20));

		// Configurar Etiqueta Msj
		eMensajes.setSize(150, 50);
		eMensajes.setLocation(10, 5);
		eMensajes.setFont(new Font("Times New Roman", Font.BOLD, 20));

		// Configurar Etiqueta Tiempo
		eTiempo.setSize(150, 50);
		eTiempo.setLocation(220, 5);
		eTiempo.setFont(new Font("Times New Roman", Font.BOLD, 20));

		// Configurar lista Contenido
		listaContenido.setSize(280, 250);
		listaContenido.setLocation(300, 50);

		// Configurar lista Destinatario
		listaDestinarios.setSize(105, 250);
		listaDestinarios.setLocation(100, 50);

		// Configurar lista Msj
		listaMensajes.setSize(75, 250);
		listaMensajes.setLocation(10, 50);

		// Configurar lista Tiempo
		listaTiempo.setSize(65, 250);
		listaTiempo.setLocation(220, 50);

		// Configurar Panel
		jpanel.setLayout(null);

		// Agregar al panel
		jpanel.add(bVerCamino);
		jpanel.add(eContenido);
		jpanel.add(eDestinarios);
		jpanel.add(eMensajes);
		jpanel.add(eTiempo);
		jpanel.add(listaContenido);
		jpanel.add(listaDestinarios);
		jpanel.add(listaMensajes);
		jpanel.add(listaTiempo);

		// Agregar el panel
		add(jpanel);
	}

	public void setName(String cliente) {
		setTitle(cliente);
	}

	public void setContenido(ArrayList<Mensaje> mensajes) {
		String[] aMensajes = new String[mensajes.size()];
		String[] destinatarios = new String[mensajes.size()];
		String[] numMsj = new String[mensajes.size()];
		String[] tiempoTardado = new String[mensajes.size()];
		for (int i = 0; i < mensajes.size(); i++) {
			aMensajes[i] = mensajes.get(i).getTexto();
			destinatarios[i] = mensajes.get(i).getDestinatario();
			numMsj[i] = (i + 1) + "";
			tiempoTardado[i] = mensajes.get(i).getTiempoTardado();
		}
		listaContenido.setListData(aMensajes);
		listaDestinarios.setListData(destinatarios);
		listaMensajes.setListData(numMsj);
		listaTiempo.setListData(tiempoTardado);
	}
}