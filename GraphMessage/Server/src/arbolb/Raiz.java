package arbolb;

import java.util.ArrayList;

import cliente.Mensaje;

/*
 * clase donde se creara el arbol
 */
public class Raiz {
	// grado de el arbolsiempre va a ser 2
	private static int grado = 2;
	// primer nodo de el arbol
	private Nodo primerNodo;
	private static boolean esRaiz = true;
	private static int nivel = 1;
	private static int imprimir = 1;
	private static String arbol = "";
	private int contador;
	private static Raiz raiz;

	/*
	 * constructor de la clase raiz donde se inicializa el primer nodo y el
	 * contador para insertar los nodos en el arbol
	 */
	private Raiz() {
		primerNodo = new Nodo();
		this.contador = 0;
	}

	public static Raiz getRaiz() {
		if (raiz == null) {
			raiz = new Raiz();
		}
		return raiz;
	}

	/*
	 * metodo de insertar al el arbol donde entra como paramentro un string que
	 * representa el mensaje, y con este mensaje crea una instancia de la clase
	 * mensaje donde donde se guarda el mensaje y un valor numerico para
	 * guardarlo en el arbol b
	 */
	public void insertar(String texto, String destinatario, String partida) {
		// instancia creada que sera guardada en el arbol b
		Mensaje temp = new Mensaje(partida, destinatario, texto);
		// se aumenta el contador global de los valores numericos que entra a el
		// arbol
		this.contador++;
		// en caso de que el primer nodo no tenga hijos
		if (primerNodo.isTengoHijos() == false) {
			int j = 0;
			// se recorre el arreglo que contiene los valores del nodo hasta
			// encontrar un campo para insertar
			for (int i = 0; i < primerNodo.getMensajes().length; i++) {
				if (primerNodo.getMensajes()[i] == null) {
					primerNodo.getMensajes()[i] = temp;
					Lista.ingresados.add(temp);
					j = i;
					ordenar(primerNodo.getMensajes());
					break;
				}
			}
			// si j llega a ser el doble del el nodo hay que dividirlo
			if (j == 2 * grado) {
				split(primerNodo);
			}
			// en caso de que el nodo tenga hijos
		} else {
			setTengoHijos(primerNodo);
			ingresarEnHijos(primerNodo, temp);
		}
	}

	public void insertar(Mensaje temp) {
		this.contador++;
		// en caso de que el primer nodo no tenga hijos
		if (primerNodo.isTengoHijos() == false) {
			int j = 0;
			// se recorre el arreglo que contiene los valores del nodo hasta
			// encontrar un campo para insertar
			for (int i = 0; i < primerNodo.getMensajes().length; i++) {
				if (primerNodo.getMensajes()[i] == null) {
					primerNodo.getMensajes()[i] = temp;
					Lista.ingresados.add(temp);
					j = i;
					ordenar(primerNodo.getMensajes());
					break;
				}
			}
			// si j llega a ser el doble del el nodo hay que dividirlo
			if (j == 2 * grado) {
				split(primerNodo);
			}
			// en caso de que el nodo tenga hijos
		} else {
			setTengoHijos(primerNodo);
			ingresarEnHijos(primerNodo, temp);
		}
	}

	/*
	 * metodo que ordena un arreglo mediante
	 */
	public void ordenar(Mensaje arr[]) {
		// se inicializa una variable longitud que va a se r lacantidad de el
		// ementos que tenga el nodo
		int longitud = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				longitud++;
			} else {
				break;
			}
		} // mientras ord sea distinto de la longitud
		for (int ord = 0; ord < longitud; ord++) {
			for (int ord1 = 0; ord1 < longitud - 1; ord1++) {
				// si l valor de la ord1 es mayor que ord1 + 1 los intercambia
				// de posicion
				if (arr[ord1].getValor() > arr[ord1 + 1].getValor()) {
					Mensaje tmp = arr[ord1];
					arr[ord1] = arr[ord1 + 1];
					arr[ord1 + 1] = tmp;
				}
			}
		}
	}

	/*
	 * metodo que daclar si los nodos tienen hijos
	 */
	public void setTengoHijos(Nodo nodo) {
		// si el nodo que entra es igual a primer nodo se revisa para ver si
		// tiene hijos
		if (nodo == primerNodo) {
			if (primerNodo.getNodo()[0] != null) {
				primerNodo.setTengoHijos(true);
			}
		}
		// en caso de que el arreglo que contienen los nodos sea distintos a
		// null quiere decir que tienen hijos
		for (int i = 0; i < nodo.getNodo().length; i++) {
			if (nodo.getNodo()[i] != null) {
				nodo.setTengoHijos(true);
				setTengoHijos(nodo.getNodo()[i]);
			}
		}
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	/*
	 * metodo que inserta hijos a los nodos
	 */
	public void ingresarEnHijos(Nodo conHijos, Mensaje valor) {
		boolean entro = false;
		// en caso de con hijos no tenga hijos
		if (conHijos != null && !conHijos.isTengoHijos()) {
			ubicarValorEnArreglo(conHijos, valor);
			entro = true;
		}
		// mientras i sea menor que el dos veces el grado mas uno y en el nodo
		// conHijos sea distinto de nulo
		for (int i = 0; conHijos != null && i < 2 * grado + 1 && !entro; i++) {
			// si la posicion i de el nodo conHijos es distinta a nulo
			if (conHijos.getMensajes()[i] != null) {
				// si el valor numerico del mensaje que vamos a ingresar es
				// menor a el valor numerico del nodo con hijos en la posicion i
				// se ingres el valor en ese nodo
				if (valor.getValor() < conHijos.getMensajes()[i].getValor() || conHijos.getMensajes()[i] == null) {
					entro = true;
					ingresarEnHijos(conHijos.getNodo()[i], valor);
					i = 2 * grado;
				}
			}
			// si el valor es igual a nullnse ingresa el valor en esa posicion
			else if (conHijos.getMensajes()[i] == null) {
				entro = true;
				ingresarEnHijos(conHijos.getNodo()[i], valor);
				i = 2 * grado;
			}
		}
	}

	/*
	 * metodo que ubica los valores de ntro de los nodos
	 */
	public void ubicarValorEnArreglo(Nodo nodoA, Mensaje valor) {
		// se inicializa un contador
		int cont = 0;
		// mientras el contador sea menor que dos veces el grado, si lo que
		// contiene la posicion en el arreglo es nulo mete elvalor ahi
		while (cont <= 2 * grado) {
			if (nodoA.getMensajes()[cont] == null) {
				nodoA.getMensajes()[cont] = valor;
				ordenar(nodoA.getMensajes());
				Lista.ingresados.add(valor);
				// si el contador es mayor que dos veces el grado se debe
				// dividir el nodo
				if (cont == 2 * grado) {
					split(nodoA);
				}
				break;
			}
			cont++;
		}
	}

	/*
	 * Metodo que ordena los nodos segun su valor numerico dentro de los nodos
	 */
	public void ordenarNodos(Nodo aOrdenar) {
		int i, j;
		i = 0;
		Nodo tmp;
		// mientras i sea distinta a dos veces el grado mas 3 yel nodo es
		// distinto a null
		while (i < 2 * grado + 3 && aOrdenar.getNodo()[i] != null) {
			j = 0;
			// mientras j sea distinta de dos veces el grado mas dos vecesylos
			// nodos del nodo aordenar sean distintos de nulo
			while (j < 2 * grado + 2 && aOrdenar.getNodo()[j] != null && aOrdenar.getNodo()[j + 1] != null) {
				// si el primer valor del nodo en la posicion j es mayor a el
				// primer valor del nodo en la posicion j + 1, se intercambian
				// los nodos
				if (aOrdenar.getNodo()[j].getMensajes()[0].getValor() > aOrdenar.getNodo()[j + 1].getMensajes()[0]
						.getValor()) {
					tmp = aOrdenar.getNodo()[j];
					aOrdenar.getNodo()[j] = aOrdenar.getNodo()[j + 1];
					aOrdenar.getNodo()[j + 1] = tmp;
				}
				j++;
			}
			i++;
		}
	}

	/*
	 * metodo que divide los nodos y sube un valor ya que se sobre pasa la
	 * cantidad de valores que contiene el nodo
	 */
	public void split(Nodo nodo) {
		Nodo hijoIzq = new Nodo();
		Nodo hijoDer = new Nodo();
		// si tiene hijos antes de hacer el split entonces
		if (nodo.getNodo()[0] != null) {
			// los separa los hijos del nodo en hijoIzq e hijoDer
			for (int i = 0; i < grado + 1; i++) {
				hijoIzq.getNodo()[i] = nodo.getNodo()[i];
				hijoIzq.getNodo()[i].setPadre(hijoIzq);
				nodo.getNodo()[i] = null;
				hijoDer.getNodo()[i] = nodo.getNodo()[grado + 1 + i];
				hijoDer.getNodo()[i].setPadre(hijoDer);
				nodo.getNodo()[grado + 1 + i] = null;
			}
		}
		// guarda los valores en hijoIzq e hijoDer
		for (int i = 0; i < grado; i++) {
			hijoIzq.getMensajes()[i] = nodo.getMensajes()[i];
			nodo.getMensajes()[i] = null;
			hijoDer.getMensajes()[i] = nodo.getMensajes()[grado + 1 + i];
			nodo.getMensajes()[grado + 1 + i] = null;
		}
		nodo.getMensajes()[0] = nodo.getMensajes()[grado];
		// queda en nodo solo el valor que "subio"
		nodo.getMensajes()[grado] = null;
		// asigna a nodo el nuevo hijo izquierdo
		nodo.getNodo()[0] = hijoIzq;
		// se hizo en primer ciclo
		nodo.getNodo()[0].setPadre(nodo);
		// asigna a nodo el nuevo hijo derecho
		nodo.getNodo()[1] = hijoDer;
		// se hizo en el primer ciclo
		nodo.getNodo()[1].setPadre(nodo);
		setTengoHijos(primerNodo);
		ordenarNodos(nodo);
		// luego del split y asignar los hijos, subir el valor al padre
		if (nodo.getPadre() != null) {
			boolean subido = false;
			// se recorre el nodo padre
			for (int i = 0; i < nodo.getPadre().getMensajes().length && subido == false; i++) {
				// si el valor i del nodo padre es igual a nulo
				if (nodo.getPadre().getMensajes()[i] == null) {
					// se ubica el valor a subir ahi
					nodo.getPadre().getMensajes()[i] = nodo.getMensajes()[0];
					subido = true;
					nodo.getMensajes()[0] = null;
					ordenar(nodo.getPadre().getMensajes());
				}
			}
			// busca donde estan lo hijos en el arreglo
			int posHijos = 0;
			for (int i = 0; i < 2 * grado + 3; i++) {
				if (nodo.getPadre().getNodo()[i] != null) {
					posHijos++;
				} else {
					break;
				}
			}
			// ubica el valor de los hijos en los primeros valores del nodo hijo
			// y reasigna el valor del padre
			nodo.getPadre().getNodo()[posHijos] = nodo.getNodo()[0];
			nodo.getPadre().getNodo()[posHijos + 1] = nodo.getNodo()[1];
			nodo.getPadre().getNodo()[posHijos].setPadre(nodo.getPadre());
			nodo.getPadre().getNodo()[posHijos + 1].setPadre(nodo.getPadre());
			// busca la posicon donde el nodo padre en su arreglo de de nodos en
			// la primera posicion de los valores es igual nodo. valores
			int aqui = 0;
			for (int i = 0; i < 2 * grado + 3 && nodo.getPadre().getNodo()[i] != null; i++) {
				if (nodo.getPadre().getNodo()[i].getMensajes()[0] == nodo.getMensajes()[0]) {
					aqui = i;
					break;
				}
			}
			Nodo papa = nodo.getPadre();
			nodo = null;
			int j = aqui;
			while (j < 2 * grado + 2 && papa.getNodo()[j] != null && papa.getNodo()[j + 1] != null) {
				papa.getNodo()[j] = papa.getNodo()[j + 1];
				j++;
			}
			papa.getNodo()[j] = null;
			ordenar(papa.getMensajes());
			ordenarNodos(papa);
			if (papa.getMensajes()[2 * grado] != null) {
				split(papa);
			}
		}
	}

	/*
	 * metodo que busca un valor numerico dentro del arbol
	 */
	public String buscar(int valor) {
		boolean esta = false;
		// mientras el valor de i sea diferente de del largo del arreglo
		for (int i = 0; i < Lista.ingresados.size() && !esta; i++) {
			// si el valor numerico que buscamos coincide con la posicion del
			// arreglo que bucabamos, retorna el texto de este valor
			if (Lista.ingresados.get(i).getValor() == valor) {
				esta = true;
				return Lista.ingresados.get(i).getTexto();
			}
		}
		return null;
	}

	/*
	 * metodo queretorna el arbol en un string
	 */
	public String recorrer(Nodo nodo) {
		arbol += "\n";
		// mientras i sea distinto de el dos veces el grado mas 1
		for (int i = 0; i < 2 * grado + 1; i++) {
			// si el primer nodo del nodo es distinto de nulo y el valor de i es
			// 0 se aumenta en uno el nivel y se suma uno a imprimir
			if (nodo.getNodo()[i] != null) {
				if (i == 0) {
					nivel++;
					imprimir = 1;
					// en caso de que valor de i no sea 0 se aumenta solo el
					// valor de imprimir
				} else {
					imprimir++;
				}
				// se repite el proceso con los nodos de los nodos
				recorrer(nodo.getNodo()[i]);
			}
			// ademas se guardan todos los valores del nodo en el string arbol
			arbol += "[ ";
			for (int j = 0; nodo.getNodo()[i] != null && j < nodo.getNodo()[i].getMensajes().length; j++) {
				if (nodo.getNodo()[i].getMensajes()[j] != null) {
					arbol += nodo.getNodo()[i].getMensajes()[j].getValor() + ", ";
				}
			}
			arbol += " ]";
		}
		if (arbol.length() > (2 * grado + 3) * 4) {
			// System.out.println (arbol);
			return arbol;
		}
		return arbol;
	}

	/*
	 * metodo que llama a recorrer para crear el arbol
	 */
	public String llamarRecorrer() {
		String mostrar = recorrer(primerNodo);
		nivel = 1;
		imprimir = 1;
		return arbol;
	}

	/*
	 * metodo que determina si un string es un numero
	 */
	public boolean esNumero(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/*
	 * metodo que busca por contenido en el cual le entra una palabra y revisa
	 * el arbol para ver cuales mensajes contienen esa palabra
	 */
	public ArrayList<Mensaje> buscarPorContenido(String palabra) {
		// se crea una nueva lista
		ArrayList<Mensaje> listaTemp = new ArrayList<Mensaje>();
		// se recorre el arreglo donde estan los mensajes
		for (int i = 0; i < Lista.ingresados.size(); i++) {
			String Mensaje = Lista.ingresados.get(i).getTexto();
			String temp = "";
			String letraActual;
			String vacio = " ";
			// se recocore el mensaje letra por letra y cuando se forma un
			// palabra lo compara con la palabra que el usuario digito
			// en caso de estar la palabra buscada en el mensaje, este mensaje
			// se guarda en un arrglo que sera retornado
			for (int j = 0; j < Mensaje.length(); j++) {
				System.out.println(temp);
				letraActual = Character.toString(Mensaje.charAt(j));
				if (String.valueOf(vacio.toLowerCase()).equals(letraActual.toLowerCase())) {
					temp = "";
				}

				else {
					temp += letraActual;
				}
				if (String.valueOf(palabra).equals(temp)) {
					listaTemp.add(Lista.ingresados.get(i));
				}
			}
		}
		return listaTemp;
	}

	public static int getGrado() {
		return grado;
	}

	public static void setGrado(int grado) {
		Raiz.grado = grado;
	}

	public Nodo getPrimerNodo() {
		return primerNodo;
	}

	public void setPrimerNodo(Nodo primerNodo) {
		this.primerNodo = primerNodo;
	}

	public static boolean isEsRaiz() {
		return esRaiz;
	}

	public static void setEsRaiz(boolean esRaiz) {
		Raiz.esRaiz = esRaiz;
	}

	public static int getNivel() {
		return nivel;
	}

	public static void setNivel(int nivel) {
		Raiz.nivel = nivel;
	}

	public static int getImprimir() {
		return imprimir;
	}

	public static void setImprimir(int imprimir) {
		Raiz.imprimir = imprimir;
	}

	public static String getArbol() {
		return arbol;
	}

	public static void setArbol(String arbol) {
		Raiz.arbol = arbol;
	}

	public int getContador() {
		return contador;
	}

	public void aumentarContador() {
		this.contador++;
	}
}