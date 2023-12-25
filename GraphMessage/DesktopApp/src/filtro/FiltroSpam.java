package filtro;

import cliente.Cliente;

public class FiltroSpam {
	// Declarar variables a utilizar
	private static FiltroSpam filtroSpam;
	private static ListaNegra<Cliente> listaNegra;
	private static String[] malasPalabras = { "mierda", "joder", "puta", "hijueputa", "picha", "malparido", "estupido",
			"imbecil", "puto", "maricon", "sobon", "sobo", "panocha", "verga", "playo", "carepicha" };

	/**
	 * Constructor de la clase
	 */
	private FiltroSpam() {
		listaNegra = new ListaNegra<>();
	}

	/**
	 * Singleton de la clase
	 * 
	 * @return
	 */
	public static FiltroSpam getFiltroSpam() {
		// Sino hay un filtro de spam creado
		if (filtroSpam == null) {
			// Se instancia uno
			filtroSpam = new FiltroSpam();
		}
		// Se retorna el filtro de Spam
		return filtroSpam;
	}

	/**
	 * Metodo que se encarga de revisar el vocabulario en el mensaje
	 * 
	 * @param mensaje
	 * @return
	 */
	public boolean revisarContenido(String mensaje) {
		String[] texto = mensaje.split(" ");
		for (String palabra : texto) {
			for (int i = 0; i < malasPalabras.length; i++) {
				if (malasPalabras[i].equals(palabra.toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Funcion que censura un mensaje si se encontro contenido no apropiado
	 * 
	 * @param mensaje
	 * @return
	 */
	public String censurar(String mensaje) {
		// Partir el texto en palabras para revisarlo
		String[] texto = mensaje.split(" ");
		// Recorrer las palabras en el texto
		for (int i = 0; i < texto.length; i++) {
			// Recorrer la lista de malas palabras
			for (int j = 0; j < malasPalabras.length; j++) {
				// Comparar si alguna de las malas palabras se encuentra en el
				if (malasPalabras[j].equals(texto[i].toLowerCase())) {
					// Cambiar la palabra por una censura
					int cant = texto[i].length();
					int cont = 0;
					texto[i] = "";
					while (cont < cant) {
						texto[i] += "*";
						cont++;
					}
				}
			}
		}
		String mensajeRevisado = "";
		for (int i = 0; i < texto.length; i++) {
			mensajeRevisado += texto[i];
			if (i < texto.length - 1) {
				mensajeRevisado += " ";
			}
		}
		return mensajeRevisado;
	}

	/**
	 * 
	 * @return
	 */
	public static ListaNegra<Cliente> getListaNegra() {
		return listaNegra;
	}

	/**
	 * 
	 * @param listaNegra
	 */
	public static void setListaNegra(ListaNegra<Cliente> listaNegra) {
		FiltroSpam.listaNegra = listaNegra;
	}
}