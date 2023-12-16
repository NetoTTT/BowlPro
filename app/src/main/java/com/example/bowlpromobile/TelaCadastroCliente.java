package com.example.bowlpromobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TelaCadastroCliente extends AppCompatActivity {

    private EditText nome,email,senha,idade;
    private Button bl;
    View conteiner1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_cliente);
        allCompCadastro();
        bl.setVisibility(View.INVISIBLE);


    }

    private void allCompCadastro(){
        conteiner1 = findViewById(R.id.conteiner1);
        bl = findViewById(R.id.bl1);
        nome = findViewById(R.id.nome_edit_cadastro);
        email = findViewById(R.id.email_edit_cadastro);
        senha = findViewById(R.id.senha_edit_cadastro);
        idade = findViewById(R.id.idade_edit_cadastro);
    }


}
