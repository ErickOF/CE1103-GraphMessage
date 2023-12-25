package com.fabiangonzalez.graphmessage;

import java.util.ArrayList;

public class InstanciaMensajes {
    private static InstanciaMensajes _mensajes;
    private static ArrayList<CargarEstiloChat> _msjInstanciado = null;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private InstanciaMensajes(ArrayList<CargarEstiloChat> pMensaje) {
        this._msjInstanciado = pMensaje;
        System.out.println("Mi nombre es: " + this._msjInstanciado);
    }

    public static InstanciaMensajes getSingletonInstance(ArrayList<CargarEstiloChat> pMensaje) {
        if (_msjInstanciado == null) {
            _mensajes = new InstanciaMensajes(pMensaje);
        } else {
            System.out.println("No se puede crear el objeto " + pMensaje + " porque ya existe un objeto de la clase");
        }
        return _mensajes;
    }

    public ArrayList<CargarEstiloChat> get_mensajes() {
        return _msjInstanciado;
    }

    public void set_mensajes(CargarEstiloChat pMensaje) {
        this._msjInstanciado.add(pMensaje);
    }
}
