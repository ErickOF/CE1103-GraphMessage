package com.fabiangonzalez.graphmessage;

public class InstanciaChatServer {
    private static InstanciaChatServer _servidor;
    private static IniciarChatServer _chatCreado;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private InstanciaChatServer(IniciarChatServer pChat) {
        this._chatCreado = pChat;
        System.out.println("Mi nombre es: " + this._chatCreado);
    }

    public static InstanciaChatServer getSingletonInstance(IniciarChatServer pChat) {
        if (_chatCreado == null) {
            _servidor = new InstanciaChatServer(pChat);
        } else {
            System.out.println("No se puede crear el objeto " + pChat + " porque ya existe un objeto de la clase");
        }
        return _servidor;
    }

    public IniciarChatServer get_chatCreado() {
        return _chatCreado;
    }
}
