package com.example.bowlpromobile;




import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TelaLoginCliente extends AppCompatActivity {
    Button tt;
    View conteiner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_cliente);


    }

    private void allComp(){
        tt = findViewById(R.id.TT);
        conteiner1 = findViewById(R.id.conteiner1);
    }
}
