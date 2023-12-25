package grafo;

/**
 * Clase donde se crean las lineas de union
 * 
 * @author Erick
 * 
 */
public class Arista {
	// Declarar variables a utilizar
	private boolean habilitado;
	private int idArista;
	private LineaQuebrada lineaQuebrada;
	private String nombreArista;
	private int peso;

	/**
	 * Constuctor de la clase
	 */
	public Arista() {
		this.idArista = -1;
		this.nombreArista = "";
	}

	// Getters y Setters

	/**
	 * Saber si esta disponible la Arista
	 * 
	 * @return
	 */
	public boolean isHabilitado() {
		return habilitado;
	}

	/**
	 * Settear la disponiblidad de la Arista
	 * 
	 * @param habilitado
	 */
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	/**
	 * Obtener el id de la Arista
	 * 
	 * @return
	 */
	public int getIdArista() {
		return this.idArista;
	}

	/**
	 * Settear el id de la Arista
	 * 
	 * @param idArista
	 */
	public void setIdArista(int idArista) {
		this.idArista = idArista;
	}

	/**
	 * Obtener la Linea Quebrada
	 * 
	 * @return
	 */
	public LineaQuebrada getLineaQuebrada() {
		return lineaQuebrada;
	}

	/**
	 * Settear la Linea queb|rada
	 * 
	 * @param lineaQuebrada
	 */
	public void setLineaQuebrada(LineaQuebrada lineaQuebrada) {
		this.lineaQuebrada = lineaQuebrada;
		if (lineaQuebrada != null) {
			this.lineaQuebrada.setLongitud(peso);
		}
	}

	/**
	 * Obtener el nombre de la Arista
	 * 
	 * @return
	 */
	public String getNombreArista() {
		return this.nombreArista;
	}

	/**
	 * Settear el nombre de la Arista
	 * 
	 * @param nombreVia
	 */
	public void setNombreArista(String nombreVia) {
		this.nombreArista = nombreVia;
	}

	/**
	 * Obtener peso de la Arista
	 * 
	 * @return
	 */
	public int getPeso() {
		return this.peso;
	}

	/**
	 * Settear el peso de la Arista
	 * 
	 * @param peso
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}
}