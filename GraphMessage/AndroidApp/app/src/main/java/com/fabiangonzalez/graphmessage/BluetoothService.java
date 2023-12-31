package com.fabiangonzalez.graphmessage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class BluetoothService {

    private static final String TAG = "org.danigarcia.examples.bluetooth.BluetoothService";
    private static final boolean DEBUG_MODE = true;

    private final Handler handler;
    private final Context context;
    private final BluetoothAdapter bAdapter;

    public static final String NOMBRE_SEGURO = "BluetoothServiceSecure";
    public static final String NOMBRE_INSEGURO = "BluetoothServiceInsecure";
    public static UUID UUID_SEGURO; //= UUID.fromString("org.danigarcia.examples.bluetooth.BluetoothService.Secure");
    public static UUID UUID_INSEGURO; //= UUID.fromString("org.danigarcia.examples.bluetooth.BluetoothService.Insecure");

    public static final int ESTADO_NINGUNO = 0;
    public static final int ESTADO_CONECTADO = 1;
    public static final int ESTADO_REALIZANDO_CONEXION = 2;
    public static final int ESTADO_ATENDIENDO_PETICIONES = 3;

    public static final int MSG_CAMBIO_ESTADO = 10;
    public static final int MSG_LEER = 11;
    public static final int MSG_ESCRIBIR = 12;

    private int estado;
    private HiloServidor hiloServidor = null;
    private HiloCliente hiloCliente = null;
    private HiloConexion hiloConexion = null;


    public BluetoothService(Context context, Handler handler, BluetoothAdapter adapter) {
        debug("BluetoothService()", "Iniciando metodo");

        this.context = context;
        this.handler = handler;
        this.bAdapter = adapter;
        this.estado = ESTADO_NINGUNO;

        UUID_SEGURO = generarUUID();
        UUID_INSEGURO = generarUUID();
    }

    private synchronized void setEstado(int estado) {
        this.estado = estado;
        handler.obtainMessage(MSG_CAMBIO_ESTADO, estado, -1).sendToTarget();
    }


    public synchronized void realizarConexion(BluetoothSocket socket, BluetoothDevice dispositivo) {
        debug("realizarConexion()", "Iniciando metodo");
        hiloConexion = new HiloConexion(socket);
        hiloConexion.start();
    }


    // Hilo que hace las veces de servidor, encargado de escuchar conexiones entrantes y
    // crear un hilo que maneje la conexion cuando ello ocurra.
    // La otra parte debera solicitar la conexion mediante un HiloCliente.
    private class HiloServidor extends Thread {
        private final BluetoothServerSocket serverSocket;

        public HiloServidor() {
            debug("HiloServidor.new()", "Iniciando metodo");
            BluetoothServerSocket tmpServerSocket = null;

            // Creamos un socket para escuchar las peticiones de conexion
            try {
                tmpServerSocket = bAdapter.listenUsingRfcommWithServiceRecord(NOMBRE_SEGURO, UUID_SEGURO);
            } catch (IOException e) {
                Log.e(TAG, "HiloServidor(): Error al abrir el socket servidor", e);
            }

            serverSocket = tmpServerSocket;
        }

        public void run() {
            debug("HiloServidor.run()", "Iniciando metodo");
            BluetoothSocket socket = null;

            setName("HiloServidor");
            setEstado(ESTADO_ATENDIENDO_PETICIONES);
            // El hilo se mantendra en estado de espera ocupada aceptando conexiones
            // entrantes siempre y cuando no exista una conexion activa.
            // En el momento en el que entre una nueva conexion,
            while (estado != ESTADO_CONECTADO) {
                try {
                    // Cuando un cliente solicite la conexion se asignara valor al socket..
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    Log.e(TAG, "HiloServidor.run(): Error al aceptar conexiones entrantes", e);
                    break;
                }

                // Si el socket tiene valor sera porque un cliente ha solicitado la conexion
                if (socket != null) {
                    // Realizamos un lock del objeto
                    synchronized (BluetoothService.this) {
                        switch (estado) {
                            case ESTADO_ATENDIENDO_PETICIONES:
                            case ESTADO_REALIZANDO_CONEXION: {
                                debug("HiloServidor.run()", estado == ESTADO_ATENDIENDO_PETICIONES ? "Atendiendo peticiones" : "Realizando conexion");
                                // Estado esperado, se crea el hilo de conexion que recibira
                                // y enviara los mensajes
                                realizarConexion(socket, socket.getRemoteDevice());
                                break;
                            }
                            case ESTADO_NINGUNO:
                            case ESTADO_CONECTADO: {
                                // No preparado o conexion ya realizada.
                                // Se cierra el nuevo socket.
                                try {
                                    debug("HiloServidor.run()", estado == ESTADO_NINGUNO ? "Ninguno" : "Conectado");
                                    socket.close();
                                } catch (IOException e) {
                                    Log.e(TAG, "HiloServidor.run(): socket.close(). Error al cerrar el socket.", e);
                                }
                                break;
                            }
                            default:
                                break;
                        }
                    }
                }

            } // End while
        }

        public void cancelarConexion() {
            debug("HiloServidor.cancelarConexion()", "Iniciando metodo");
            try {
                serverSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "HiloServidor.cancelarConexion(): Error al cerrar el socket", e);
            }
        }
    }

    // Hilo encargado de solicitar una conexion a un dispositivo que este corriendo un
    // HiloServidor.
    private class HiloCliente extends Thread {
        private final BluetoothDevice dispositivo;
        private final BluetoothSocket socket;

        public HiloCliente(BluetoothDevice dispositivo) {
            debug("HiloCliente.new()", "Iniciando metodo");
            BluetoothSocket tmpSocket = null;
            this.dispositivo = dispositivo;

            // Obtenemos un socket para el dispositivo con el que se quiere conectar
            try {
                tmpSocket = dispositivo.createRfcommSocketToServiceRecord(UUID_SEGURO);
            } catch (IOException e) {
                Log.e(TAG, "HiloCliente.HiloCliente(): Error al abrir el socket", e);
            }

            socket = tmpSocket;
        }

        public void run() {
            debug("HiloCliente.run()", "Iniciando metodo");
            setName("HiloCliente");
            if (bAdapter.isDiscovering())
                bAdapter.cancelDiscovery();

            try {
                socket.connect();
                setEstado(ESTADO_REALIZANDO_CONEXION);
            } catch (IOException e) {
                Log.e(TAG, "HiloCliente.run(): socket.connect(): Error realizando la conexion", e);
                try {
                    socket.close();
                } catch (IOException inner) {
                    Log.e(TAG, "HiloCliente.run(): Error cerrando el socket", inner);
                }
                setEstado(ESTADO_NINGUNO);
            }

            // Reiniciamos el hilo cliente, ya que no lo necesitaremos mas
            synchronized (BluetoothService.this) {
                hiloCliente = null;
            }

            // Realizamos la conexion
            realizarConexion(socket, dispositivo);
        }

        public void cancelarConexion() {
            debug("cancelarConexion()", "Iniciando metodo");
            try {
                socket.close();
            } catch (IOException e) {
                Log.e(TAG, "HiloCliente.cancelarConexion(): Error al cerrar el socket", e);
            }
            setEstado(ESTADO_NINGUNO);
        }
    }

    // Hilo encargado de mantener la conexion y realizar las lecturas y escrituras
    // de los mensajes intercambiados entre dispositivos.
    private class HiloConexion extends Thread {
        private final BluetoothSocket socket;            // Socket
        private final InputStream inputStream;    // Flujo de entrada (lecturas)
        private final OutputStream outputStream;    // Flujo de salida (escrituras)

        public HiloConexion(BluetoothSocket socket) {
            debug("HiloConexion.new()", "Iniciando metodo");
            this.socket = socket;

            setName(socket.getRemoteDevice().getName() + " [" + socket.getRemoteDevice().getAddress() + "]");

            // Se usan variables temporales debido a que los atributos se declaran como final
            // no seria posible asignarles valor posteriormente si fallara esta llamada
            InputStream tmpInputStream = null;
            OutputStream tmpOutputStream = null;

            // Obtenemos los flujos de entrada y salida del socket.
            try {
                tmpInputStream = socket.getInputStream();
                tmpOutputStream = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "HiloConexion(): Error al obtener flujos de E/S", e);
            }

            inputStream = tmpInputStream;
            outputStream = tmpOutputStream;
        }

        // Metodo principal del hilo, encargado de realizar las lecturas
        public void run() {
            debug("HiloConexion.run()", "Iniciando metodo");
            byte[] buffer = new byte[1024];
            int bytes;
            setEstado(ESTADO_CONECTADO);
            // Mientras se mantenga la conexion el hilo se mantiene en espera ocupada
            // leyendo del flujo de entrada
            while (true) {
                try {
                    // Leemos del flujo de entrada del socket
                    bytes = inputStream.read(buffer);

                    // Enviamos la informacion a la actividad a traves del handler.
                    // El metodo handleMessage sera el encargado de recibir el mensaje
                    // y mostrar los datos recibidos en el TextView
                    handler.obtainMessage(MSG_LEER, bytes, -1, buffer).sendToTarget();
                    sleep(500);
                } catch (IOException e) {
                    Log.e(TAG, "HiloConexion.run(): Error al realizar la lectura", e);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void escribir(byte[] buffer) {
            debug("HiloConexion.escribir()", "Iniciando metodo");
            try {
                // Escribimos en el flujo de salida del socket
                outputStream.write(buffer);

                // Enviamos la informacion a la actividad a traves del handler.
                // El metodo handleMessage sera el encargado de recibir el mensaje
                // y mostrar los datos enviados en el Toast
                handler.obtainMessage(MSG_ESCRIBIR, -1, -1, buffer).sendToTarget();
            } catch (IOException e) {
                Log.e(TAG, "HiloConexion.escribir(): Error al realizar la escritura", e);
            }
        }

        public void cancelarConexion() {
            debug("HiloConexion.cancelarConexion()", "Iniciando metodo");
            try {
                // Forzamos el cierre del socket
                socket.close();

                // Cambiamos el estado del servicio
                setEstado(ESTADO_NINGUNO);
            } catch (IOException e) {
                Log.e(TAG, "HiloConexion.cerrarConexion(): Error al cerrar la conexion", e);
            }
        }

    }

    public void debug(String metodo, String msg) {
        if (DEBUG_MODE)
            Log.d(TAG, metodo + ": " + msg);
    }

    private UUID generarUUID() {
        ContentResolver appResolver = context.getApplicationContext().getContentResolver();
        String id = Settings.Secure.getString(appResolver, Settings.Secure.ANDROID_ID);
        final TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        final String deviceId = String.valueOf(tManager.getDeviceId());
        final String simSerialNumber = String.valueOf(tManager.getSimSerialNumber());
        final String androidId = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID uuid = new UUID(androidId.hashCode(), ((long) deviceId.hashCode() << 32) | simSerialNumber.hashCode());
        uuid = new UUID((long) 1000, (long) 23);
        return uuid;
    }

    public synchronized void solicitarConexion(BluetoothDevice dispositivo) {
        debug("solicitarConexion()", "Iniciando metodo");
        // Comprobamos si existia un intento de conexion en curso.
        // Si es el caso, se cancela y se vuelve a iniciar el proceso
        if (estado == ESTADO_REALIZANDO_CONEXION) {
            if (hiloCliente != null) {
                hiloCliente.cancelarConexion();
                hiloCliente = null;
            }
        }
    }
}


