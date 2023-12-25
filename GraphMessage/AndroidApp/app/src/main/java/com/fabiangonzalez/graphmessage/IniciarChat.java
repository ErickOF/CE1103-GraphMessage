package com.fabiangonzalez.graphmessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class IniciarChat extends AppCompatActivity implements Runnable {
    private Button _enviar;
    private EditText _texto;
    private ListView _listaMensajes;
    private ArrayList<CargarEstiloChat>  _mensajes = new ArrayList<>();
    private InstanciaMensajes _instMensajes = InstanciaMensajes.getSingletonInstance(_mensajes);
    private CargarEstiloChat _row;
    private ArrayAdapterPersonalChat _a_aPersonal;
    private Thread _tChat;
    private ClienteChat _clientChat;
    private InstanciaClienteChat _instClientChat = InstanciaClienteChat.getSingletonInstance(_clientChat);

    private Cliente _clienteServer;
    private InstanciaCliente _instClientServer = InstanciaCliente.getSingletonInstance(_clienteServer);
    private ArrayList<String> _mensaje = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_chat);
        System.out.println("Manda huevo");
        _enviar = (Button)findViewById(R.id.btnEnviar);
        _texto = (EditText)findViewById(R.id.txtMensaje);
        _listaMensajes = (ListView)findViewById(R.id.listaMensajes);
        _a_aPersonal = new ArrayAdapterPersonalChat(this, _instMensajes.get_mensajes());
        _listaMensajes.setAdapter(_a_aPersonal);
        Thread Thread = new Thread(this);
        Thread.start();

        _enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _instClientChat.get_clienterecibido().enviarDatos( _texto.getText().toString());
                _mensaje.add(_texto.getText().toString());
                //_instClientServer.get_clienterecibido().enviarDatos(_mensaje);
                _row = new CargarEstiloChat();
                _row.setTitle(_texto.getText().toString());
                _instMensajes.set_mensajes(_row);
                _texto.setText("");
            }
        });
    }

    public void llenarChat(String pMensaje){
        _row = new CargarEstiloChat();
        _row.setTitle(pMensaje);
        _instMensajes.set_mensajes(_row);
    }

    @Override
    public void run() {
        try {
            while(true){
                runOnUiThread (new Thread(new Runnable() {
                    public void run() {
                        _a_aPersonal.notifyDataSetChanged();
                    }
                }));
                _tChat.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void confirmado(){
        System.out.println("++++++++++++++++++++++Esta es la entrega+++++++++*******************");
        _instMensajes.get_mensajes().get(_instMensajes.get_mensajes().size()-1).setSubtitle("Entregado");
    }
}