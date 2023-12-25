package cliente;

import java.util.ArrayList;

import filtro.FiltroSpam;
import filtro.ListaNegra;

public class Cliente {
	private String macAddress;
	private ArrayList<Mensaje> mensajes;
	private String nombre;

	public Cliente(String nombre, String macAddress) {
		this.nombre = nombre;
		this.macAddress = macAddress;
		this.mensajes = new ArrayList<>();
	}

	public void addMensaje(String texto, String destinatario) {
		if (!FiltroSpam.getFiltroSpam().revisarContenido(texto)) {
			Mensaje msj = new Mensaje(this.nombre, destinatario, texto);
			mensajes.add(msj);
		} else {
			texto = FiltroSpam.getFiltroSpam().censurar(texto);
			Mensaje msj = new Mensaje(this.nombre, destinatario, texto);
			mensajes.add(msj);
			FiltroSpam.getFiltroSpam();
			FiltroSpam.getListaNegra().banear(this);
		}
	}

	public void verificarBaneado(ListaNegra lista, String macAddress) {
		if (lista.buscar(macAddress) == true) {
			System.out.println("Error Usuario baneado");
		} else {
			// Registrar en grafo
			System.out.println("Nada");
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
}