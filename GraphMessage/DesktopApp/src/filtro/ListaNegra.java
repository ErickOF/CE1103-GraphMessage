package filtro;

public class ListaNegra<T> {
	private Nodo<T> _primero;
	private int _capacidad;
	private T _valor;

	/**
	 * Constructor por defecto
	 */
	public ListaNegra() {
		_primero = null;
		_capacidad = 0;
	}

	/**
	 * Verifica si la lista esta vacia
	 */
	public boolean estaVacia() {
		return _primero == null;
	}

	public int getCapacidad() {
		return _capacidad;
	}

	public void banear(T _elemento) {
		Nodo<T> _nuevo = new Nodo<T>(_elemento);
		if (estaVacia()) {
			_primero = _nuevo;
		} else {
			_nuevo.setSiguiente(_primero);
			_primero = _nuevo;
		}
		_capacidad++;
	}

	public boolean buscar(T _elemento) {
		Nodo<T> _temp = _primero;
		boolean _encontrado = false;

		while (_temp != null && _encontrado != true) {
			if (_elemento == _temp.getDato()) {
				_encontrado = true;
			} else {
				_temp = _temp.getSiguiente();
			}
		}
		return _encontrado;
	}

	public void imprimir() {
		if (!estaVacia()) {
			Nodo<T> _temp = _primero;
			while (_temp != null) {
				System.out.println("[" + _temp.getDato() + "]");
				_temp = _temp.getSiguiente();
			}
		}
	}
}