package com.fabiangonzalez.graphmessage;

public class InstanciaCliente {
    private static InstanciaCliente _cliente;
    private static Cliente _clienterecibido;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private InstanciaCliente(Cliente pCliente) {
        this._clienterecibido = pCliente;
        System.out.println("Mi nombre es: " + this._clienterecibido);
    }

    public static InstanciaCliente getSingletonInstance(Cliente pCliente) {
        if (_cliente == null) {
            _cliente = new InstanciaCliente(pCliente);
        } else {
            System.out.println("No se puede crear el objeto " + pCliente + " porque ya existe un objeto de la clase");
        }
        return _cliente;
    }

    public Cliente get_clienterecibido() {
        return _clienterecibido;
    }

}