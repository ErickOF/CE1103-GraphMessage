package com.fabiangonzalez.graphmessage;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Fabian Gonzalez on 11/17/2016.
 */

public class Bluetooth extends Activity implements OnClickListener{

    private Button btnBluetooth;
    private Button btnBuscarDispositivo;
    private ListView lvDispositivos;
    private TextView tvMensaje;

    private BluetoothAdapter bAdapter;					// Adapter para uso del Bluetooth
    private ArrayList<BluetoothDevice> arrayDevices;    // Listado de dispositivos
    private ArrayAdapter arrayAdapter;					// Adaptador para el listado de dispositivos

    private static final int    REQUEST_ENABLE_BT   = 1;


    // Instanciamos un BroadcastReceiver que se encargara de detectar si el estado
    // del Bluetooth del dispositivo ha cambiado mediante su handler onReceive
    private final BroadcastReceiver bReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            final String action = intent.getAction();

            // BluetoothAdapter.ACTION_STATE_CHANGED
            // Codigo que se ejecutara cuando el Bluetooth cambie su estado.
            // Manejaremos los siguientes estados:
            //		- STATE_OFF: El Bluetooth se desactiva
            //		- STATE ON: El Bluetooth se activa
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action))
            {
                final int estado = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                switch (estado)
                {
                    // Apagado
                    case BluetoothAdapter.STATE_OFF:
                    {
                        ((Button)findViewById(R.id.btnBluetooth)).setText(R.string.ActivarBluetooth);
                        ((Button)findViewById(R.id.btnBuscarDispositivo)).setEnabled(false);
                        break;
                    }

                    // Encendido
                    case BluetoothAdapter.STATE_ON:
                    {
                        ((Button)findViewById(R.id.btnBluetooth)).setText(R.string.DesactivarBluetooth);
                        ((Button)findViewById(R.id.btnBuscarDispositivo)).setEnabled(true);

                        // Lanzamos un Intent de solicitud de visibilidad Bluetooth, al que añadimos un par
                        // clave-valor que indicara la duracion de este estado, en este caso 120 segundos
                        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 120);
                        startActivity(discoverableIntent);

                        break;
                    }
                    default:
                        break;
                }
            }
            // BluetoothDevice.ACTION_FOUND
            // Cada vez que se descubra un nuevo dispositivo por Bluetooth, se ejecutar este fragmento de codigo
            else if (BluetoothDevice.ACTION_FOUND.equals(action))
            {
                // Si el array no ha sido aun inicializado, lo instanciamos
                if(arrayDevices == null)
                    arrayDevices = new ArrayList<BluetoothDevice>();

                // Extraemos el dispositivo del intent mediante la clave BluetoothDevice.EXTRA_DEVICE
                BluetoothDevice dispositivo = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                // Añadimos el dispositivo al array
                arrayDevices.add(dispositivo);

                // Le asignamos un nombre del estilo NombreDispositivo [00:11:22:33:44]
                String descripcionDispositivo = dispositivo.getName() + " [" + dispositivo.getAddress() + "]";

                // Mostramos que hemos encontrado el dispositivo por el Toast
                Toast.makeText(getBaseContext(), getString(R.string.DetectadoDispositivo) + ": " + descripcionDispositivo, Toast.LENGTH_SHORT).show();

            }

            // BluetoothAdapter.ACTION_DISCOVERY_FINISHED
            // Codigo que se ejecutara cuando el Bluetooth finalice la busqueda de dispositivos.
            if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
            {
                // Instanciamos un nuevo adapter para el ListView mediante la clase que acabamos de crear
                ArrayAdapter arrayAdapter = new BluetoothDeviceArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_2, arrayDevices);

                lvDispositivos.setAdapter(arrayAdapter);
                Toast.makeText(getBaseContext(), "Fin de la búsqueda", Toast.LENGTH_SHORT).show();
            }
        }
    };

    // Handler que obtendrá informacion de BluetoothService
    private final Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            byte[] buffer = null;
            String mensaje = null;

            // Atendemos al tipo de mensaje
            switch (msg.what) {
                // Mensaje de lectura: se mostrara en un TextView
                case BluetoothService.MSG_LEER: {
                    buffer = (byte[]) msg.obj;
                    mensaje = new String(buffer, 0, msg.arg1);
                    tvMensaje.setText(mensaje);
                    break;
                }

                // Mensaje de escritura: se mostrara en el Toast
                case BluetoothService.MSG_ESCRIBIR: {
                    buffer = (byte[]) msg.obj;
                    mensaje = new String(buffer);
                    mensaje = "Enviando mensaje: " + mensaje;
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                    break;
                }

                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        //Invocamos el metodos de configuracion de controles
        configurarControles();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Metodo de configuracion de la actividad
     */
    private void configurarControles()
    {
        // Instanciamos el array de dispositivos
        arrayDevices = new ArrayList<BluetoothDevice>();

        // Referenciamos los controles y registramos los listeners
        referenciarControles();
        registrarEventosControles();

        // Por defecto, desactivamos los botones que no puedan utilizarse
        //btnBuscarDispositivo.setEnabled(false);

        // Configuramos el adaptador bluetooth y nos suscribimos a sus eventos
        configurarAdaptadorBluetooth();
        registrarEventosBluetooth();
    }

    /**
     * Configura el BluetoothAdapter y los botones asociados
     */
    private void configurarAdaptadorBluetooth() {
        // Obtenemos el adaptador Bluetooth. Si es NULL, significara que el
        // dispositivo no posee Bluetooth, por lo que deshabilitamos el boton
        // encargado de activar/desactivar esta caracteristica.
        bAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bAdapter == null) {
            btnBluetooth.setEnabled(false);
            return;
        }

        // Comprobamos si el Bluetooth esta activo y cambiamos el texto del
        // boton dependiendo del estado.
        if(bAdapter.isEnabled()) {
            btnBluetooth.setText(R.string.DesactivarBluetooth);
        } else {
            btnBluetooth.setText(R.string.ActivarBluetooth);
        }
    }

    /**
     * Referencia los elementos de interfaz
     */
    private void referenciarControles()
    {
        btnBluetooth = (Button)findViewById(R.id.btnBluetooth);
        btnBuscarDispositivo = (Button)findViewById(R.id.btnBuscarDispositivo);
        lvDispositivos = (ListView)findViewById(R.id.lvDispositivos);
    }


    /**
     * Suscribe el BroadcastReceiver a los eventos relacionados con Bluetooth que queremos
     * controlar.
     */
    private void registrarEventosBluetooth()
    {
        // Registramos el BroadcastReceiver que instanciamos previamente para
        // detectar los distintos eventos que queremos recibir
        IntentFilter filtro = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        filtro.addAction(BluetoothDevice.ACTION_FOUND);
        filtro.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        this.registerReceiver(bReceiver, filtro);
    }

    /**
     * Registra los eventos de interfaz (eventos onClick, onItemClick, etc.)
     */
    private void registrarEventosControles()
    {
        btnBluetooth.setOnClickListener(this);
        btnBuscarDispositivo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            // Codigo ejecutado al pulsar el Button que se va a encargar de activar y
            // desactivar el Bluetooth.
            case R.id.btnBluetooth:
            {
                if(bAdapter.isEnabled())
                    bAdapter.disable();
                else
                {
                    // Lanzamos el Intent que mostrara la interfaz de activacion del
                    // Bluetooth. La respuesta de este Intent se manejara en el metodo
                    // onActivityResult
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                }
                break;
            }

            case R.id.btnBuscarDispositivo:
            {
                arrayDevices.clear();

                // Comprobamos si existe un descubrimiento en curso. En caso afirmativo, se
                // cancela.
                if(bAdapter.isDiscovering())
                    bAdapter.cancelDiscovery();

                // Iniciamos la busqueda de dispositivos
                if(bAdapter.startDiscovery())
                    // Mostramos el mensaje de que el proceso ha comenzado
                    Toast.makeText(this, R.string.IniciandoDescubrimiento, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, R.string.ErrorIniciandoDescubrimiento, Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    /**
     * Handler del evento desencadenado al retornar de una actividad. En este caso, se utiliza
     * para comprobar el valor de retorno al lanzar la actividad que activara el Bluetooth.
     * En caso de que el usuario acepte, resultCode sera RESULT_OK
     * En caso de que el usuario no acepte, resultCode valdra RESULT_CANCELED
     */
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        switch(requestCode)
        {
            case REQUEST_ENABLE_BT:
            {
                if(resultCode == RESULT_OK)
                {
                    // Acciones adicionales a realizar si el usuario activa el Bluetooth
                }
                else
                {
                    // Acciones adicionales a realizar si el usuario no activa el Bluetooth
                }
                break;
            }

            default:
                break;
        }
    }

    // Ademas de realizar la destruccion de la actividad, eliminamos el registro del
    // BroadcastReceiver.
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(bReceiver);
    }
}

