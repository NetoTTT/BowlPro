package com.example.bowlpromobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Menu_Cliente extends AppCompatActivity {

    private Button bl2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_cliente);
        bl2.setVisibility(View.INVISIBLE);
    }

    private void allCompMenuCliente(){
        bl2 = findViewById(R.id.bl2);
    }
}
