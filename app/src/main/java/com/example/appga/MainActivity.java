package com.example.appga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText usuario = (EditText) findViewById(R.id.StringUser);
        final EditText senha = (EditText) findViewById(R.id.StringSenha);



         usuario.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 usuario.setText("");
             }
         });
        senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senha.setText("");
            }
        });



    }

    public void logar(View v){

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final EditText usuario = (EditText) findViewById(R.id.StringUser);
        final EditText senha = (EditText) findViewById(R.id.StringSenha);
        if((usuario.getText().toString().equals("Usuário"))&&(senha.getText().toString().equals("Senha"))) {
            Intent it = new Intent(MainActivity.this, Tela_principal.class);
            startActivity(it);
        }

    }



}
