package com.fabiangonzalez.graphmessage;

import android.util.Log;


import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author Victor Fuentes
 *
 */
public class Cliente implements Runnable{

    private final int _puerto = 2222; //Puerto para la conexión
    private final String _host = "172.26.100.7"; //Host para la conexión
    private JSONObject _mensajeServidor; //Mensajes entrantes (recibidos) en el servidor
    private Socket _client; //Socket del cliente
    private ObjectOutputStream _salidaCliente; //Flujo de datos de salida
    private ObjectInputStream _entradaCliente;
    private ArrayList _mensaje;
    private String _cadena1, _cadena2;

    private Bluetooth _bluet;
    private Selection _pantSelection;
    private ArrayList<String> _aEnviar = new ArrayList<>();
    private JSONObject _obj;
    /**
     * Este es el constructor de la clase
     */
    public Cliente(Bluetooth pBluet,String pAddress,String pNombre,String pIP){
        Log.v(this.getClass().getClass().getCanonicalName(), "Socket " + _host + " " + _puerto);
        try {
            _bluet = pBluet;
            _client = new Socket(_host,_puerto);
            Log.v(getClass().getCanonicalName(), "Se ha iniciado conexion...");
            _salidaCliente = new ObjectOutputStream(_client.getOutputStream());
            Thread clienteThread = new Thread(this);//Corre el hilo que mantiene en escucha al cliente
            clienteThread.start();
            System.out.println("Conectado");
            _aEnviar.add(pNombre);
            _aEnviar.add(pAddress);
            _aEnviar.add(pIP);
            enviarDatosPersonal(_aEnviar);
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método se encarga de enviar los datos al servidor
     * Enviar copia del mensaje
     */
    public void enviarDatos(ArrayList pMensaje){
        try {
            _obj = new JSONObject();
            _obj.put("Copia",pMensaje);
            _salidaCliente.writeObject(_obj);
            _salidaCliente.flush();
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Este método se encarga de enviar los datos al servidor
     * Enviar nombre, mac address e ip
     */
    public void enviarDatosPersonal(ArrayList pMensaje){
        try {
            _obj = new JSONObject();
                _obj.put("Mensaje",pMensaje);
            _salidaCliente.writeObject(_obj);
            _salidaCliente.flush();
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método se encarga de enviar los datos al servidor
     * Pedir lista de usuarios conectados
     */
    public void enviarDatosSelect(ArrayList pMensaje,Selection pPantallaSelection){
        _pantSelection = pPantallaSelection;
        try {
            _obj = new JSONObject();
            _obj.put("Lista",pMensaje);
            _salidaCliente.writeObject(_obj);
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
                _entradaCliente = new ObjectInputStream(_client.getInputStream());
                _mensajeServidor = (JSONObject)_entradaCliente.readObject();
                _mensaje = (ArrayList) _mensajeServidor.get("Mensaje");
                System.out.println(_mensaje.get(0) + "  Primer elemento    " + _mensaje.get(_mensaje.size()-1));
                _cadena1 = (String) _mensaje.get(_mensaje.size()-1);
                if(_cadena1.equals("verificar")){
                    System.out.println("Verificando........");
                    _cadena2 = (String) _mensaje.get(0);
                    if(_mensaje.get(0).equals("Aceptado")){
                        System.out.println("Aceptado!!!");
                        _bluet.cambiarPantalla();
                    }else{
                        System.out.println("Rechazado!!!");
                        //_bluet.mostrarEstado();
                    }
                }else if(_cadena1.equals("Lista")){
                    System.out.println("Eso es, he aquí la lista");
                    _pantSelection.cargarLista(_mensaje);
                }

            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}