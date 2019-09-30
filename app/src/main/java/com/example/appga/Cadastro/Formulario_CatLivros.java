package com.example.appga.Cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appga.Banco.LivrariaBD;
import com.example.appga.Model.Categoria_Livro;
import com.example.appga.R;

public class Formulario_CatLivros extends AppCompatActivity {

    Categoria_Livro editarCategoria, categoria;
    LivrariaBD banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario__cat_livros);

        categoria = new Categoria_Livro();
        banco = new LivrariaBD(Formulario_CatLivros.this);

        Intent intent = getIntent();
        editarCategoria = (Categoria_Livro) intent.getSerializableExtra("categoriaLivro");

         final EditText editText_CodCat = (EditText) findViewById(R.id.editText_CodCatLivro);
         final EditText editText_Descricao = (EditText) findViewById(R.id.editText_DescricaoCatLivro);
         final EditText editText_Aluguel = (EditText) findViewById(R.id.editText_AluguelCatLivro);
         final EditText editText_Multa = (EditText) findViewById(R.id.editText_MultaCatLivro);

         final Button btn_Cadastro = (Button) findViewById(R.id.btn_CadastroFormLivro);

    if(editarCategoria != null){
        btn_Cadastro.setText("Modificar");

        editText_Descricao.setText(editarCategoria.getDescricao());
        editText_Aluguel.setText(editarCategoria.getAluguel_Maximo()+"");
        editText_Multa.setText(editarCategoria.getMulta()+"");
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
            categoria.setMulta(Float.parseFloat(editText_Multa.getText().toString()));

            if(btn_Cadastro.getText().toString().equals("Cadastrar")){
                banco.salvarCategoriaLivro(categoria);
                banco.close();
            } else {
                banco.alterarCategoriaLivro(categoria);
                banco.close();
            }
            sair();

        }

    });

    }
    public void sair(){
        Intent it = new Intent(Formulario_CatLivros.this, Cad_CategoriaLivro.class);
        startActivity(it);
    }
}
