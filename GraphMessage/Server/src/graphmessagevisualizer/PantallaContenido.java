package graphmessagevisualizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import arbolb.Raiz;
import cliente.Mensaje;

/**
 * Clase donde se busca en los mensajes por su contenido
 * 
 * @author Erick
 *
 */
public class PantallaContenido extends JFrame {
	private static final long serialVersionUID = 1L;
	// Variables a utilizar
	private JButton bBuscar;
	private JTextField campoTexto;
	private JPanel jpanel;
	private JList<String> listaCoincidencias;
	private ArrayList<Mensaje> ultimos;
	private Raiz raiz = Raiz.getRaiz();
	// CONSTANTES a utilizar
	private static final int ALTO_VENTANA = 400;
	private static final int ANCHO_VENTANA = 600;
	private static final String NOMBRE = "Buscar Contenido";
	private static final String TEXTO_BUSCAR = "Buscar Contenido";

	/**
	 * Constructor de la clase
	 */
	public PantallaContenido() {
		// Cuando la ventana se cierra el programa se detiene
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// No se le puede cambiar el tama�o
		setResizable(false);
		// Dar nombre
		setTitle(NOMBRE);
		// Tama�o de la ventana
		setSize(ANCHO_VENTANA, ALTO_VENTANA);
		// Llamar metodo donde se inician los componentes
		iniciarComponentes();
	}

	/**
	 * Metodo que se encarga de ininicar los componentes
	 */
	private void iniciarComponentes() {
		// Inicializar componentes
		bBuscar = new JButton(TEXTO_BUSCAR);
		campoTexto = new JTextField();
		jpanel = new JPanel();
		listaCoincidencias = new JList<>();

		// Configurar Boton buscar
		bBuscar.setSize(140, 25);
		bBuscar.setLocation(430, 330);
		bBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Crear un array para los resultados
				String[] resultados = null;
				ultimos = new ArrayList<>();
				// Si el texto no esta vacio
				if (!campoTexto.getText().equals("")) {
					// Se obtiene una lista con palabras que concidan
					ArrayList<Mensaje> lista = raiz.buscarPorContenido(campoTexto.getText());
					// Instanciar la matriz con resultados
					resultados = new String[lista.size()];
					// Recorrer el ArrayList donde se encontraron los resultados
					for (int i = 0; i < lista.size(); i++) {
						// Asignar cada resultado al array de resultados
						resultados[i] = lista.get(i).getTexto();
						ultimos.add(lista.get(i));
					}
				} // Settear contenido en la Vista
				listaCoincidencias.setListData(resultados);
			}
		});

		// Configurar campo de texto
		campoTexto.setSize(400, 25);
		campoTexto.setLocation(10, 330);

		// Configurar jpanel
		jpanel.setLayout(null);

		// Configurar lista coincidencias
		listaCoincidencias.setSize(570, 300);
		listaCoincidencias.setLocation(10, 10);
		listaCoincidencias.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JOptionPane.showMessageDialog(null,
						"De: " + ultimos.get(e.getLastIndex()).getPartida() + "\nPara: "
								+ ultimos.get(e.getLastIndex()).getDestinatario() + "\nFecha: "
								+ ultimos.get(e.getLastIndex()).getHoraEnvio());
			}
		});

		// Agregar al panel
		jpanel.add(bBuscar);
		jpanel.add(campoTexto);
		jpanel.add(listaCoincidencias);

		// Agregar panel
		add(jpanel);
	}
}