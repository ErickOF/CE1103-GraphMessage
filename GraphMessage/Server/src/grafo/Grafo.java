package grafo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Clase Grafo
 * 
 * @author Erick
 *
 */
public class Grafo {
	// Declarar variables a utilizar
	private static Grafo grafo;
	private ArrayList<Nodo> listaNodo;
	private int[][] matrizDeCostos;
	private Random random;
	private int tamaño;
	// CONSTANTES a utilizar
	private static final int cantNodos = 30;
	public static final int INFINITO = 999999999;
	private static final String[] LISTA_NICKNAME = { "Blandinus", "Javed", "Moreen", "Porphyrios", "Malcom", "Susann",
			"Slawomira", "Friduman", "Shaquila", "Hormazd", "Sabia", "Nicomedes", "Tatius", "Dikla", "Kelsi", "Florina",
			"Lucja", "Nomiki", "Lehi", "Maiken", "Teodor", "Jennifer", "Haribert", "Epiphanes", "Cyra", "Sheard",
			"Milivoj", "Izabel", "Ada", "Ahmose" };

	/**
	 * Contructor de la clase
	 * 
	 * Se declara privado ya que solo va haber un grafo para la red conectada
	 */
	private Grafo() {
		this.listaNodo = new ArrayList<Nodo>();
		random = new Random();
		tamaño = 0;
		// Llamar metodo que se encarga de generar los nodos del grafo
		generarGrafo();
		// Llamar metodo que se encargar de enlazar los nodos del grafo
		enlazarGrafo();
		// Llamar metodo que genera la matriz de pesos
		generarMatrizDePesos();
	}

	/**
	 * Obtener el Grafo
	 * 
	 * @return
	 */
	public static Grafo getGrafo() {
		// Si el grafo es null se instancia
		if (grafo == null) {
			grafo = new Grafo();
		} // Retornar el grafo
		return grafo;
	}

	/**
	 * Metodo que se encarga de buscar un nodo por medio del nickname
	 * 
	 * @param dato
	 * @return
	 */
	public Nodo buscarNodo(Object dato) {
		// Crear variable temporal
		Nodo temp = null;
		// Si el dato no esta vacio
		if (dato != null) {
			// Se empieza a buscar nodos en la lista
			for (Nodo nodo : listaNodo) {
				// Hasta que el dato calce con el nickname
				if (dato.equals(nodo.getNickname())) {
					// Se asigna el temporal al nodo
					temp = nodo;
				}
			}
		} // Se retorna el nodo
		return temp;
	}

	/**
	 * Metodo que se encarga de crear el enlace en una sola direccion
	 * 
	 * @param padre
	 * @param adyacente
	 * @param arista
	 */
	public void crearEnlacesDirigido(Nodo padre, Nodo adyacente, Arista arista) {
		// Si en nodo padre y el nodo adyacentes no estan vacios
		if (padre != null && adyacente != null) {
			// Se agrega el adyacente y la arista al padre
			padre.addNodoAdyacente(arista, adyacente);
		}
	}

	/**
	 * Metodo que se encarga de enlazar los nodos en ambas direcciones
	 * 
	 * @param padre
	 * @param adyacente
	 * @param arista
	 */
	public void crearEnlacesNoDirigido(Nodo padre, Nodo adyacente, Arista arista) {
		// Crear enlace en ambas direcciones
		crearEnlacesDirigido(padre, adyacente, arista);
		crearEnlacesDirigido(adyacente, padre, arista);
	}

	/**
	 * Metodo que se encarga de enlazar los nodos cercanos
	 */
	public void enlazarGrafo() {
		// Recorrer para ver a quien se puede enlazar el nodo
		for (int i = 0; i < listaNodo.size(); i++) {
			// Nodo al cual se le va a enlazar nodos cercanos
			Nodo nodoInicio = listaNodo.get(i);
			for (int j = 0; j < listaNodo.size(); j++) {
				// Nodo el cual se desea saber si se puede enlazar
				Nodo nodoFin = listaNodo.get(j);
				// Guardar la distancia en x que hay entre los nodos
				int distanciaX = Math.abs(nodoInicio.getCirculo().getX() - nodoFin.getCirculo().getX());
				// Guardar la distancia en y que hay entre los nodos
				int distanciaY = Math.abs(nodoInicio.getCirculo().getY() - nodoFin.getCirculo().getY());
				// Distancia total que se obtiene usando pitagoras
				int distanciaTotal = (int) (Math.sqrt(Math.pow(distanciaX, 2) + Math.pow(distanciaY, 2)) / 2);
				// Si la distancia que se obtuvo es menor que 80
				if (distanciaTotal < 80) {
					// Se instancia una nueva arista
					Arista arista = new Arista();
					// Dar un peso a la arista (la distancia total entre nodos)
					arista.setPeso(distanciaTotal);
					// Instanciar una nueva coordenada
					Coordenadas coordenadas = new Coordenadas(100000, 100000);
					// Agregar las coordenadas
					coordenadas.addCoordenada(
							nodoInicio.getCirculo().getX() + (nodoInicio.getCirculo().getDiametro() / 2),
							nodoInicio.getCirculo().getY() + (nodoInicio.getCirculo().getDiametro() / 2));
					coordenadas.addCoordenada(nodoFin.getCirculo().getX() + (nodoInicio.getCirculo().getDiametro() / 2),
							nodoFin.getCirculo().getY() + (nodoInicio.getCirculo().getDiametro() / 2));
					// Instanciar una linea quebrada
					LineaQuebrada lq = new LineaQuebrada(coordenadas);
					// Agregar la linea a la arista
					arista.setLineaQuebrada(lq);
					// Enlazar los nodos
					crearEnlacesNoDirigido(nodoInicio, nodoFin, arista);
				}
			}
		}
	}

	/**
	 * Metodo que se encarga de crear los 30 nodos del grafo automaticamente
	 */
	public void generarGrafo() {
		for (int i = 0; i < cantNodos; i++) {
			// Situar el nodo
			Coordenadas coordenadas = new Coordenadas(100000, 100000, random.nextInt(550), random.nextInt(550));
			// Crear el nodo
			Nodo nodo = new Nodo(LISTA_NICKNAME[i], coordenadas);
			// Dar el MacAdress del dispositivo
			nodo.setMacAdress(generarMacAdress());
			// Configurar el circulo que representa el nodo
			nodo.getCirculo().setDiametro(12);
			nodo.getCirculo().setEtiqueta(nodo.getNickname() + "");
			// Agregar el nuevo nodo
			listaNodo.add(nodo);
			// Aumentar tamaño del grafo
			tamaño++;
		}
	}

	/**
	 * Metodo que genera un MacAdress al azar
	 * 
	 * @return
	 */
	public String generarMacAdress() {
		// Posibles valores para el MacAdress
		String[] posiblesCodigos = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
		// Variable para el mac adress
		String macAdress = "";
		// Contador para cada dos caracteres agregar :
		int cont = 0;
		for (int i = 0; i < 12; i++) {
			// Verificar si se han agregado 2 caracteres
			if (cont == 2) {
				// Agregar : al mac adress
				macAdress += ":";
				// Reiniciar el contador
				cont = 0;
			} // Agregar codigo al azar
			macAdress += posiblesCodigos[random.nextInt(posiblesCodigos.length)];
			cont++;
		} // Retornar el MacAdress
		return macAdress;
	}

	/**
	 * Metodo que se encarga crear la matriz de pesos del grafo
	 */
	public void generarMatrizDePesos() {
		// Instanciar la matriz de pesos
		matrizDeCostos = new int[tamaño][tamaño];
		// Recorrer las filas
		for (int i = 0; i < tamaño; i++) {
			// Recorrer las columnas
			for (int j = 0; j < tamaño; j++) {
				// Tomar pares de nodos para evaluar el peso de las rutas
				Nodo nodo1 = listaNodo.get(i);
				Nodo nodo2 = listaNodo.get(j);
				// Ver si entre los nodos hay ruta
				if (isAdyacente(nodo1, nodo2)) {
					// Si la hay se agrega el costo
					matrizDeCostos[j][i] = nodo1.buscarEnlace(nodo2.getNickname().toString()).getArista().getPeso();
				} // Sino hay ruta
				else {
					// Se coloca el INFINITO para indicarlo
					matrizDeCostos[j][i] = INFINITO;
				}
			}
		}
	}

	// Getters and Setters

	/**
	 * Metodo que busca los nodos adyacentes
	 * 
	 * @param dato
	 * @return
	 */
	public ArrayList<Nodo> getAdyacentes(Object dato) {
		// Se crea una lista
		ArrayList<Nodo> lista = new ArrayList<>();
		// Se busca el nodo el cual se quiere los nodos adyacentes
		Nodo principal = buscarNodo(dato);
		// Se obtiene las aristas
		ArrayList<Enlace> aristas = principal.getListaNodoAdyacente();
		// Si la arista es diferente de nulo
		if (aristas != null) {
			// Se recorre y se agregan los nodos enlazados
			for (int i = 0; i < aristas.size(); i++) {
				lista.add(aristas.get(i).getNodo());
			}
		} // Retornar la lista con los nodos enlazados
		return lista;
	}

	/**
	 * Obtener los nodos del grafo
	 * 
	 * @return
	 */
	public ArrayList<Nodo> getListaNodos() {
		return listaNodo;
	}

	/**
	 * Verificar si un nodo esta enlazado con otro
	 * 
	 * @param nodo1
	 * @param nodo2
	 * @return
	 */
	public boolean isAdyacente(Nodo nodo1, Nodo nodo2) {
		// Obtener los nodos enlazados con el primero
		ArrayList<Enlace> listaAristas = nodo1.getListaNodoAdyacente();
		// Si la lista de aristas no esta vacia
		if (listaAristas != null) {
			// Recorrer la lista de aristas
			for (int i = 0; i < listaAristas.size(); i++) {
				// Si se encuentra el nodo se retorna true
				if (listaAristas.get(i).getNodo() == nodo2) {
					return true;
				}
			}
		} // Si el nodo no esta enlazado se retorna false
		return false;
	}

	/**
	 * Metodo que verifica si dos nodos estan enlazados por medio de sus
	 * nicknames
	 * 
	 * @param datoNodoInicio
	 * @param datoNodoDestino
	 * @return
	 */
	public boolean isAdyacente(Object datoNodoInicio, Object datoNodoDestino) {
		// Buscar ambos nodos en el grafo
		Nodo nodo1 = buscarNodo(datoNodoInicio);
		Nodo nodo2 = buscarNodo(datoNodoDestino);
		// Obtener los nodos enlazados con el primero
		ArrayList<Enlace> listaAristas = nodo1.getListaNodoAdyacente();
		// Si la lista de aristas no esta vacia
		if (listaAristas != null) {
			// Recorrer la lista de aristas
			for (int i = 0; i < listaAristas.size(); i++) {
				// Si se encuentra el nodo se retorna true
				if (listaAristas.get(i).getNodo() == nodo2) {
					return true;
				}
			}
		} // Si el nodo no esta enlazado se retorna false
		return false;
	}

	/**
	 * Obtner la arista por medio de sus nicknames
	 * 
	 * @param datoNodo1
	 * @param datoNodo2
	 * @return
	 */
	public Arista getArista(Object datoNodo1, Object datoNodo2) {
		return getArista(buscarNodo(datoNodo1), buscarNodo(datoNodo2));
	}

	/**
	 * Obtner la arista por medio de los nodos
	 * 
	 * @param nodo1
	 * @param nodo2
	 * @return
	 */
	public Arista getArista(Nodo nodo1, Nodo nodo2) {
		// Declarar la arista null
		Arista aux = null;
		// Verificar si los nodos son adyacentes
		if (isAdyacente(nodo1, nodo2)) {
			// Obtener la lista de aristas del primer nodo
			ArrayList<Enlace> listaAristas = nodo1.getListaNodoAdyacente();
			// Recorrer la lista de aristas
			for (int i = 0; i < listaAristas.size(); i++) {
				// Si los nodos son iguales
				if (listaAristas.get(i).getNodo() == nodo2) {
					// Se le asigna al auxiliar la arista
					aux = listaAristas.get(i).getArista();
				}
			}
		} // Se verifica si estan enlazados al reves
		else if (isAdyacente(nodo2, nodo1)) {
			// Y al auxiliar se le asigna la arista
			aux = getArista(nodo2, nodo1);
		} // Retornar lo de la auxiliar
		return aux;

	}

	/**
	 * Obtener el enlace del nodo
	 * 
	 * @param datoNodo1
	 * @param datoNodo2
	 * @return
	 */
	public Enlace getEnlace(Object datoNodo1, Object datoNodo2) {
		Enlace aux = null;
		if (isAdyacente(datoNodo1, datoNodo2)) {
			Nodo n1 = buscarNodo(datoNodo1);
			Nodo n2 = buscarNodo(datoNodo2);
			ArrayList<Enlace> listaAristas = n1.getListaNodoAdyacente();
			for (int i = 0; i < listaAristas.size(); i++) {
				if (listaAristas.get(i).getNodo() == n2) {
					aux = listaAristas.get(i);
				}
			}
		} else if (isAdyacente(datoNodo2, datoNodo1)) {
			aux = getEnlace(datoNodo2, datoNodo1);
		}
		return aux;
	}

	public boolean eliminarNodo(Nodo nodo) {
		boolean retornado = false;
		if (nodo != null) {
			retornado = listaNodo.remove(nodo);
		}
		return retornado;
	}

	public void reiniciarColores() {
		if (listaNodo != null) {
			for (Nodo nodo : listaNodo) {
				nodo.getCirculo().setColor(Color.yellow);
				ArrayList<Enlace> la = nodo.getListaNodoAdyacente();
				if (la != null) {
					for (Enlace enlace : la) {
						if (enlace.getArista().isHabilitado()) {
							enlace.getArista().getLineaQuebrada().setColor(Color.black);
							enlace.getArista().getLineaQuebrada().setGrosorLinea(1);
						}
					}
				}
			}
		}
	}

	public int[][] getMatrizDeCostos() {
		return this.matrizDeCostos;
	}

	public void setMatrizDeCostos(int[][] matrizDeCostos) {
		this.matrizDeCostos = matrizDeCostos;
	}

	public int getTamaño() {
		return this.tamaño;
	}
}