package com.fabiangonzalez.graphmessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Víctor Fuentes Vargas
 * Esta clase posee el servidor al cual se conectan los clientes para acceder al juego.
 */
public class Servidor implements Runnable {
    private final int _puerto = 3333; //Puerto para la conexión
    private ServerSocket _server; //Socket del servidor
    private Socket _client; //Socket del cliente
    private Thread _tServer;
    private ServerClient _serverClient;
    private Bluetooth _pantallaCrear;
    private DataOutputStream _salidaCliente;
    private IniciarChatServer _init;
    private InstanciaChatServer _instChat = InstanciaChatServer.getSingletonInstance(_init);
    private Selection _sel;
    private InstanciaSelection _select;

    /**
     * Este método es el constructor de la clase.
     */
    public Servidor(Bluetooth pPantallaCrear){
        _pantallaCrear = pPantallaCrear;
        _init = _instChat.get_chatCreado();
        _pantallaCrear = pPantallaCrear;
        System.out.println("Iniciando servidor");
        _tServer = new Thread(this);
        _tServer.start();

    }

    /**
     * Este método es donde se ejecuta el hilo que mantiene al servidor escuchando para aceptar conexiones.
     */
    public void run() {
        try {
            System.out.println("Esperando conexion");
            _server = new ServerSocket(_puerto);
            while(true){
                _client = _server.accept(); //Acepta las conexiones que lleguen
                System.out.println("Conectado");
                _select = InstanciaSelection.getSingletonInstance(_sel);
                _select.get_select().cambiarBanderaBoton();
                _serverClient = new ServerClient(Servidor.this,_client);

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writter(String pDato){
        try{
            _salidaCliente = new DataOutputStream(_client.getOutputStream());
            _salidaCliente.writeUTF(pDato);
            _salidaCliente.flush();
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void obtenerMensaje(String pMensaje){
        //Revisar este código ********************************************************************
        //****************************************************************************************
        _init.llenarChat(pMensaje);
    }
}
