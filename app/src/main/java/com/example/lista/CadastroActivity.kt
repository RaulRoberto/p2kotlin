package com.example.lista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.lista.adapter.ItemAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.lista.data.AppDatabase
import com.example.lista.model.Item

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
            finish()
            startActivity(getIntent());

        }

        itemAdapter.onItemClick = { item ->
            abrirNovaTela(item.id,item.info1,item.info2.toInt())
            Toast.makeText(this, "Você escolheu o jogador de Nº: ${item.id}", Toast.LENGTH_LONG).show()

        }
    }

    fun incluirItem() {
        val edtInfo1 = findViewById<EditText>(R.id.edtInfo1)
        val edtInfo2 = findViewById<EditText>(R.id.edtInfo2)

        val novoItem = Item(0, edtInfo1.text.toString(), edtInfo2.text.toString())
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

    fun abrirNovaTela(id:Int, nome:String, valor: Int){
        //val edtNome = findViewById<EditText>(R.id.edtInfo1)
        //val edtValor = findViewById<EditText>(R.id.edtInfo2)

        //val nome = edtNome.text.toString()
        //val valor = edtValor.text.toString().toInt()

        val intent = Intent(this, SegundaActivity::class.java)
        intent.putExtra("nome", nome)
        intent.putExtra("valor", valor)
        intent.putExtra("id",id)
        //intent.putExtra("id",a)
        startActivity(intent)

    }
}