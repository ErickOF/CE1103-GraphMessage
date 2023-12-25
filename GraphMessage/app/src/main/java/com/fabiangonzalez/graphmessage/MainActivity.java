package com.fabiangonzalez.graphmessage;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(AccessToken.getCurrentAccessToken() == null){
            goLoginScreen();
        }
    }

    //Metodo que dirige a la pantalla de bluetooth
    public void goBluetooth(View view){
        Intent bluetooth = new Intent(this, Bluetooth.class);
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