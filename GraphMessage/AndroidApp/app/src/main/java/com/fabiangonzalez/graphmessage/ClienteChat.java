package com.fabiangonzalez.graphmessage;

import android.util.Log;


import org.json.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author Victor Fuentes
 *
 */
public class ClienteChat implements Runnable{

    private final int _puerto = 3333; //Puerto para la conexión
    private String _mensajeServidor; //Mensajes entrantes (recibidos) en el servidor
    private Socket _client; //Socket del cliente
    private DataOutputStream _salidaCliente; //Flujo de datos de salida
    private DataInputStream _entradaCliente;
    private IniciarChat _init;
    private InstanciaChat _instChat = InstanciaChat.getSingletonInstance(_init);
    private InstanciaClienteChat _instClienteChat;
    //Enviar copia al server
    private Cliente _cli;
    private InstanciaCliente _instCli = InstanciaCliente.getSingletonInstance(_cli);
    private ArrayList _arra = new ArrayList();

    /**
     * Este es el constructor de la clase
     */
    public ClienteChat(String pHost){
        Log.v(this.getClass().getClass().getCanonicalName(), "Socket " + pHost + " " + _puerto);
        try {
            _init = _instChat.get_chatCreado();
            _client = new Socket(pHost,_puerto);
            Log.v(getClass().getCanonicalName(), "Se ha iniciado conexion...");
            _salidaCliente = new DataOutputStream(_client.getOutputStream());
            Thread clienteThread = new Thread(this);//Corre el hilo que mantiene en escucha al cliente
            clienteThread.start();
            System.out.println("Conectado");
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método se encarga de enviar los datos al servidor
     */
    public void enviarDatos(String pMensaje){
        try {
            System.out.println(pMensaje + "Mensaje");
            _arra.add(pMensaje);
            _instCli.get_clienterecibido().enviarDatos(_arra);
            _salidaCliente.writeUTF(pMensaje);
            _salidaCliente.flush();
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método es el que mantiene al cliente esperando los mensajes del servidor
     */
    public void run() {
        try{
            while(true) {
                _entradaCliente = new DataInputStream(_client.getInputStream());
                System.out.println("+++++++++++++++++++++++++++++++*******************");
                _mensajeServidor = _entradaCliente.readUTF();
                System.out.println(_mensajeServidor);
                this.enviarDatos("115total115");
                if(_mensajeServidor.equals("115total115")){
                    System.out.println("Patrón encontrado }}}}}}}}}}}}}}}}}}}}}}}}}}}");
                    confirmar();
                }else {
                    obtenerMensaje(_mensajeServidor);
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void obtenerMensaje(String pMensaje){
        //Revisar este código ********************************************************************
        //****************************************************************************************
        _init.llenarChat(pMensaje);
    }

    public void confirmar(){
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        _init.confirmado();
    }
}