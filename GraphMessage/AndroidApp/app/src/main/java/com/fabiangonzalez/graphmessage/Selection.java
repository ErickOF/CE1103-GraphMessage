package com.fabiangonzalez.graphmessage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Selection extends AppCompatActivity implements Runnable {
    private ListView _listaPosibles;
    private ArrayList _nombres = new ArrayList();
    private Cliente _cliente;
    private InstanciaCliente _instCliente = InstanciaCliente.getSingletonInstance(_cliente);
    private Boolean _bandera = true;
    private ArrayList _lineas;
    private ArrayList<String> _listaConectados;
    private CargarEstilo _row;
    private Boolean _banderaBoton = false;

    //Nuevo Cliente
    private ClienteChat _clienteChat;
    private InstanciaClienteChat _instClientChat;
    private IniciarChat _init = new IniciarChat();
    private InstanciaChat _instChat;
    private ArrayList _palabraClave = new ArrayList();
    private Selection _sel;
    private InstanciaSelection _select = InstanciaSelection.getSingletonInstance(this);
    private Thread _thread;
    private Button _btnsolicitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        _listaPosibles = (ListView)findViewById(R.id.lvReceptores);
        _cliente = _instCliente.get_clienterecibido();
        _btnsolicitud = (Button)findViewById(R.id.btnSolicitado);
        _palabraClave.add("Lista");
        _cliente.enviarDatosSelect(_palabraClave,Selection.this);
        _instClientChat = InstanciaClienteChat.getSingletonInstance(_clienteChat);
        _instChat = InstanciaChat.getSingletonInstance(_init);
        _thread = new Thread(this);
        _thread.start();
        while(_bandera){        }
        _lineas = new ArrayList();
        System.out.println(_listaConectados.size() + "size");
        if(_listaConectados.size() == 0){
            _row = new CargarEstilo();
            _row.setNombre("No hay más usuarios conectados");
            _row.setIP("");
            _lineas.add(_row);
        }else {
            for (int _cont = 0; _cont < _listaConectados.size()-1; _cont += 2) {
                _row = new CargarEstilo();
                _row.setNombre(_listaConectados.get(_cont));
                System.out.println(_listaConectados.get(_cont));
                _row.setIP(_listaConectados.get(_cont + 1));
                _lineas.add(_row);
            }
        }

        _listaPosibles.setAdapter(new ArrayAdapterPersonal(this, _lineas));
        _listaPosibles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CargarEstilo est = (CargarEstilo) _lineas.get(position);
                System.out.println(est.getIP()+ "  Esta es la IP");
                _clienteChat = new ClienteChat(est.getIP());
                _instClientChat = InstanciaClienteChat.getSingletonInstance(_clienteChat);
                Intent _chat = new Intent(Selection.this,_instChat.get_chatCreado().getClass());
                _chat.putExtra("IP",est.getIP());
                startActivity(_chat);
            }
        });
        _btnsolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatServer = new Intent(Selection.this,IniciarChatServer.class);
                startActivity(chatServer);
            }
        });
    }

    public void cargarLista(ArrayList pLista){
        System.out.println("Támbien llegó!!!!!!!!!!!!!");
        _listaConectados = pLista;
        /*_listaConectados.add("Victor");
        _listaConectados.add("172.26.100.199");
        _listaConectados.add("Manuel");
        _listaConectados.add("172.26.100.199");
        _listaConectados.add("Erick");
        _listaConectados.add("172.26.100.199");
        _listaConectados.add("no");
        _listaConectados.add("172.26.100.199");*/
        _bandera = false;
    }

    public void cambiarBanderaBoton(){
        System.out.println("Ohhh Ohh########################################");
        _banderaBoton = true;
    }

    @Override
    public void run() {
        try {
            while(true){
                runOnUiThread (new Thread(new Runnable() {
                    public void run() {
                        if(_banderaBoton){
                            _btnsolicitud.setEnabled(true);
                        }
                    }
                }));
                _thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
