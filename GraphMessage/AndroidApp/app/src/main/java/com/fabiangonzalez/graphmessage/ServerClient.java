package com.fabiangonzalez.graphmessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Víctor Fuentes Vargas
 * Esta clase se encarga crear ServerClients apartir de los clientes que se han conectado al servidor.
 * A cada cliente se le asigna un número.
 */
public class ServerClient implements Runnable{
    protected String _mensajeServidor; //Mensajes entrantes (recibidos) en el servidor
    private  Socket _cliente; //Socket del cliente
    private DataOutputStream _salidaCliente; //Flujo de datos de salida
    private DataInputStream _entradaCliente;
    private Thread _reader;
    private Servidor _server;
    private IniciarChatServer _init;
    private InstanciaChatServer _instChat = InstanciaChatServer.getSingletonInstance(_init);

    /**
     *
     * @param pCliente Recibe el Socket del cliente que se conectó al servidor.
     */
    public ServerClient(Servidor pServer,Socket pCliente){
        super();
        _server = pServer;
        _cliente = pCliente;
        _reader = new Thread(this);
        _reader.start();
    }

    /**
     * Este método se encarga de recibir los datos que le envian los clientes al servidor.
     */
    public void run() {
        try{
            while(true){
                _entradaCliente = new DataInputStream(_cliente.getInputStream());
                System.out.println(_entradaCliente);
                _mensajeServidor = _entradaCliente.readUTF();
                System.out.println(_mensajeServidor + "  Este es el mensaje enviado por el servidor");
                if(_mensajeServidor.equals("115total115")){
                    System.out.println("Patrón encontrado }}}}}}}}}}}}}}}}}}}}}}}}}}}");
                    confirmar();
                }else {
                    obtenerMensaje(_mensajeServidor);
                }
            }
        }catch(Exception e){
            System.out.println("errores");
            System.out.println(e.getMessage());
        }
    }
    public void obtenerMensaje(String pMensaje){
        //Revisar este código ********************************************************************
        //****************************************************************************************
        System.out.println("Patrfsfsdfsdfsddddd}}}}}}}}}}}}}}}}}}}}}}}");
        _init.llenarChat(pMensaje);
    }

    public void confirmar(){
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        _init.confirmado();
    }

}

