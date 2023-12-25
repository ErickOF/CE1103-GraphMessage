package graphmessagevisualizer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cliente.Cliente;
import grafo.Grafo;

/**
 * Clase principal del visualizar donde se muestran las opciones a mostrar
 * 
 * @author Erick
 *
 */
public class Visualizer extends JFrame {
	// Declarar variables a utilizar
	private JButton bBuscarContenido;
	private JButton bBuscarPorNodo;
	private JButton bTraficoMsj;
	private JButton bVerArbol;
	private JButton bVisualizarRed;
	private JLabel eArbol;
	private JLabel eClientes;
	private JLabel eGrafo;
	private JList<String> lClientes;
	private JPanel panel;
	private JScrollBar scrollBar;
	private ArrayList<Cliente> ln;
	// CONSTANTES a utilizar
	private static final int ALTO_BOTON = 25;
	private static final int ALTO_VENTANA = 600;
	private static final int ANCHO_BOTON = 150;
	private static final int ANCHO_VENTANA = 850;
	private static final String NOMBRE = "Graph Message Visualizer";
	private static final long serialVersionUID = 1L;
	private static final String TEXTO_ARBOL = "Arbol";
	private static final String TEXTO_BUSCAR_CONTENIDO = "Buscar Contenido";
	private static final String TEXTO_BUSCAR_POR_NODO = "Buscar Por Nodo";
	private static final String TEXTO_CLIENTES = "Clientes";
	private static final String TEXTO_GRAFO = "Grafo";
	private static final String TEXTO_TRAFICO_MSJ = "Trafico De Mensajes";
	private static final String TEXTO_VER_ARBOL = "Ver Arbol B";
	private static final String TEXTO_VISUALIZAR_RED = "Visualizar Red";

	/**
	 * Constructor de la clase, se configura la ventana
	 */
	public Visualizer() {
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
		ln = new ArrayList<Cliente>();
		Cliente c1 = new Cliente("ErickOF", "F2:D2:D2:E3");
		Cliente c2 = new Cliente("Fabian", "F2:D2:D2:E3");
		Grafo.getGrafo().agregarCliente(c1);
		Grafo.getGrafo().agregarCliente(c2);
		ln.add(c1);
		ln.add(c2);
		String[] listData = new String[ln.size()];
		for (int i = 0; i < ln.size(); i++) {
			listData[i] = ln.get(i).getNombre();
		}
		lClientes.setListData(listData);

	}

	/**
	 * Metodo donde se inicializan los componentes
	 */
	private void iniciarComponentes() {
		// Instanciar los objetos
		bBuscarContenido = new JButton(TEXTO_BUSCAR_CONTENIDO);
		bBuscarPorNodo = new JButton(TEXTO_BUSCAR_POR_NODO);
		bTraficoMsj = new JButton(TEXTO_TRAFICO_MSJ);
		bVerArbol = new JButton(TEXTO_VER_ARBOL);
		bVisualizarRed = new JButton(TEXTO_VISUALIZAR_RED);
		eArbol = new JLabel(TEXTO_ARBOL);
		eClientes = new JLabel(TEXTO_CLIENTES);
		eGrafo = new JLabel(TEXTO_GRAFO);
		panel = new JPanel();
		scrollBar = new JScrollBar();

		// Configurar boton de Buscar Contenido
		bBuscarContenido.setSize(ANCHO_BOTON, ALTO_BOTON);
		bBuscarContenido.setLocation(600, 350);
		bBuscarContenido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PantallaContenido pc = new PantallaContenido();
				pc.setVisible(true);
			}
		});

		// Configurar boton de Buscar Por Nodo
		bBuscarPorNodo.setSize(ANCHO_BOTON, ALTO_BOTON);
		bBuscarPorNodo.setLocation(600, 400);
		bBuscarPorNodo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PantallaNodo pn = new PantallaNodo();
				pn.setVisible(true);
			}
		});

		// Configurar boton de Trafico de Mensajes
		bTraficoMsj.setSize(ANCHO_BOTON, ALTO_BOTON);
		bTraficoMsj.setLocation(600, 150);
		bTraficoMsj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TraficoMensajes tm = new TraficoMensajes();
				tm.setVisible(true);
			}
		});

		// Configurar boton de Ver Arbol
		bVerArbol.setSize(ANCHO_BOTON, ALTO_BOTON);
		bVerArbol.setLocation(600, 300);
		bVerArbol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PantallaArbol pa = new PantallaArbol();
				pa.setVisible(true);
			}
		});

		// Configurar boton de Vizualizar Red
		bVisualizarRed.setSize(ANCHO_BOTON, ALTO_BOTON);
		bVisualizarRed.setLocation(600, 100);
		bVisualizarRed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PantallaGrafo pg = new PantallaGrafo();
				pg.setVisible(true);
			}
		});

		// Configurar Etiqueta Arbol
		eArbol.setSize(150, 50);
		eArbol.setLocation(640, 250);
		eArbol.setFont(new Font("Times New Roman", Font.BOLD, 24));

		// Configurar Etiqueta Clientes
		eClientes.setSize(150, 50);
		eClientes.setLocation(200, 50);
		eClientes.setFont(new Font("Times New Roman", Font.BOLD, 24));

		// Configurar Etiqueta Grafo
		eGrafo.setSize(150, 50);
		eGrafo.setLocation(640, 50);
		eGrafo.setFont(new Font("Times New Roman", Font.BOLD, 24));

		// Configurar lista Clientes
		lClientes = new JList<>();
		lClientes.setSize(325, 400);
		lClientes.setLocation(75, 120);
		lClientes.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Cliente temp = ln.get(e.getLastIndex());
				while (true) {
					String texto = JOptionPane.showInputDialog(null, "Mensaje");
					String destinatario = JOptionPane.showInputDialog(null, "Para");
					if (texto.equals("")) {
						break;
					}
					temp.addMensaje(texto, destinatario);
				}
				ln.set(e.getLastIndex(), temp);
				// Llamar Pantalla para ver info del cliente
				PantallaCliente pc = new PantallaCliente();
				pc.setName(ln.get(e.getLastIndex()).getNombre());
				pc.setContenido(ln.get(e.getLastIndex()).getMsj());
				pc.setVisible(true);
			}
		});

		// Configurar el panel
		panel.setLayout(null);

		// Configurar Scroll Bar
		scrollBar.setSize(20, 400);
		scrollBar.setLocation(400, 120);

		// Agregar al panel
		panel.add(bBuscarContenido);
		panel.add(bBuscarPorNodo);
		panel.add(bTraficoMsj);
		panel.add(bVerArbol);
		panel.add(bVisualizarRed);
		panel.add(eArbol);
		panel.add(eClientes);
		panel.add(eGrafo);
		panel.add(lClientes);
		panel.add(scrollBar);

		// Agregar panel a la ventana
		add(panel);
	}

	public static void main(String[] args) {
		Visualizer ventanaPrincipal = new Visualizer();
		ventanaPrincipal.setVisible(true);
	}
}