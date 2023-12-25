package Server;


import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * En esta clase se codifican y descodifican los datos a formato Json.
 * @author Víctor Fuentes Vargas
 *
 */
public class JsonParser{
	private JSONObject _obj;
	private JSONArray _lista,_listaPos;
	private JSONObject _jsonObject;
	private String _name,_IP;
	private ArrayList<Object> _array,_datos;
	private int _tipoMensaje;
	public JsonParser(){
		_obj = new JSONObject();
		 _lista = new JSONArray();
		 _listaPos = new JSONArray();
	}
	
	/**
	 * Este método se encarga de crear el objeto Json que sera enviado a los clientes
	 * @return Retorna el objeto Json.
	 */
	@SuppressWarnings("unchecked")
	public JSONObject crearJson(ArrayList<String> pMensaje){
	    _obj.put("Mensaje", pMensaje);
	    return _obj;
	}
	
	/**
	 * Este método se encarga de leer y decodificar los datos recibidos.
	 * @param pDatos Recibe los datos del cliente conectado.
	 */
	public ArrayList leer_Json(Object pDatos){
		System.out.println("Ahora se mostraran los elemntos del Json");
		_jsonObject = (JSONObject) pDatos;
		System.out.println(_jsonObject.get("Lista") + "  Este es lista");
		System.out.println(_jsonObject.get("Mensaje") + "  Este es mensaje");
		System.out.println(_jsonObject.get("Copia") + "  Este es mensaje");
        
        if(_jsonObject.get("Lista")!= null){
        	_datos = (ArrayList) _jsonObject.get("Lista");
        	_datos.add("Lista");
        }else if(_jsonObject.get("Mensaje")!= null){
        	_datos = (ArrayList) _jsonObject.get("Mensaje");
        	_datos.add("Mensaje");
        	System.out.println("Este es mensaje procesando");
        }else{
        	_datos = (ArrayList) _jsonObject.get("Copia");
        	_datos.add("Copia");
        }
        return _datos;
	}
}

