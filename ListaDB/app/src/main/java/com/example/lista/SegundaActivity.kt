package com.example.lista

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lista.model.Item
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.*


class SegundaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)

        val img = findViewById<ImageView>(R.id.imageView)
        val id = intent.getIntExtra("id",0)
        //vai da errado isso
        val nome = intent.getStringExtra("nome")
        val numero = intent.getStringExtra("numero")



        val spinner = findViewById<Spinner>(R.id.spinner)

        val context = this

        // list of spinner items
        val list = listOf("Real","Dolar","Euro","Libra"
        )

        // initialize an array adapter for spinner
        val adapter:ArrayAdapter<String> = object: ArrayAdapter<String>(
            context,
            android.R.layout.simple_spinner_dropdown_item,
            list
        ){
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view:TextView = super.getDropDownView(
                    position,
                    convertView,
                    parent
                ) as TextView
                // set item text bold

                // set selected item style
                return view
            }
        }

        // finally, data bind spinner with adapter
        spinner.adapter = adapter




        val txtNome = findViewById<TextView>(R.id.txtNome)
        val txtValor = findViewById<TextView>(R.id.txtValor)
        val txtId = findViewById<TextView>(R.id.txtId)

        txtId.text = id.toString()
        txtNome.text = nome
        txtValor.text = numero
        var numero2= numero?.toDouble()

        val button2 = findViewById<Button>(R.id.button2)

        // spinner on item selected listener
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                txtValor.text = "Selected: "
                // get selected item text
                if (position == 0) txtValor.text = String.format("BRL :  %.2f",numero2)
                if (position == 1 ) txtValor.text = String.format("USD : %.2f",(numero2?.div(5)))
                if (position == 2 ) txtValor.text = String.format("EUR : %.2f",(numero2?.div(6)))
                if (position == 3 ) txtValor.text = String.format("GBP : %.2f",(numero2?.div(7)))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // another interface callback
            }
        }




        //fim spinner


        if (id%2==0) {
            img.setImageResource(R.drawable.mask_1)
        } else if (id%3==0) {
            img.setImageResource(R.drawable.mask_2)
        }else img.setImageResource(R.drawable.mask_3)



        button2.setOnClickListener { finish()}
    }

}