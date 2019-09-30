package com.example.appga.Cadastro;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appga.Banco.LivrariaBD;
import com.example.appga.Model.Categoria_Leitor;
import com.example.appga.R;

public class Formulario_CatLeitores extends AppCompatActivity {

    Categoria_Leitor editarCategoria, categoria;
    LivrariaBD banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario__cat_leitores);

        categoria = new Categoria_Leitor();
        banco = new LivrariaBD(Formulario_CatLeitores.this);

        Intent intent = getIntent();
        editarCategoria = (Categoria_Leitor) intent.getSerializableExtra("categoriaLeitor");

        final EditText editText_CodCat = (EditText) findViewById(R.id.editText_CodCatLeitor);
        final EditText editText_Descricao = (EditText) findViewById(R.id.editText_DescricaoCatLeitor);
        final EditText editText_Aluguel = (EditText) findViewById(R.id.editText_AluguelCatLeitor);


        final Button btn_Cadastro = (Button) findViewById(R.id.btn_CadastroCatLeitor);

        if(editarCategoria != null){
            btn_Cadastro.setText("Modificar");

            editText_Descricao.setText(editarCategoria.getDescricao());
            editText_Aluguel.setText(editarCategoria.getAluguel_Maximo()+"");
            editText_CodCat.setText(editarCategoria.getCodigo()+"");

            categoria.setCodigo(editarCategoria.getCodigo());

        } else {
            btn_Cadastro.setText("Cadastrar");
        }

        btn_Cadastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                categoria.setCodigo(Integer.parseInt(editText_CodCat.getText().toString()));
                categoria.setDescricao(editText_Descricao.getText().toString());
                categoria.setAluguel_Maximo(Integer.parseInt(editText_Aluguel.getText().toString()));

                if(btn_Cadastro.getText().toString().equals("Cadastrar")){
                    banco.salvarCategoriaLeitor(categoria);
                    banco.close();
                } else {
                    banco.alterarCategoriaLeitor(categoria);
                    banco.close();
                }
                sair();

            }

        });

    }


    public void sair(){
        Intent it = new Intent(Formulario_CatLeitores.this, Cad_CategoriaLeitor.class);
        startActivity(it);
    }
}
