package filtro;

public class Nodo<T> {
	private T _dato;
	private Nodo<T> _siguiente;
	private Nodo<T> _anterior;

	/**
	 * Constructor por defecto del nodo
	 */
	public Nodo() {
		_siguiente = null;
	}

	/**
	 * Metodo que le pasa un dato al nodo
	 * 
	 * @param _elemento
	 */
	public Nodo(T _elemento) {
		_siguiente = null;
		_dato = _elemento;
	}

	public T getDato() {
		return _dato;
	}

	public void setDato(T _elemento) {
		this._dato = _elemento;
	}

	public Nodo<T> getSiguiente() {
		return _siguiente;
	}

	public void setSiguiente(Nodo<T> _siguiente) {
		this._siguiente = _siguiente;
	}

	public Nodo<T> getAnterior() {
		return _anterior;
	}

	public void setAnterior(Nodo<T> _anterior) {
		this._anterior = _anterior;
	}
}