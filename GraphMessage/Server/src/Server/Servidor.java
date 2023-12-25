package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import cliente.ListaNegra;
import graphmessagevisualizer.Visualizer;

/**
 * 
 * @author Víctor Fuentes Vargas
 * Esta clase posee el servidor al cual se conectan los clientes para acceder al juego.
 */
public class Servidor implements Runnable{
	private final int _puerto = 2222; //Puerto para la conexión
	private ServerSocket _server; //Socket del servidor
	private Socket _client; //Socket del cliente
	private int _cont;
	private Thread _tServer;
	private ServerClient _serverClient;
	private ListaNegra _lnegra;
	private ObjectInputStream _inpStream;
	
	/**
	 * Este método es el constructor de la clase.
	 */
	public Servidor(){
		System.out.println("Iniciando servidor");
		_lnegra = new ListaNegra<>();
		_tServer = new Thread(this);
		_tServer.start();
		_cont = 0;
		Visualizer ventanaPrincipal = new Visualizer();
		ventanaPrincipal.setVisible(true);
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
				_inpStream = new ObjectInputStream(_client.getInputStream());
				System.out.println("Conectado");
				_cont++;
				_serverClient = new ServerClient(_client,_lnegra,_inpStream);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
