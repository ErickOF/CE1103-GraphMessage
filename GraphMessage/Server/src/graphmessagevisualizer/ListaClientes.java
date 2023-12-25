package graphmessagevisualizer;

import java.util.ArrayList;

import cliente.Cliente;

public class ListaClientes {
	private static ListaClientes _lista;
	private ArrayList<Cliente> clientes;

	// El constructor es privado, no permite que se genere un constructor por
	// defecto.
	private ListaClientes() {
		clientes = new ArrayList<>();
	}

	public static ListaClientes getSingletonInstance() {
		if (_lista == null) {
			_lista = new ListaClientes();
		}
		return _lista;
	}

	public void agregar(Cliente nuevoCliente) {
		if (!isEnLista(nuevoCliente)) {
			clientes.add(nuevoCliente);
		}
	}

	private boolean isEnLista(Cliente cliente) {
		for (Cliente nodo : clientes) {
			if (nodo.getMacAddress().equals(cliente.getMacAddress())) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
}