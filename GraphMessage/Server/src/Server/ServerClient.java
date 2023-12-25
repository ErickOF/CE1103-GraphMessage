package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import cliente.Cliente;
import cliente.ListaNegra;
import graphmessagevisualizer.ListaClientes;

/**
 * 
 * @author Víctor Fuentes Vargas
 * Esta clase se encarga crear ServerClients apartir de los clientes que se han conectado al servidor.
 * A cada cliente se le asigna un número.
 */
public class ServerClient implements Runnable{
	private Object _mensajeServidor; //Mensajes entrantes (recibidos) en el servidor
	private  Socket _socketcliente; //Socket del cliente
	private ObjectOutputStream _salidaCliente; //Flujo de datos de salida
	private ObjectInputStream _entradaCliente;
	private Integer _numCliente;
	private Thread _reader;
	private JsonParser _parser;
	private Cliente _cliente;
	private Boolean _bandera; //Determina el procesimiento que se realizará.
	private ListaNegra _lnegra;
	private String _estado;
	private ArrayList<String> _array, _datosObtenidos;
	private Object _obj;
	private ObjectInputStream _inpStream;
	private ListaClientes _listaClientes = ListaClientes.getSingletonInstance(); 
	
	private String _macAddress;
	
	/**
	 * 
	 * @param pCliente Recibe el Socket del cliente que se conectó al servidor.
	 * @param pNumCliente Recibe el número de cliente.
	 */
	public ServerClient(Socket pCliente,ListaNegra pNegra,ObjectInputStream pInput){
		super();
		_inpStream = pInput;
		_parser = new JsonParser();
		_lnegra = pNegra;
		_bandera = false;
		_socketcliente = pCliente;
		_reader = new Thread(this);
		_reader.start();
	}
	
	/**
	 * Este método se encarga de recibir los datos que le envian los clientes al servidor.
	 */
	public void run() {
		try{
			while(true){
				System.out.println("1");
				_entradaCliente = _inpStream;
				System.out.println("2");
				_mensajeServidor = _entradaCliente.readObject();
				System.out.println("3");
				_datosObtenidos = _parser.leer_Json(_mensajeServidor);
				System.out.println("4");
				System.out.println(_bandera);
				if(_bandera){
					System.out.println(_datosObtenidos.get(0));
				}else{
					System.out.println("5");
					System.out.println(_datosObtenidos.get(_datosObtenidos.size()-1).equals("Lista"));
					if(_datosObtenidos.get(_datosObtenidos.size()-1).equals("Lista")){
						//Enviar lista de usuarios conectados
						System.out.println("6");
						
						ArrayList<String> _ar = new ArrayList<String>();
						for(int i=0;i<_listaClientes.getClientes().size();i++){
							if(_listaClientes.getClientes().get(i).getMacAddress().equals(_macAddress)){
								
							}else{
								_ar.add(_listaClientes.getClientes().get(i).getNombre());
								_ar.add(_listaClientes.getClientes().get(i).getIP());
							}
						}
						_ar.add("Lista");
						_bandera = true;
						System.out.println("7");
						writter2(_ar,"Lista");
					}else if(_datosObtenidos.get(_datosObtenidos.size()-1).equals("Mensaje")){
						_cliente = new Cliente(_datosObtenidos.get(0),_datosObtenidos.get(1),_datosObtenidos.get(2));
						_listaClientes.agregar(_cliente);
						_macAddress = _datosObtenidos.get(1);
						_estado = _cliente.verificarBaneado(_lnegra, _datosObtenidos.get(0));
						writter(_estado,"verificar");
					}
					
				}
			}
		}catch(Exception e){
			System.out.println("errores");
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Este método se encarga de enviar mensajes a los clientes conectados al servidor.
	 * @param pDato Recibe los datos que serán enviados a los clientes.
	 */
	public void writter(String pDato,String pVerif){
		try{
			System.out.println("Mensaje enviado");
		_salidaCliente = new ObjectOutputStream(_socketcliente.getOutputStream());
		_obj = null;
		_array = new ArrayList<String>();
		_array.add(pDato);
		_array.add(pVerif);
		_obj = _parser.crearJson(_array);
		_salidaCliente.writeObject(_obj);
		_salidaCliente.flush();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void writter2(ArrayList<String> pDato,String pVerif){
		try{
			System.out.println("Mensaje enviado");
		_salidaCliente = new ObjectOutputStream(_socketcliente.getOutputStream());
		_obj = _parser.crearJson(pDato);
		System.out.println("8");
		_salidaCliente.writeObject(_obj);
		System.out.println("9");
		_salidaCliente.flush();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}

