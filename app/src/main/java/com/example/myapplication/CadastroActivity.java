package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.database.Product;
import com.example.myapplication.database.ProductDatabase;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtNome, edtCodigo, edtPreco, edtQuantidade;
    private Button btnSalvar, btnLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome = findViewById(R.id.edtNome);
        edtCodigo = findViewById(R.id.edtCodigo);
        edtPreco = findViewById(R.id.edtPreco);
        edtQuantidade = findViewById(R.id.edtQuantidade);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnLista = findViewById(R.id.btnLista);

        btnSalvar.setOnClickListener(v -> salvarProduto());

        btnLista.setOnClickListener(v -> {
            Intent intent = new Intent(CadastroActivity.this, ListaActivity.class);
            startActivity(intent);
        });
    }

    private void salvarProduto() {
        String nome = edtNome.getText().toString().trim();
        String codigo = edtCodigo.getText().toString().trim();
        String precoTexto = edtPreco.getText().toString().trim();
        String quantidadeTexto = edtQuantidade.getText().toString().trim();

        if (nome.isEmpty() || codigo.isEmpty() || precoTexto.isEmpty() || quantidadeTexto.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double preco;
        int quantidade;

        try {
            preco = Double.parseDouble(precoTexto);
        } catch (Exception e) {
            Toast.makeText(this, "Preço inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            quantidade = Integer.parseInt(quantidadeTexto);
        } catch (Exception e) {
            Toast.makeText(this, "Quantidade inválida", Toast.LENGTH_SHORT).show();
            return;
        }

        if (preco <= 0) {
            Toast.makeText(this, "O preço deve ser positivo", Toast.LENGTH_SHORT).show();
            return;
        }

        if (quantidade <= 0) {
            Toast.makeText(this, "A quantidade deve ser positiva", Toast.LENGTH_SHORT).show();
            return;
        }

        Product product = new Product();
        product.nome = nome;
        product.codigo = codigo;
        product.preco = preco;
        product.quantidade = quantidade;

        ProductDatabase.getDatabase(this).productDao().inserir(product);

        Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

        edtNome.setText("");
        edtCodigo.setText("");
        edtPreco.setText("");
        edtQuantidade.setText("");
    }
}