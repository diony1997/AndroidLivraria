package com.example.appga.Cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appga.Banco.LivrariaBD;
import com.example.appga.Model.Leitor;
import com.example.appga.R;

public class Formulario_Leitores extends AppCompatActivity {

    Leitor editarLeitor, leitor;
    LivrariaBD banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario__leitores);

        leitor = new Leitor();
        banco = new LivrariaBD(Formulario_Leitores.this);

        Intent intent = getIntent();
        editarLeitor = (Leitor) intent.getSerializableExtra("Leitor");

        final EditText editText_Nome = (EditText) findViewById(R.id.editText_NomeLeitor);
        final EditText editText_Endereco = (EditText) findViewById(R.id.editText_EnderecoLeitor);
        final EditText editText_celular = (EditText) findViewById(R.id.editText_CelularLeitor);
        final EditText editText_Email = (EditText) findViewById(R.id.editText_EmailLeitor);
        final EditText editText_Cpf = (EditText) findViewById(R.id.editText_CpfLeitor);
        final EditText editText_Dt = (EditText) findViewById(R.id.editText_DtLeitor);
        final EditText editText_CatLeitor = (EditText) findViewById(R.id.editText_CatLeitor);

        final Button btn_Cadastro = (Button) findViewById(R.id.btn_CadastroLeitor);

        if(editarLeitor != null){
            btn_Cadastro.setText("Modificar");

            editText_Nome.setText(editarLeitor.getNome());
            editText_Endereco.setText(editarLeitor.getEndereco());
            editText_celular.setText(editarLeitor.getCelular());
            editText_Email.setText(editarLeitor.getEmail());
            editText_Cpf.setText(editarLeitor.getCpf());
            editText_Dt.setText(editarLeitor.getDt_Nascimento());
            editText_CatLeitor.setText(editarLeitor.getCategoria()+"");

            leitor.setCpf(editarLeitor.getCpf());

        } else {
            btn_Cadastro.setText("Cadastrar");
        }

        btn_Cadastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                leitor.setNome(editText_Nome.getText().toString());
                leitor.setEndereco(editText_Endereco.getText().toString());
                leitor.setCelular(editText_celular.getText().toString());
                leitor.setEmail(editText_Email.getText().toString());
                leitor.setCpf(editText_Cpf.getText().toString());
                leitor.setDt_Nascimento(editText_Dt.getText().toString());
                leitor.setCategoria(Integer.parseInt(editText_CatLeitor.getText().toString()));

                if(btn_Cadastro.getText().toString().equals("Cadastrar")){
                    banco.salvarLeitor(leitor);
                    banco.close();
                } else {
                    banco.alterarLeitor(leitor);
                    banco.close();
                }
                sair();
            }

        });
    }

    public void sair(){
        Intent it = new Intent(Formulario_Leitores.this, Cad_Leitor.class);
        startActivity(it);
    }
}
