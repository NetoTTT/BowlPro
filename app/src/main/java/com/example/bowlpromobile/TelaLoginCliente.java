package com.example.bowlpromobile;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TelaLoginCliente extends AppCompatActivity {
    private EditText nome,email;
    private Button bl;
    private View conteiner1;
    private TextView cadastro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_cliente);
        allComp();
        bl.setVisibility(View.INVISIBLE);

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TelaCadastroCliente.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private void allComp(){
        conteiner1 = findViewById(R.id.conteiner1);
        bl = findViewById(R.id.bl);
        nome = findViewById(R.id.nome_edit);
        email = findViewById(R.id.email_edit);
        cadastro = findViewById(R.id.CadastrarCliente);
    }
}
