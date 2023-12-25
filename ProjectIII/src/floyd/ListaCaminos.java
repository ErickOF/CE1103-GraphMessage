package floyd;

import java.util.ArrayList;

public class ListaCaminos {
	ArrayList<Camino> rutasMinimas;

	public ListaCaminos(String[] rutas) {
		rutasMinimas = new ArrayList<>();
		for (int i = 0; i < rutasMinimas.size(); i++) {
			String[] rutaMin = rutas[i].split(",");
			Camino camino = new Camino(rutaMin[rutaMin.length - 1], rutaMin);
			rutasMinimas.add(camino);
		}
	}

	public ArrayList<Camino> getRutasMinimas() {
		return rutasMinimas;
	}

	public void setRutasMinimas(ArrayList<Camino> rutasMinimas) {
		this.rutasMinimas = rutasMinimas;
	}
}