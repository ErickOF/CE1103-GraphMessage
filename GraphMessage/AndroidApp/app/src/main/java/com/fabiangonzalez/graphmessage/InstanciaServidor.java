package com.fabiangonzalez.graphmessage;

public class InstanciaServidor {
    private static InstanciaServidor _servidor;
    private static Servidor _servidorCreado;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private InstanciaServidor(Servidor pServer) {
        this._servidorCreado = pServer;
        System.out.println("Mi nombre es: " + this._servidorCreado);
    }

    public static InstanciaServidor getSingletonInstance(Servidor pServer) {
        if (_servidorCreado == null) {
            _servidor = new InstanciaServidor(pServer);
        } else {
            System.out.println("No se puede crear el objeto " + pServer + " porque ya existe un objeto de la clase");
        }
        return _servidor;
    }

    public Servidor get_servidorCreado() {
        return _servidorCreado;
    }


}
