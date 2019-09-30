package com.example.appga.Cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appga.Banco.LivrariaBD;
import com.example.appga.Model.Livro;
import com.example.appga.R;

public class Formulario_Livros extends AppCompatActivity {

    Livro editarLivro, livro;
    LivrariaBD banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario__livros);

        livro = new Livro();
        banco = new LivrariaBD(Formulario_Livros.this);

        Intent intent = getIntent();
        editarLivro = (Livro) intent.getSerializableExtra("Livro");

        final EditText editText_Isbn = (EditText) findViewById(R.id.editText_IsbnLivro);
        final EditText editText_Titulo = (EditText) findViewById(R.id.editText_TituloLivro);
        final EditText editText_Categoria = (EditText) findViewById(R.id.editText_CatLivro);
        final EditText editText_Autor = (EditText) findViewById(R.id.editText_AutorLivro);
        final EditText editText_KeyWords = (EditText) findViewById(R.id.editText_KeyWordsLivro);
        final EditText editText_Data = (EditText) findViewById(R.id.editText_DTLivro);
        final EditText editText_Edicao = (EditText) findViewById(R.id.editText_EdicaoLivro);
        final EditText editText_Editora = (EditText) findViewById(R.id.editText_EditoraLivro);
        final EditText editText_Paginas = (EditText) findViewById(R.id.editText_PaginasLivro);

        final Button btn_Cadastro = (Button) findViewById(R.id.btn_CadastroLivro);

        if(editarLivro != null){
            btn_Cadastro.setText("Modificar");

            editText_Isbn.setText(editarLivro.getIsbn()+"");
            editText_Titulo.setText(editarLivro.getTitulo());
            editText_Categoria.setText(editarLivro.getCategoria()+"");
            editText_Autor.setText(editarLivro.getAutor());
            editText_KeyWords.setText(editarLivro.getKeyWords());
            editText_Data.setText(editarLivro.getDtPub());
            editText_Edicao.setText(editarLivro.getEdicao()+"");
            editText_Editora.setText(editarLivro.getEditora());
            editText_Paginas.setText(editarLivro.getPaginas()+"");

            livro.setCodigo(editarLivro.getCodigo());

        } else {
            btn_Cadastro.setText("Cadastrar");
        }

        btn_Cadastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                livro.setIsbn(Integer.parseInt(editText_Isbn.getText().toString()));
                livro.setTitulo(editText_Titulo.getText().toString());
                livro.setCategoria(Integer.parseInt(editText_Categoria.getText().toString()));
                livro.setAutor(editText_Autor.getText().toString());
                livro.setKeyWords(editText_KeyWords.getText().toString());
                livro.setDtPub(editText_Data.getText().toString());
                livro.setEdicao(Integer.parseInt(editText_Edicao.getText().toString()));
                livro.setEditora(editText_Editora.getText().toString());
                livro.setPaginas(Integer.parseInt(editText_Paginas.getText().toString()));

                if(btn_Cadastro.getText().toString().equals("Cadastrar")){
                    banco.salvarLivro(livro);
                    banco.close();
                } else {
                    banco.alterarLivro(livro);
                    banco.close();
                }
                sair();
            }

        });


    }

    public void sair(){
        Intent it = new Intent(Formulario_Livros.this, Cad_Livros.class);
        startActivity(it);
    }
}
