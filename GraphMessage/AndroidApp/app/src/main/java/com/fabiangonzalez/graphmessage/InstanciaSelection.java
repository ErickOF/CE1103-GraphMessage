package com.fabiangonzalez.graphmessage;


public class InstanciaSelection{
    private static InstanciaSelection _instSelection;
    private static Selection _selection;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private InstanciaSelection(Selection pMensaje) {
        this._selection= pMensaje;
        System.out.println("Mi nombre es: " + this._selection);
    }

    public static InstanciaSelection getSingletonInstance(Selection pMensaje) {
        if (_selection == null) {
            _instSelection = new InstanciaSelection(pMensaje);
        } else {
            System.out.println("No se puede crear el objeto " + pMensaje + " porque ya existe un objeto de la clase");
        }
        return _instSelection;
    }

    public Selection get_select() {
        return _selection;
    }

    public void set_select(Selection pMensaje) {
        this._selection = pMensaje;
    }
}