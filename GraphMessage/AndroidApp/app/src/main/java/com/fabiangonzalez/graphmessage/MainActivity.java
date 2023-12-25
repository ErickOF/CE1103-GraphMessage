package com.fabiangonzalez.graphmessage;

import android.app.*;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity {
    private TextView nombre;
    private TextView email;
    private String _nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(AccessToken.getCurrentAccessToken() == null){
            goLoginScreen();
        }

        nombre = (TextView) findViewById(R.id.textoNombre);
        email = (TextView) findViewById(R.id.textoEmail);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            String cadena = (String) bundle.get("nombres");
            _nombre = cadena;
            nombre.setText(cadena);
            String cadena2 = (String) bundle.get("correos");
            email.setText(cadena2);
        }
    }

    //Metodo que dirige a la pantalla de bluetooth
    public void goBluetooth(View view){
        Intent bluetooth = new Intent(this, Bluetooth.class);
        bluetooth.putExtra("nombre",_nombre);
        startActivity(bluetooth);

    }

    //Metodo que devuelve a la pantalla de login con Facebook
    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //Metodo que hace logout de la cuenta de Facebook
    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

}
