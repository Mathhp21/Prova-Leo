package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.database.Product;
import com.example.myapplication.database.ProductDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listView = findViewById(R.id.listView);

        List<Product> listaProdutos = ProductDatabase.getDatabase(this).productDao().listarTodos();
        List<String> dados = new ArrayList<>();

        for (Product p : listaProdutos) {
            dados.add("Nome: " + p.nome +
                    "\nCódigo: " + p.codigo +
                    "\nPreço: R$ " + String.format("%.2f", p.preco));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dados
        );

        listView.setAdapter(adapter);
    }
}