package com.example.lista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.lista.adapter.ItemAdapter
import com.example.lista.data.Dao
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.lista.data.AppDatabase
import com.example.lista.model.Item
import android.text.Editable

import android.text.TextWatcher
import android.view.View

import android.widget.EditText

class CadastroActivity : AppCompatActivity() {

    lateinit var dados: MutableList<Item>
    lateinit var recyclerView: RecyclerView

    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)


        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java,
            "banco.db").allowMainThreadQueries().build()

        dados = db.itemDao().obterItens()

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.setLayoutManager(llm)
        val itemAdapter = ItemAdapter(this, dados)
        recyclerView.adapter = itemAdapter

        val divider = DividerItemDecoration(recyclerView.context, llm.orientation)
        recyclerView.addItemDecoration(divider)




        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {

            incluirItem()


        }

        itemAdapter.onItemClick = { item ->
            abrirNovaTela(item)

            Toast.makeText(this, "Você escolheu o jogador de Nº: ${item.id}", Toast.LENGTH_LONG).show()

        }
    }

    fun incluirItem() {
        val edtInfo1 = findViewById<EditText>(R.id.edtInfo1)
        val edtInfo2 = findViewById<EditText>(R.id.edtInfo2)


        val novoItem = Item(0, edtInfo1.toString(), edtInfo2.toString())
        if (edtInfo1!=null){
            dados.add(novoItem)
            db.itemDao().incluirItem(novoItem)
            recyclerView.adapter?.notifyDataSetChanged()
            Toast.makeText(this, "Jogador Inserido com sucesso na Lista!!!", Toast.LENGTH_LONG).show()


        }else{ Toast.makeText(this, "Necessário preencher o Nome do Jogador", Toast.LENGTH_LONG).show()}

        edtInfo1.text.clear()
        edtInfo2.text.clear()
        edtInfo1.requestFocus()

    }
    fun abrirNovaTela(a:Item) {
//        val edtNome = findViewById<EditText>(R.id.${item.info1})
//        val edtValor = findViewById<EditText>(R.id.edtInfo2)
        val id = a.id
        val nome = a.info1
        val numero = a.info2
        // print(nome+numero)
        //debug ^^^^
        val intent = android.content.Intent(this, com.example.lista.SegundaActivity::class.java)
        intent.putExtra("nome", nome)
        intent.putExtra("numero", numero)
        intent.putExtra("id", id)
        startActivity(intent)

    }
}