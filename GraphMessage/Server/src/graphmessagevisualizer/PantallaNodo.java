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

public class PantallaNodo extends JFrame {
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
	private static final String NOMBRE = "Buscar Nodo";
	private static final String TEXTO_BUSCAR = "Buscar Nodo";

	public PantallaNodo() {
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
				String[] esta = new String[1];
				if (!campoTexto.getText().equals("")) {
					boolean numero = raiz.esNumero(campoTexto.getText());
					esta[0] = raiz.buscar(Integer.parseInt(campoTexto.getText()));
					if (numero) {
						if (esta != null) {
							JOptionPane.showMessageDialog(null, esta, "El Dato del Nodo!!!",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				}
				listaCoincidencias.setListData(esta);
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