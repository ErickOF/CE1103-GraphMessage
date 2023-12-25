package cliente;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import arbolb.Raiz;

public class Mensaje {
	// Declarar variables a utilizar
	private String destinatario;
	private String texto;
	private Calendar fecha = Calendar.getInstance();;
	private Date horaEnvio;
	private String remitente;
	private String tiempoTardado;
	private int valor;
	private Random random = new Random();
	private Raiz raiz = Raiz.getRaiz();

	@SuppressWarnings("deprecation")
	public Mensaje(String partida, String destinatario, String texto) {
		this.destinatario = destinatario;
		this.remitente = partida;
		this.texto = texto;
		this.horaEnvio = fecha.getTime();
		this.tiempoTardado = (fecha.getTime().getSeconds() - this.horaEnvio.getSeconds()) + ":" + random.nextInt(1000)
				+ "ms";
		this.valor = raiz.getContador();
		raiz.insertar(this);
		;
	}

	public String getDestinatario() {
		return this.destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getTexto() {
		return this.texto;
	}

	public Date getHoraEnvio() {
		return horaEnvio;
	}

	public String getPartida() {
		return remitente;
	}

	public String getTiempoTardado() {
		return tiempoTardado;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getValor() {
		return valor;
	}
}