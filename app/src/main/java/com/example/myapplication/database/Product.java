package com.example.myapplication.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nome;
    public String codigo;
    public double preco;
    public int quantidade;
}