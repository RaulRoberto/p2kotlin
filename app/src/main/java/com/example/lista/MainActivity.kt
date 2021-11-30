package com.example.lista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.lista.adapter.ItemAdapter
import com.example.lista.data.Dao
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.lista.data.AppDatabase
import com.example.lista.model.Item


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.buttonentrar)
        button.setOnClickListener { abrirTela() }



    }
    fun abrirTela(){
        val intent = android.content.Intent(this, com.example.lista.CadastroActivity::class.java)
        startActivity(intent)
    }
}