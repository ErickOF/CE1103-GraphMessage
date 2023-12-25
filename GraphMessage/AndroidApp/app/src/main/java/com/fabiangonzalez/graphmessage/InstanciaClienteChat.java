package com.fabiangonzalez.graphmessage;


public class InstanciaClienteChat {
    private static InstanciaClienteChat _cliente;
    private static ClienteChat _clienterecibido;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private InstanciaClienteChat(ClienteChat pCliente) {
        this._clienterecibido = pCliente;
        System.out.println("Mi nombre es: " + this._clienterecibido);
    }

    public static InstanciaClienteChat getSingletonInstance(ClienteChat pCliente) {
        if (_clienterecibido == null) {
            _cliente = new InstanciaClienteChat(pCliente);
        } else {
            System.out.println("No se puede crear el objeto " + pCliente + " porque ya existe un objeto de la clase");
        }
        return _cliente;
    }

    public ClienteChat get_clienterecibido() {
        return _clienterecibido;
    }

    public void set_clienterecibido(ClienteChat pCliente) {
        this._clienterecibido = pCliente;
    }

}
