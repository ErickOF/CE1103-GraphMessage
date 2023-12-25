package cliente;

import java.util.ArrayList;

public class Cliente {
	private String macAddress;
	private ArrayList<Mensaje> mensajes;
	private String nombre;
	private String ip;

	public Cliente(String nombre, String macAddress,String pIP) {
		this.nombre = nombre;
		this.macAddress = macAddress;
		this.ip = pIP;
		this.mensajes = new ArrayList<>();
	}

	public void addMensaje(String texto, String destinatario) {
		Mensaje msj = new Mensaje(this.nombre, destinatario, texto);
		mensajes.add(msj);
	}

	public void banearCliente(ListaNegra lista, String macAddress) {
		lista.banear(macAddress);
		lista.imprimir();
	}

	public String verificarBaneado(ListaNegra lista, String macAddress) {
		if (lista.buscar(macAddress) == true) {
			System.out.println("Error Usuario baneado");
			return "baneado";
		} else {
			// Registrar en grafo
			System.out.println("Nada");
			return "Aceptado";
		}
	}

	public String getNombre() {
		return nombre;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public ArrayList<Mensaje> getMsj() {
		return mensajes;
	}

	public String getIP() {
		return ip;
	}
}