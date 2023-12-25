package com.fabiangonzalez.graphmessage;

public class InstanciaChat {
    private static InstanciaChat _servidor;
    private static IniciarChat _chatCreado;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private InstanciaChat(IniciarChat pChat) {
        this._chatCreado = pChat;
        System.out.println("Mi nombre es: " + this._chatCreado);
    }

    public static InstanciaChat getSingletonInstance(IniciarChat pChat) {
        if (_chatCreado == null) {
            _servidor = new InstanciaChat(pChat);
        } else {
            System.out.println("No se puede crear el objeto " + pChat + " porque ya existe un objeto de la clase");
        }
        return _servidor;
    }

    public IniciarChat get_chatCreado() {
        return _chatCreado;
    }


}
